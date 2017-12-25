package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseLabel;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;

import java.util.ArrayList;
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
public class ListTemplatePagesAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ListTemplatePagesAction.class);

    private Div conditionGrid;
    private BaseGrid dataList;
    private Div buttonGrid;
    private Integer totalRows;//多少行查询条件
    /*临时变量，用来存储控件组合的时候问题*/


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        buttonGrid = (Div) getFellowIfAny("buttonGrid");
        conditionGrid = (Div) getFellowIfAny("conditionGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        initConditionGrid();
        initButtonGrid();
        initDataList();
        reloadButton();
    }

    //初始化顶部条件
    private void initConditionGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "conditionGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            conditionGrid.setVisible(false);
            return;
        }
        conditionGrid.getChildren().clear();//清除下面的控件
        totalRows = 0;
        //2.获取下面的子控件
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
            }
            totalRows = data.getLayOutComponents().size();
        }
        //如果查询条件多余二行，则进行隐藏
        showOrHide(false);
    }

    @Override
    public void showOrHide(boolean flag) {
        if (totalRows > 2) {
            List<String> ids = new ArrayList<String>();
            for (int index = 2; index < totalRows; index++)
                ids.add("conditionGrid_" + index);
            getTemplateUtils().showOrHideConditionDiv(flag, this, ids);
        }
    }

    //初始化按钮区域
    private void initButtonGrid() {
        //此功能是页面中的功能按钮，包括新增，删除，拷贝等操作
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "buttonGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            buttonGrid.setVisible(false);
            return;
        }

        buttonGrid.getChildren().clear();//清除下面的控件
        //2.获取下面的子控件
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

    //初始化列表
    private void initDataList() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "dataList");
        if (null == data)
            return;
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(data);

        //显示当前grid的表头和相关信息
        getGridUtils().setGridProperty(dataList, data);
        getGridUtils().setDataGridProperty(dataList, data);
        //2.创建表头
        getGridUtils().setGridHeader(dataList, data);
        //判断是否要加载数据
        if (null != data.getControlLoad() && data.getControlLoad() == 1) {
            //需要初始化加载数据
            List<FilterModel> models = new ArrayList<FilterModel>();
            if (!TextUtils.isEmpty(data.getControlIntGrid())) {
                models = bind2Filter(data.getControlIntGrid());
            }
            dataList.filter(models);

            getGridUtils().displayDataWithTextbox(dataList,dataList.getResult(),this,false);
//            dataList.display();
        }

    }


    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        System.out.println(event.getTarget().getClass().getName());

    }
    @Override
    public void reloadPpt(boolean flag){
      initDataList();
    }
}
