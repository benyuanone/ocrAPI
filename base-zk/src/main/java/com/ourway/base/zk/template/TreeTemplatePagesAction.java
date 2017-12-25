package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

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
public class TreeTemplatePagesAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TreeTemplatePagesAction.class);
    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Treecell rootCell;
    private Menupopup menupopup;
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;
    private Div conditionGrid;
    private BaseGrid dataList;
    private Div buttonGrid;
    private Integer totalRows;//多少行查询条件
    private BaseTextbox searchTxt;//查询条件

    private Map<String, PageControlVO> pageControlVOMap = new HashMap<String, PageControlVO>();
    /*临时变量，用来存储控件组合的时候问题*/


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 140) + "px");
        tree.setHeight((ZKSessionUtils.getScreenHeight() - 210) + "px");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        rootItem.setValue(null);
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        rootCell = (Treecell) getFellowIfAny("rootCell");
        searchTxt = (BaseTextbox) getFellowIfAny("searchTxt");
        searchTxt.addEventListener(Events.ON_OK, this);


        buttonGrid = (Div) getFellowIfAny("buttonGrid");
        conditionGrid = (Div) getFellowIfAny("conditionGrid");
        dataList = (BaseGrid) getFellowIfAny("dataList");
        initTree();
        loadTreeData();//加载树
        initConditionGrid();
        initButtonGrid();
        initDataList();
        this.addEventListener(Events.ON_CTRL_KEY,this);
        reloadButton();
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

            getGridUtils().displayDataWithTextbox(dataList, dataList.getResult(), this, false);
//            dataList.display();
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
