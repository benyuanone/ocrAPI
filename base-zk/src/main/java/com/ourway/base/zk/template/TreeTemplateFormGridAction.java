package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.InnerGridInitSer;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.service.impl.InnerGridApiInitSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
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
public class TreeTemplateFormGridAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TreeTemplateFormGridAction.class);
    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Treecell rootCell;
    private Menupopup menupopup;
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;

    private Integer totalRows;//多少行查询条件
    private BaseTextbox searchTxt;//查询条件

    private Map<String, PageControlVO> pageControlVOMap = new HashMap<String, PageControlVO>();
    /*临时变量，用来存储控件组合的时候问题*/
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
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 140) + "px");
//        tree.setHeight((ZKSessionUtils.getScreenHeight() - 180) + "px");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        rootItem.setValue(null);
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        rootCell = (Treecell) getFellowIfAny("rootCell");
        searchTxt = (BaseTextbox) getFellowIfAny("searchTxt");
        searchTxt.addEventListener(Events.ON_OK, this);


        operateGrid = (Div) getFellowIfAny("operateGrid");
        mainTableGrid = (Div) getFellowIfAny("mainTableGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        dataListHeader = (Div) getFellowIfAny("dataListHeader");
        dataListGroupboxCaption = (Panel) getFellowIfAny("dataListGroupboxCaption");
        pageVO = PageDataUtil.detailPageByCa(getPageCA());
        initTree();
        loadTreeData();//加载树

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
                if (getWindowType() >= 2)
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
        GridUtils.setDataGridProperty(dataList, data);
        //2.创建表头
        getGridUtils().setInnerGridHeader(dataList, data);
        //判断是否要加载数据
        if (null != ppt && null != ppt.get("dataList")) {
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

    private void initTree() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "tree");
        if (!TextUtils.isEmpty(data.getControlParentId()))
            tree.setProperty(data.getControlParentId());
        if (!TextUtils.isEmpty(data.getControlPath()))
            tree.setValProperty(data.getControlPath());
        if (!TextUtils.isEmpty(data.getControlClass()))
            tree.setFilterType(data.getControlClass());
        if (!TextUtils.isEmpty(data.getControlLabel())) {
            westLayout.setTitle(I18nUtil.getLabelContent(data.getControlLabel()));
            rootCell.setLabel(I18nUtil.getLabelContent(data.getControlLabel()));
        }
        if (null == data.getLayOutComponents() || null == data.getLayOutComponents().get("1")) {
            AlterDialog.alert("未配置树相关的配置，请完善");
            return;
        }
        List<PageControlVO> objsData = data.getLayOutComponents().get("1");
        for (PageControlVO pgvo : objsData) {
            pageControlVOMap.put(pgvo.getKjAttribute(), pgvo);
        }

        boolean rightMenu = false;
        //判断tree所包含的事件
        if (null != pageControlVOMap.get("dynamicTree") && !TextUtils.isEmpty(pageControlVOMap.get("dynamicTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("dynamicClick", true);
        }
        if (null != pageControlVOMap.get("onClickTree") && !TextUtils.isEmpty(pageControlVOMap.get("onClickTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("onClickTree", true);
        }
        if (null != pageControlVOMap.get("addTree") && !TextUtils.isEmpty(pageControlVOMap.get("addTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("addTree", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("addSameTree") && !TextUtils.isEmpty(pageControlVOMap.get("addSameTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("addSameTree", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("updateTree") && !TextUtils.isEmpty(pageControlVOMap.get("updateTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("updateTree", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick3") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick3").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick3", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick2") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick2").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick2", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick1") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick1").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick1", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick4") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick4").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick4", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick5") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick5").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick5", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick6") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick6").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick6", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("rightClick7") && !TextUtils.isEmpty(pageControlVOMap.get("rightClick7").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("rightClick7", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("delTree") && !TextUtils.isEmpty(pageControlVOMap.get("delTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("delTree", true);
            rightMenu = true;
        }
        if (null != pageControlVOMap.get("moveTree") && !TextUtils.isEmpty(pageControlVOMap.get("moveTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("moveTree", true);
        }
        if (null != pageControlVOMap.get("dbClickTree") && !TextUtils.isEmpty(pageControlVOMap.get("dbClickTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("dbClickTree", true);
        }
        tree.setAttribute("rightClick", rightMenu);
        TreeUtils.doCreateMenuRight(tree, this, pageControlVOMap);
        //默认的根也有右键
//        if (rightMenu) {
        rootItem.addEventListener(Events.ON_RIGHT_CLICK, this);
        rootItem.setContext(tree.getRightPopup());
//        }
    }


    @Override
    public void loadTreeData() {
        if (null == pageControlVOMap.get("loadTree"))
            return;
        PageControlVO vo = pageControlVOMap.get("loadTree");
        if (TextUtils.isEmpty(vo.getLayoutComponent().getEvnetFormula()))
            return;
        try {
            Object _obj = Class.forName(vo.getLayoutComponent().getEvnetFormula()).newInstance();
            TreeListinerSer compAction = (TreeListinerSer) _obj;
            compAction.doAction(this, null, tree, rootItem, vo);
        } catch (Exception e) {
            e.printStackTrace();
            AlterDialog.alert(vo.getLayoutComponent().getEvnetFormula() + "执行错误");
        }
    }


    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        if (event.getTarget().getId().equalsIgnoreCase("treeAction")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("addTree");
            vo.setTreeType(1);
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupAddSameLevel")) {
            //新增同级节点
            if (null == selItem.getValue()) {
                AlterDialog.alert(I18nUtil.getLabelContent(ERCode.TREE_NO_OPERATE));
                return;
            }
            PageControlVO vo = pageControlVOMap.get("addSameTree");
            vo.setTreeType(2);
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupUpdataNode")) {
            //新增同级节点
            if (null == selItem.getValue()) {
                AlterDialog.alert(I18nUtil.getLabelContent(ERCode.TREE_NO_OPERATE));
                return;
            }
            PageControlVO vo = pageControlVOMap.get("updateTree");
            vo.setTreeType(3);
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupDelMenu")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("delTree");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick1")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick1");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick2")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick2");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick3")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick3");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick4")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick4");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick5")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick5");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick6")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick6");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("rightPopupClick7")) {
            //新增子节点
            PageControlVO vo = pageControlVOMap.get("rightClick7");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("openAll")) {
            //新增子节点
            TreeUtils.treeOpenOrCloseAction(tree, selItem, 1);
        }
        if (event.getTarget().getId().equalsIgnoreCase("closeAll")) {
            //新增子节点
            TreeUtils.treeOpenOrCloseAction(tree, selItem, 2);
        }
        if (event.getTarget().getId().equalsIgnoreCase("closeMy")) {
            //新增子节点
            TreeUtils.treeOpenOrCloseAction(tree, selItem, 3);
        }
        if (event.getTarget().getId().equalsIgnoreCase("openMy")) {
            //新增子节点
            TreeUtils.treeOpenOrCloseAction(tree, selItem, 4);
        }
        if (event.getName().equalsIgnoreCase(Events.ON_RIGHT_CLICK)) {
            selItem = (Treeitem) event.getTarget();
        }
        if (event.getName().equalsIgnoreCase(Events.ON_CLICK) && event.getTarget() instanceof Treecell) {
            selItem = (Treeitem) event.getTarget().getParent().getParent();
            PageControlVO vo = pageControlVOMap.get("onClickTree");
            TreeUtils.treeAction(this, tree, selItem, vo);
//            AlterDialog.alert(selItem.getId());
        }
        if (event.getName().equalsIgnoreCase(Events.ON_CLICK) && event.getTarget() instanceof Treechildren) {
            selItem = (Treeitem) event.getTarget().getParent();
//            AlterDialog.alert(selItem.getId());
            PageControlVO vo = pageControlVOMap.get("dynamicTree");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        //处理节点移动的
        if (event.getName().equals(Events.ON_DROP)) {
            DropEvent dropEvent = (DropEvent) event;
            if (dropEvent.getDragged() instanceof Treeitem) {
                Treeitem dragItem = (Treeitem) dropEvent.getDragged();
                Treeitem dropItem = (Treeitem) dropEvent.getTarget();
                if (null == dragItem.getValue() || null == dropItem.getValue())
                    return;
                tree.setDragItem(dragItem);
                tree.setDropItem(dropItem);
                PageControlVO vo = pageControlVOMap.get("moveTree");
                TreeUtils.treeAction(this, tree, selItem, vo);
            }
        }
        if (event.getTarget().getId().equalsIgnoreCase("searchTxt") && event.getName().equalsIgnoreCase(Events.ON_OK)) {
            //对节点进行过滤
            TreeUtils.filterTree(tree, searchTxt.getText());

        }

    }

}
