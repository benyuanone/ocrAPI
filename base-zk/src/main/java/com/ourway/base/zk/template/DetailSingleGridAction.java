package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.service.impl.APIPageInitSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class DetailSingleGridAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DetailSingleGridAction.class);

    /*操作按钮区域*/
    private Div conditionGrid;
    private Div buttonGrid;
    /*主表填写区域*/
    private BaseGrid dataList;
    private Map<String, Component> hboxMap = new HashMap<String, Component>();
    private Integer totalRows;//多少行查询条件
    PageVO pageVO = null;
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        conditionGrid = (Div) getFellowIfAny("conditionGrid");
        buttonGrid = (Div) getFellowIfAny("buttonGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        pageVO = PageDataUtil.detailPageByCa(getPageCA());
        initOperateGrid();
        initButtonGrid();
        initMainTableGrid();
        initPage(event);
        if(getWindowType()==2)
           bind2Page();
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

        if (null!=pageVO&&!TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
            } catch (Exception e) {
                AlterDialog.alert("实现类不存在，请确认配置信息是否正确");
            }
        }

    }


    //只有一行
    private void initOperateGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "conditionGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            conditionGrid.setVisible(false);
            return;
        }

        conditionGrid.getChildren().clear();//清除下面的控件
        //2.获取下面的子控件
        totalRows = 0;
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //根据行数设置查询框的值。
            List<String> setList = TemplateUtils.doGenOrderForSet(data.getLayOutComponents());
            for(String  s:setList){
                List<PageControlVO> subComps = data.getLayOutComponents().get(s);
                if(null==subComps||subComps.size()<=0)
                    continue;
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("conditionGrid_" + s);
                div.setParent(conditionGrid);
                getTemplateUtils().doRowComponents(subComps, div, data, this);
                totalRows++;
            }
//            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
//                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
//                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
//                div.setId("conditionGrid_" + index);
//                div.setParent(conditionGrid);
//                getTemplateUtils().doRowComponents(subComps, div, data, this);
//            }
//            totalRows = data.getLayOutComponents().size();
        }
        //如果查询条件多余二行，则进行隐藏
        showOrHide(false);
    }
    @Override
    public void showOrHide(boolean flag) {
        if (null!=totalRows&&totalRows >ZKConstants.QUERY_ROW_NUM) {
            List<String> ids = new ArrayList<String>();
            for (int index = 2; index < totalRows; index++)
                ids.add("conditionGrid_" + index);
            getTemplateUtils().showOrHideConditionDiv(flag, this, ids);
        }
    }

    private void initButtonGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "buttonGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            buttonGrid.setVisible(false);
            return;
        }

        String rowspan = "";
        buttonGrid.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("buttonGrid_" + index);
                div.setParent(buttonGrid);
                getTemplateUtils().doButtonRowComponents(subComps, div, data, this);
            }
        }

    }


    //    主表区域
    private void initMainTableGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataList");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            dataList.setVisible(false);
            return;
        }
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(data);

        //显示当前grid的表头和相关信息
        getGridUtils().setGridProperty(dataList, data);
        getGridUtils().setDataGridProperty(dataList, data);
        //2.创建表头
        getGridUtils().setInnerGridHeader(dataList, data);
        if (null != data.getControlLoad() && data.getControlLoad() == 1) {
            if (!TextUtils.isEmpty(data.getControlInt())) {
                List<FilterModel> models = new ArrayList<FilterModel>();
                if (!TextUtils.isEmpty(data.getControlIntGrid())) {
                    models = bind2Filter(data.getControlIntGrid());
                }
                dataList.filter(models);
                getGridUtils().displayDataWithTextbox(dataList,dataList.getResult(),this,true);
            }
        }
        //判断是否要加载数据
        if (null != ppt && null != ppt.get("dataList")) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) ppt.get("dataList");
            //根据index字段，对数据进行排序
            Collections.sort(datas, new CompareIndexOrder());//对数据进行排序，进行展现
            getGridUtils().displayDataWithTextbox(dataList,datas,this,true);
//            dataList.displaySubDatas(datas);
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
