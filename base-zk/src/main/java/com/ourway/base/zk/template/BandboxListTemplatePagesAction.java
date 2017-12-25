package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseBandboxWindow;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.TemplateUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;

import java.util.ArrayList;
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
public class BandboxListTemplatePagesAction extends BaseBandboxWindow {
    protected Log info = LogFactory.getLog(BandboxListTemplatePagesAction.class);
    private Div conditionGrid;
    private BaseGrid dataList;
    private Div buttonGrid;
    private Integer totalRows;//多少行查询条件
    private PageLayoutVO gridData;//grid对象
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

    @Override
    public PageLayoutVO getBandboxProperty(String pageCa) {
        PageLayoutVO data = PageDataUtil.deteailControl(pageCa, "bandboxDiv");
        return data;
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
        totalRows = 1;
        //2.获取下面的子控件
        totalRows = 0;
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //根据行数设置查询框的值。
            List<String> setList = TemplateUtils.doGenOrderForSet(data.getLayOutComponents());
            for (String s : setList) {
//            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(s);
                if (null == subComps || subComps.size() <= 0)
                    continue;
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("conditionGrid_" + s);
                div.setParent(conditionGrid);
                getTemplateUtils().doRowComponents(subComps, div, data, this);
                totalRows++;
            }
//            totalRows = data.getLayOutComponents().size();
        }
        //如果查询条件多余二行，则进行隐藏
    }

    @Override
    public void showOrHide(boolean flag) {
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
        gridData = PageDataUtil.deteailControl(getPageCA(), "dataList");
        if (null == gridData)
            return;
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(gridData);

        //显示当前grid的表头和相关信息
        getGridUtils().setGridProperty(dataList, gridData);
        if (!TextUtils.isEmpty(gridData.getControlHeight()))
            dataList.setHeight(gridData.getControlHeight() + "px");
        //2.创建表头
        getGridUtils().setGridHeader(dataList, gridData);
        //判断是否要加载数据
        if (null != gridData.getControlLoad() && gridData.getControlLoad() == 1) {
            //需要初始化加载数据
            List<FilterModel> models = new ArrayList<FilterModel>();
            if (!TextUtils.isEmpty(gridData.getControlIntGrid())) {
                models = bind2Filter(gridData.getControlIntGrid());
            }
            dataList.filter(models);
            getGridUtils().displayDataWithTextbox(dataList, dataList.getResult(), this, false);
//            dataList.display();
        }

    }

    @Override
    public List<Map<String, Object>> filterByKey(String key) {
        if (TextUtils.isEmpty(gridData.getControlBandboxInt()))
            return null;
        List<FilterModel> models = new ArrayList<FilterModel>();
        List<Object> objs = new ArrayList<Object>(1);
        objs.add(key);
        FilterModel model = FilterModel.instance("key", FilterModel.EQUALS, objs);
        models.add(model);
        dataList.filter(models, gridData.getControlBandboxInt());
        getGridUtils().displayDataWithTextbox(dataList, dataList.getResult(), this, false);
        if (!TextUtils.isEmpty(dataList.getResult()) && dataList.getResult().size() > 0)
            return dataList.getResult();
        else
            return null;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
    }

    @Override
    public List<Map<String, Object>> filterByModel(FilterModel model) {
        List<FilterModel> models = new ArrayList<FilterModel>();
        models.add(model);
        dataList.filter(models, gridData.getControlInt());
        if (null != dataList.getResult() && dataList.getResult().size() > 0) {
            return dataList.getResult();
        }
        return null;
    }

    @Override
    public List<Row> doGetGridRow() {
        List<Row> _rows = dataList.getRows().getChildren();
        return _rows;
    }

    @Override
    public void reloadPpt(boolean flag) {
        initDataList();
    }
}
