package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseLabel;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ListPageTemplateAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ListPageTemplateAction.class);

    private Borderlayout borderlayout;
    private East eastLayout;
    private BaseGrid conditionGrid;
    private BaseGrid otherConditionGrid;
    private BaseGrid dataList;
    private BaseGrid buttonGrid;
    /*临时变量，用来存储控件组合的时候问题*/
    private Map<String, Div> hboxMap = new HashMap<String, Div>();


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        buttonGrid = (BaseGrid) getFellowIfAny("buttonGrid");
        conditionGrid = (BaseGrid) getFellowIfAny("conditionGrid");
        otherConditionGrid = (BaseGrid) getFellowIfAny("otherConditionGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        eastLayout = (East) getFellowIfAny("eastLayout");
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 140) + "px");
        initConditionGrid();
        initOtherConditionGrid();
        initButtonGrid();
        initDataList();
        reloadButton();
    }

    //初始化顶部条件
    private void initConditionGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "conditionGrid");
        if (null == data)
            return;
        String rowspan = "";
        //对主表进行高度，宽度和样式的定义
        GridUtils.setGridProperty(conditionGrid, data);
        conditionGrid.getChildren().clear();//清除下面的控件
        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            Rows rows = conditionGrid.getHeaderRows();
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                rowspan = PageUtils.getSpans(subComps);
                Row row = PageUtils.createRow(conditionGrid,rowspan);
                doRowComponent(subComps, row, data, "center");
            }
        }
    }

    //初始化右侧高级查询条件
    private void initOtherConditionGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "otherConditionGrid");
        if (null == data)
            return;
        //对主表进行高度，宽度和样式的定义
        GridUtils.setGridProperty(otherConditionGrid, data);
        otherConditionGrid.getChildren().clear();//清除下面的控件
        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            Rows rows = new Rows();
            rows.setParent(otherConditionGrid);
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                Row row = new Row();
                row.setParent(rows);
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                doRowComponent(subComps, row, data, "left");
            }
        }
    }

    //初始化按钮区域
    private void initButtonGrid() {
        //此功能是页面中的功能按钮，包括新增，删除，拷贝等操作
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "buttonGrid");
        if (null == data)
            return;
        //对主表进行高度，宽度和样式的定义
        GridUtils.setGridProperty(buttonGrid, data);
        buttonGrid.getChildren().clear();//清除下面的控件

        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            Rows rows = new Rows();
            rows.setParent(buttonGrid);
            Row row = new Row();
            row.setParent(rows);
            row.setStyle("align:left");
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                doRowComponent(subComps, row, data, "left");
            }
        }
    }

    //初始化列表
    private void initDataList() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataList");
        if (null == data)
            return;
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(data);

        //显示当前grid的表头和相关信息
        GridUtils gridUtils = new GridUtils();
        gridUtils.setGridProperty(dataList, data);
        gridUtils.setDataGridProperty(dataList, data);
        //2.创建表头
        gridUtils.setGridHeader(dataList,data);
        //判断是否要加载数据
       if(null!=data.getControlLoad()&&data.getControlLoad()==1){
           //需要初始化加载数据
           List<FilterModel> models = new ArrayList<FilterModel>();
           if(!TextUtils.isEmpty(data.getControlIntGrid())){
               models = bind2Filter(data.getControlIntGrid());
           }
           dataList.filter(models);
           dataList.display();
       }

    }

    //每个row内部控件的创建
    private void doRowComponent(List<PageControlVO> subComps, Row row, PageLayoutVO data, String divAlign) {
        for (PageControlVO subComp : subComps) {
            if (null!=subComp.getLayoutComponent().getCompLable()&&subComp.getLayoutComponent().getCompLable() == 1) {
                BaseLabel label = PageUtils.createLabelWithDiv(row, subComp.getKjLabelid(), subComp.getLayoutComponent().getCompColor(), "right");
            }
            //创建控件
            createComponent(subComp, row, data, divAlign);
        }
    }

    //增加表格中的控件
    private void createComponent(PageControlVO subComp, Row row, PageLayoutVO data, String divAlign) {
        //根据类型创建控件
        //把控件放到全局的map中
        String compId = data.getControlId() + COMP_CONTACT + subComp.getKjAttribute();
//        Component kjComp = PageUtils.doAddComponent(subComp,compId,this);
//        if(null==kjComp){
//            AlterDialog.alert("无法创建控件"+subComp.getKjType());
//            return;
//        }
        //处理控件放的位置
        if (null != subComp.getLayoutComponent().getCompHbox() && subComp.getLayoutComponent().getCompHbox() != 0) {
            String _hboxKey = data.getControlId() + "_hbox_" + subComp.getLayoutComponent().getCompHbox();
            Div hbox = null;
            if (null == hboxMap.get(_hboxKey)) {
                Div div = new Div();
                div.setAlign(divAlign);
                div.setParent(row);
                Hbox  _hbox = new Hbox();
                _hbox.setParent(div);
                hbox = new Div();
                hbox.setClass("btn-group");
                hbox.setParent(_hbox);
                hboxMap.put(_hboxKey, hbox);
            } else
                hbox = hboxMap.get(_hboxKey);
//            kjComp.setParent(hbox);
        } else {
//            kjComp.setParent(row);
        }
    }

    public void detch() {
        this.detach();
    }

    @Override
    public void onEvent(Event event) throws Exception {
          super.onEvent(event);
        System.out.println(event.getTarget().getClass().getName());
    }

}
