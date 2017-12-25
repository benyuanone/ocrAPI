package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

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
public class DetailTwoTableTemplateAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DetailTwoTableTemplateAction.class);

    /*操作按钮区域*/
    private BaseGrid operateGrid;
    /*主表填写区域*/
    private BaseGrid mainTableGrid;
    /*子表更新区*/
    private BaseGrid dataList;
    private BaseGrid dataListHeader;
    private Caption dataListGroupboxCaption;
    private Map<String, Component> hboxMap = new HashMap<String, Component>();
    PageVO pageVO = null;
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        operateGrid = (BaseGrid) getFellowIfAny("operateGrid");
        mainTableGrid = (BaseGrid) getFellowIfAny("mainTableGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        dataListHeader = (BaseGrid) getFellowIfAny("dataListHeader");
        dataListGroupboxCaption = (Caption) getFellowIfAny("dataListGroupboxCaption");
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
    private void initPage(CreateEvent event) {

        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
                //初始化后绑定到页面中
                if(getWindowType()>=2)
                bind2Page();
            } catch (Exception e) {
                AlterDialog.alert("详细页面初始化实现类不存在，请确认配置信息是否正确");
            }
        }

    }


    //只有一行
    private void initOperateGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "operateGrid");
        if (null == data) {
            operateGrid.setVisible(false);
            return;
        }
        //不显示
        if (null != data.getControlIsshow() && data.getControlIsshow() == 1) {
            operateGrid.setVisible(false);
            return;
        }
        String rowspan = "";
        //对主表进行高度，宽度和样式的定义
        GridUtils.setGridProperty(operateGrid, data);
        operateGrid.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            Rows rows = operateGrid.getHeaderRows();
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                rowspan = PageUtils.getSpans(subComps);
                Row row = PageUtils.createRow(operateGrid, rowspan);
//                PageUtils.doRowComponent(subComps, row, data, "left", this, hboxMap,true);
            }
        }
    }

    //定义Groupbox的标签
    private void initDataListGroupboxCaptionPageUtils() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataListGroupboxCaption");
        if (null == data) {
            dataListGroupboxCaption.getParent().setVisible(false);
            return;
        }
        //不显示
        if (null != data.getControlIsshow() && data.getControlIsshow() == 1) {
            dataListGroupboxCaption.getParent().setVisible(false);
            return;
        }
        if (!TextUtils.isEmpty(data.getControlLabel()))
            dataListGroupboxCaption.setLabel(I18nUtil.getLabelContent(data.getControlLabel()));
        if (!TextUtils.isEmpty(data.getControlSclass()))
            dataListGroupboxCaption.setSclass(data.getControlSclass().toString());

    }

    //    主表区域
    private void initMainTableGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "mainTableGrid");
        if (null == data) {
            mainTableGrid.setVisible(false);
            return;
        }
        //不显示
        if (null != data.getControlIsshow() && data.getControlIsshow() == 1) {
            mainTableGrid.setVisible(false);
            return;
        }
        String rowspan = "";
        GridUtils.setGridProperty(mainTableGrid, data);
        mainTableGrid.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            Rows rows = mainTableGrid.getHeaderRows();
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                rowspan = PageUtils.getSpans(subComps);
                Row row = PageUtils.createRow(mainTableGrid, rowspan);
//                PageUtils.doRowComponent(subComps, row, data, "left", this, hboxMap,false);
            }
        }
    }

    private void initDataListHeader() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataListHeader");
        if (null == data) {
            dataListHeader.setVisible(false);
            return;
        }
        //不显示
        if (null != data.getControlIsshow() && data.getControlIsshow() == 1) {
            dataListHeader.setVisible(false);
            return;
        }
        String rowspan = "";
        GridUtils.setGridProperty(dataListHeader, data);
        dataListHeader.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            Rows rows = dataListHeader.getHeaderRows();
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                rowspan = PageUtils.getSpans(subComps);
                Row row = PageUtils.createRow(dataListHeader, rowspan);
//                PageUtils.doRowComponent(subComps, row, data, "left", this, hboxMap,true);
            }
        }
    }


    //    //初始化列表
    private void initDataList() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataList");

        if (null == data) {
            dataList.setVisible(false);
            return;
        }
        //不显示
        if (null != data.getControlIsshow() && data.getControlIsshow() == 1) {
            dataList.setVisible(false);
            return;
        }
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(data);

        //显示当前grid的表头和相关信息
        GridUtils.setGridProperty(dataList, data);
        GridUtils.setDataGridProperty(dataList,data);
        //2.创建表头
        GridUtils.instance().setInnerGridHeader(dataList, data);
        //判断是否要加载数据
        if (null != ppt && null != ppt.get("dataList")) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) ppt.get("dataList");
            //根据index字段，对数据进行排序
            Collections.sort(datas, new CompareIndexOrder());//对数据进行排序，进行展现
            dataList.displaySubDatas(datas);
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
