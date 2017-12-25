package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTabbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
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
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class MultiPanelTabTemplateAction extends BaseWindow {
    protected Log info = LogFactory.getLog(MultiPanelTabTemplateAction.class);

    Div mainTableGrid;

    Map<String, PageLayoutVO> tabMap = new HashMap<String, PageLayoutVO>();
    Map<String, Component> tabPanelMap = new HashMap<String, Component>();//当前界面中的tab和panel组合
    Map<String, List<List<PageControlVO>>> groupButtonMap = new HashMap<String, List<List<PageControlVO>>>();//分组组的相关信息
    Map<String, Div> buttonDiv = new HashMap<String, Div>();//分组组的相关信息
    Map<String, PageLayoutVO> dataListMap = new HashMap<String, PageLayoutVO>();//行对应的dataList
    Component parentComponent = null;
    PageVO pageVO = null;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        mainTableGrid = (Div) getFellowIfAny("mainTableGrid");
        pageVO = PageDataUtil.detailPageByCa(getPageCA());
        initPage(event);
        initMainTableGrid();
        if (this.getWindowType() != 1) {
            bind2Page();
        }
        reloadButton();
        afterInitPage(event);
    }

    private void initPage(CreateEvent event) {
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
            } catch (Exception e) {
                AlterDialog.alert("详细页面初始化实现类不存在，请确认配置信息是否正确");
            }
        }
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

    //初始化tab和panel数据
    private void doInitTabPanel() {
        List<PageLayoutVO> tabControls = PageDataUtil.deteailTabControl(getPageCA());
        if (null != tabControls && tabControls.size() > 0) {
            for (PageLayoutVO tabControl : tabControls) {
                tabMap.put(tabControl.getControlId(), tabControl);
            }
        }

    }

    private void doInitButton() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "mainButtonGrid");
        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                doHandleButtonGroup(subComps, index);

            }
        }
    }

    //处理tab或者panel页面
    private List<PageControlVO> doHandleButtonGroup(List<PageControlVO> subComps, int index) {
        List<PageControlVO> inDiv = new ArrayList<PageControlVO>();
        String compRow = "";
        Map<String, List<PageControlVO>> groupButtons = new HashMap<String, List<PageControlVO>>();
        List<PageLayoutControlVO> groupRowDefine = new ArrayList<PageLayoutControlVO>();
        for (PageControlVO pageControlVO : subComps) {
            if (null != pageControlVO.getLayoutComponent().getCompOrder() && pageControlVO.getLayoutComponent().getCompOrder() == -1) {
                //处理tab页面或者panel页面
                PageLayoutControlVO pgvo = pageControlVO.getLayoutComponent();
                groupRowDefine.add(pgvo);
            } else {
                List<PageControlVO> vos = new ArrayList<PageControlVO>();
                if (null != groupButtons.get(pageControlVO.getLayoutComponent().getCompHbox() + "group"))
                    vos = groupButtons.get(pageControlVO.getLayoutComponent().getCompHbox() + "group");
                vos.add(pageControlVO);
                groupButtons.put(pageControlVO.getLayoutComponent().getCompHbox() + "group", vos);
            }
        }
        for (PageLayoutControlVO pageLayoutControlVO : groupRowDefine) {
            List<List<PageControlVO>> datas = new ArrayList<List<PageControlVO>>();
            compRow = "buttonRow_" + pageLayoutControlVO.getCompColor();
            if (null != groupButtonMap.get(compRow))
                datas = (List<List<PageControlVO>>) groupButtonMap.get(compRow);
            if (null == groupButtons.get(pageLayoutControlVO.getCompHbox() + "group"))
                continue;
            datas.add(groupButtons.get(pageLayoutControlVO.getCompHbox() + "group"));
            groupButtonMap.put(compRow, datas);
        }
        return inDiv;
    }

    private void doInitDataList() {
        List<PageLayoutVO> datas = PageDataUtil.deteailDataListControl(getPageCA(), "dataList");
        if (null != datas && datas.size() > 0) {
            for (PageLayoutVO data : datas) {
                dataListMap.put("row" + data.getControlParentId(), data);
            }
        }
    }


    //    主表区域
    private void initMainTableGrid() {
        //处理tab
        doInitTabPanel();
        doInitButton();
        doInitDataList();
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
        mainTableGrid.getChildren().clear();//清除下面的控件
        parentComponent = mainTableGrid;
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            List<String> setList = TemplateUtils.doGenOrderForSet(data.getLayOutComponents());
            for (String s : setList) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(s);
                if (null == subComps || subComps.size() <= 0)
                    continue;
                //处理多tab
                subComps = doHandleTabOrPanel(subComps);

                //1.处理该行下面是否有按钮
                doDisplayButtons(s);
                //2.处理表单的内容
                if (null != subComps && subComps.size() > 0) {
                    Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS + " form-group");
                    div.setId("mainTableGrid_" + s);
                    div.setParent(parentComponent);
                    getTemplateUtils().doRowComponents(subComps, div, data, this);
                }
                //3.处理dataList
                initDataList(s);

            }
        }

    }


    //在界面中显示按钮控件
    private void doDisplayButtons(String row) {
        if (null == groupButtonMap.get("buttonRow_" + row)) {
            return;
        }
        List<List<PageControlVO>> controlVOs = groupButtonMap.get("buttonRow_" + row);
        if (null == controlVOs || controlVOs.size() <= 0)
            return;
        Div div = null;
        if (null == buttonDiv.get("operateGrid_" + row)) {
            div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
            div.setId("operateGrid_" + row);
            div.setParent(parentComponent);
            buttonDiv.put(div.getId(), div);
        } else
            div = buttonDiv.get("operateGrid_" + row);
        PageLayoutVO data = new PageLayoutVO();
        data.setControlId(row);
        for (List<PageControlVO> controlVO : controlVOs) {
            getTemplateUtils().doButtonRowComponents(controlVO, div, data, this);
        }

    }


    //处理tab或者panel页面
    private List<PageControlVO> doHandleTabOrPanel(List<PageControlVO> subComps) {
        List<PageControlVO> inDiv = new ArrayList<PageControlVO>();
        parentComponent = mainTableGrid;
        for (PageControlVO pageControlVO : subComps) {
            if (null != pageControlVO.getLayoutComponent().getCompOrder() && pageControlVO.getLayoutComponent().getCompOrder() == -1) {
                //处理tab页面或者panel页面
                if (!TextUtils.isEmpty(pageControlVO.getLayoutComponent().getCompColor())) {
                    parentComponent = doCreateTabPanel(pageControlVO.getLayoutComponent().getCompColor());
                } else {
                    parentComponent = mainTableGrid;
                }
            } else {
                inDiv.add(pageControlVO);
            }
        }
        return inDiv;
    }

    private Component doCreateTabPanel(String tabid) {
        PageLayoutVO layoutVO = tabMap.get(tabid);
        Component parentCompoent = mainTableGrid;
        if (null == layoutVO)
            return null;
        if (!TextUtils.isEmpty(layoutVO.getControlParentId())) {
            parentCompoent = doCreateTabPanel(layoutVO.getControlParentId());
        }
        //开始判断是否有这个tab或者panel
        if (layoutVO.getControlClass().equals("0")) {
            //表示是tab
            if (null == layoutVO.getControlSplitpage()) {
                AlterDialog.alert("Tab配置错误,没有配置组号");
                return null;
            }
            BaseTabbox tabbox = null;
            if (null == tabPanelMap.get("mainTab_" + layoutVO.getControlSplitpage())) {
                //创建tab
                tabbox = new BaseTabbox();
                tabbox.setId("mainTab_" + layoutVO.getControlSplitpage());
                tabbox.setParent(parentCompoent);
                tabPanelMap.put("mainTab_" + layoutVO.getControlSplitpage(), tabbox);
                Tabs _tabs = new Tabs();
                _tabs.setParent(tabbox);
                Tabpanels _tabpanels = new Tabpanels();
                _tabpanels.setParent(tabbox);
                //同时创建tabbox的tabs和tabpanels
            } else {
                tabbox = (BaseTabbox) tabPanelMap.get("mainTab_" + layoutVO.getControlSplitpage());
            }
            if (null == tabPanelMap.get(layoutVO.getControlId())) {
                //创建tab
                Tab tab1 = new Tab();
                tab1.setId(layoutVO.getControlId());
                tab1.setParent(tabbox.getTabs());
                tab1.setLabel(I18nUtil.getLabelContent(layoutVO.getControlSclass()));
                tabPanelMap.put(layoutVO.getControlId(), tab1);
                Tabpanel tabpanel = new Tabpanel();
                tabpanel.setId(layoutVO.getControlId() + "_panel");
                tabpanel.setParent(tabbox.getTabpanels());
                tabPanelMap.put(tabpanel.getId(), tabpanel);
                return tabpanel;
            } else
                return tabPanelMap.get(layoutVO.getControlId() + "_panel");

        } else {
            //表示panel
            if (null == tabPanelMap.get(layoutVO.getControlId())) {
                Panel panel = new Panel();
                panel.setId(layoutVO.getControlId());
                panel.setParent(parentCompoent);
                panel.setSclass("panel-primary");
                panel.setCollapsible(true);
                if (null != layoutVO.getControlHeight() && layoutVO.getControlHeight() == 1)
                    panel.setOpen(false);
                panel.setTitle(I18nUtil.getLabelContent(layoutVO.getControlSclass()));
                tabPanelMap.put(layoutVO.getControlId(), panel);
                Panelchildren panelchildren = new Panelchildren();
                panelchildren.setParent(panel);
                panelchildren.setId(layoutVO.getControlId() + "_panelChildren");
                tabPanelMap.put(panelchildren.getId(), panelchildren);
                return panelchildren;
            } else {
                return tabPanelMap.get(layoutVO.getControlId() + "_panelChildren");
            }
        }

    }


    //    //初始化列表
    private void initDataList(String row) {
        if (null == dataListMap.get("row" + row))
            return;
        PageLayoutVO data = dataListMap.get("row" + row);
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            return;
        }
        BaseGrid dataList = new BaseGrid();
        dataList.setParent(parentComponent);
        dataList.getChildren().clear();//清除下面的控件
        dataList.setLayoutVO(data);
        dataList.setId(data.getControlId());

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
    public void reloadPpt(boolean flag) {
        PageVO pageVO = PageDataUtil.detailPageByCa(getPageCA());
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, parentArgs, pageVO);
                if (flag)
                    bind2Page();
            } catch (Exception e) {

            }
        }
    }
}
