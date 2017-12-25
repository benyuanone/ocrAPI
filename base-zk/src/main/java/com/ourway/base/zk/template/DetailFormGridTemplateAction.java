package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.InnerGridInitSer;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.service.impl.InnerGridApiInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TemplateUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Panel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class DetailFormGridTemplateAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DetailFormGridTemplateAction.class);

    /*操作按钮区域*/
    private Div operateGrid;
    /*主表填写区域*/
    private Div mainTableGrid;
    /*子表更新区*/
    private BaseGrid dataList;
    private Div dataListHeader;
    private Panel dataListGroupboxCaption;
    private Map<String, Component> hboxMap = new HashMap<String, Component>();
    PageVO pageVO = null;
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        setTemplateUtils(new TemplateUtils());
        operateGrid = (Div) getFellowIfAny("operateGrid");
        mainTableGrid = (Div) getFellowIfAny("mainTableGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        dataListHeader = (Div) getFellowIfAny("dataListHeader");
        dataListGroupboxCaption = (Panel) getFellowIfAny("dataListGroupboxCaption");
        pageVO = PageDataUtil.detailPageByCa(getPageCA());
        initOperateGrid();
        initDataListGroupboxCaptionPageUtils();
        initMainTableGrid();
        initDataListHeader();
        initPage(event);
        initDataList();
        reloadButton();
        afterInitPage(event);
    }
    private void afterInitPage(CreateEvent event) {
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInitAfter())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInitAfter()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
                //初始化后绑定到页面中
            } catch (Exception e) {
                AlterDialog.alert("请确认配置信息是否正确");
            }
        }
    }
    //只有一行
    private void initOperateGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "operateGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            operateGrid.setVisible(false);
            return;
        }
        operateGrid.getChildren().clear();//清除下面的控件
        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("operateGrid_" + index);
                div.setParent(operateGrid);
                getTemplateUtils().doButtonRowComponents(subComps, div, data, this);
            }
        }
    }

    //定义Groupbox的标签
    private void initDataListGroupboxCaptionPageUtils() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataListGroupboxCaption");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            dataListGroupboxCaption.setVisible(false);
            return;
        }
        if (!TextUtils.isEmpty(data.getControlLabel()))
            dataListGroupboxCaption.setTitle(I18nUtil.getLabelContent(data.getControlLabel()));
        if (!TextUtils.isEmpty(data.getControlSclass()))
            dataListGroupboxCaption.setSclass(data.getControlSclass().toString());
        else
            dataListGroupboxCaption.setSclass(ZKConstants.PANEL_DEFAULT_CSS);
    }


    private void initPage(CreateEvent event) {

        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
                //初始化后绑定到页面中
                if (getWindowType()>=2)
                     bind2Page();
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert("详细页面初始化实现类不存在，请确认配置信息是否正确");
            }
        }

    }

    //    主表区域
    private void initMainTableGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "mainTableGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            mainTableGrid.setVisible(false);
            return;
        }
        mainTableGrid.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            List<String> setList = TemplateUtils.doGenOrderForSet(data.getLayOutComponents());
            for (String s : setList) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(s);
                if (null == subComps || subComps.size() <= 0)
                    continue;
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS + " form-group");
                div.setId("mainTableGrid_" + s);
                div.setParent(mainTableGrid);
                getTemplateUtils().doRowComponents(subComps, div, data, this);
            }
        }
    }

    private void initDataListHeader() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataListHeader");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            dataListHeader.setVisible(false);
            return;
        }
        dataListHeader.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("dataListHeader_" + index);
                div.setParent(dataListHeader);
                getTemplateUtils().doButtonRowComponents(subComps, div, data, this);
            }
        }
    }


    //    //初始化列表
    private void initDataList() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataList");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            dataList.setVisible(false);
            return;
        }
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(data);

        //显示当前grid的表头和相关信息
        GridUtils.setGridProperty(dataList, data);
//        GridUtils.setDataGridProperty(dataList, data);
        //2.创建表头
        getGridUtils().setInnerGridHeader(dataList, data);
        //判断是否要加载数据
        if (!TextUtils.isEmpty(ppt.get("dataList"))) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) ppt.get("dataList");
            if (null != datas && datas.size() > 0) {
                //根据index字段，对数据进行排序
                Collections.sort(datas, new CompareIndexOrder());//对数据进行排序，进行展现
                dataList.displaySubDatas(datas);
            }
        }
        //通过接口加载的模式来完成
        if (null != data.getControlLoad() && data.getControlLoad() == 1 && !TextUtils.isEmpty(data.getControlInt())) {
            InnerGridInitSer ser = new InnerGridApiInitSer();
            ser.initPage(this, data, ppt, dataList);
        }
    }


    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        System.out.println(event.getTarget().getClass().getName());
    }
    @Override
    public void reloadPpt(boolean flag){
        PageVO pageVO = PageDataUtil.detailPageByCa(getPageCA());
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, parentArgs, pageVO);
                if(flag)
                    bind2Page();
            } catch (Exception e) {

            }
        }
    }
}
