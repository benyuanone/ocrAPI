package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
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
import com.ourway.base.zk.utils.treeutils.TreeMoveInt;
import com.ourway.base.zk.utils.treeutils.impl.CommonTreeMoveIntImpl;
import com.ourway.base.zk.utils.treeutils.impl.MenuTreeMoveIntImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
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
public class TreeTemplateDivAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TreeTemplateDivAction.class);
    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Treecell rootCell;
    private Menupopup menupopup;
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;
    private Div operateGrid;
    private Integer totalRows;//多少行查询条件
    //    private BaseTextbox searchTxt;//查询条件
    private Menupopup rightMenuPopup;//移动过去的时候进行弹出

    private Map<String, PageControlVO> pageControlVOMap = new HashMap<String, PageControlVO>();
    /*临时变量，用来存储控件组合的时候问题*/

    private Treeitem dragItem = null; //移动前
    private Treeitem dropItem = null;//移动后

    /*主表填写区域*/
    private Div mainDiv;
    /*子表更新区*/
    PageVO pageVO =null;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        pageVO = PageDataUtil.detailPageByCa(getPageCA());
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - BaseConstants.BORDER_LAYOUT_HEIGHT) + "px");
        tree.setHeight((ZKSessionUtils.getScreenHeight() - BaseConstants.BORDER_LAYOUT_HEIGHT_TREE) + "px");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        rootItem.setValue(null);
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        rootCell = (Treecell) getFellowIfAny("rootCell");
//        searchTxt = (BaseTextbox) getFellowIfAny("searchTxt");
//        searchTxt.addEventListener(Events.ON_OK, this);
        operateGrid = (Div) getFellowIfAny("operateGrid");
        mainDiv = (Div) getFellowIfAny("mainDiv");
        System.out.println("==========" + getWindowType());
        initTree();
        loadTreeData();//加载树
        initOperateGrid();
        initMainTableGrid();
        initPage(event);
        reloadButton();
        //初始化移动的效果
        initRightMenu();
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
    //移动过去的时候，弹出menupopup
    private void initRightMenu() {
        rightMenuPopup = new Menupopup();
        rightMenuPopup.setParent(this);
        Menuitem item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_MOVE_SUBITEM));
        item.setId("subItemAction");
        item.addEventListener(Events.ON_CLICK, this);
        item.setParent(rightMenuPopup);
        item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_MOVE_ABOVEITEM));
        item.setId("aboveItemAction");
        item.addEventListener(Events.ON_CLICK, this);
        item.setParent(rightMenuPopup);
        item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_MOVE_BELOWITEM));
        item.setId("belowItemAction");
        item.addEventListener(Events.ON_CLICK, this);
        item.setParent(rightMenuPopup);
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
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "leftTreeDiv");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            mainDiv.setVisible(false);
            return;
        }
        mainDiv.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            List<String> setList = TemplateUtils.doGenOrderForSet(data.getLayOutComponents());
            for (String s : setList) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(s);
                if (null == subComps || subComps.size() <= 0)
                    continue;
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS + " form-group");
                div.setId("mainDiv_" + s);
                div.setParent(mainDiv);
                getTemplateUtils().doRowComponents(subComps, div, data, this);
            }
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

    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        Component comp = event.getTarget();
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
            //修改移动12-04
            if (dropEvent.getDragged() instanceof Treeitem) {
                dragItem = (Treeitem) dropEvent.getDragged();
                dropItem = (Treeitem) dropEvent.getTarget();
                rightMenuPopup.open(dropItem);//打开移动menu
            }
//            if (dropEvent.getDragged() instanceof Treeitem) {
//                Treeitem dragItem = (Treeitem) dropEvent.getDragged();
//                Treeitem dropItem = (Treeitem) dropEvent.getTarget();
//                if (null == dragItem.getValue() || null == dropItem.getValue())
//                    return;
//                tree.setDragItem(dragItem);
//                tree.setDropItem(dropItem);
//                PageControlVO vo = pageControlVOMap.get("moveTree");
//                TreeUtils.treeAction(this, tree, selItem, vo);
//            }
        }
        if (comp instanceof Menuitem) {
            if (comp.getId().equalsIgnoreCase("subItemAction")) {
                //移动过去变为子节点
                PageControlVO vo = pageControlVOMap.get("moveTree");
                TreeMoveInt treeMoveInt = new CommonTreeMoveIntImpl();
                treeMoveInt.moveToSubTree(dragItem, dropItem,vo);
                return;
            }
            if (comp.getId().equalsIgnoreCase("aboveItemAction")) {
                //移动过去变为子节点
                PageControlVO vo = pageControlVOMap.get("moveTree");
                TreeMoveInt treeMoveInt = new CommonTreeMoveIntImpl();
                treeMoveInt.moveToPreTree(dragItem, dropItem,vo);
                return;
            }
            if (comp.getId().equalsIgnoreCase("belowItemAction")) {
                //移动过去变为子节点
                PageControlVO vo = pageControlVOMap.get("moveTree");
                TreeMoveInt treeMoveInt = new CommonTreeMoveIntImpl();
                treeMoveInt.moveToAfterTree(dragItem, dropItem,vo);
                return;
            }
        }

        if (event.getTarget().getId().equalsIgnoreCase("searchTxt") && event.getName().equalsIgnoreCase(Events.ON_OK)) {
            //对节点进行过滤
//            TreeUtils.filterTree(tree, searchTxt.getText());

        }

    }

}
