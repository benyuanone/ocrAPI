package com.ourway.base.zk.utils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/6.
 */
public class TreeUtils {

    //把menu转换为树节点
    public static List<TreeVO> convertTree(List<MenuVO> menuvos) {
        List<TreeVO> _vos = new ArrayList<TreeVO>();
        for (MenuVO vo : menuvos) {
            TreeVO tvo = new TreeVO();
            BeanUtil.copyBean(vo, tvo, "owid", "fid", "path", "name", "link", "icon", "px", "cc");
            tvo.setOwid(vo.getOwid());
            tvo.setBean(vo);
            _vos.add(tvo);
        }
        return getTrees(_vos);
    }

    //形成树
    public static List<TreeVO> convertAreaTree(List<AreaVO> areavos) {
        List<TreeVO> _vos = new ArrayList<TreeVO>();
        for (AreaVO vo : areavos) {
            TreeVO tvo = new TreeVO();
            BeanUtil.copyBean(vo, tvo, "fid", "path");
            tvo.setName(vo.getAreaName());
            tvo.setOwid(vo.getOwid());
            tvo.setBean(vo);
            _vos.add(tvo);
        }
        return getTrees(_vos);
    }

    //部门变成一棵树
    public static List<TreeVO> convertTrees(List<DepartVo> menuvos) {
        List<TreeVO> _vos = new ArrayList<TreeVO>();
        for (DepartVo vo : menuvos) {
            TreeVO tvo = new TreeVO();
            BeanUtil.copyBean(vo, tvo, "fid", "path", "path", "name", "link", "icon");
            tvo.setOwid(vo.getOwid());
            tvo.setBean(vo);
            _vos.add(tvo);
        }
        return getTrees(_vos);
    }

    //Map转为树
    public static List<TreeVO> mapConvertTree(List<Map> params) {
        List<TreeVO> _vos = new ArrayList<TreeVO>();
        for (Map vo : params) {
            TreeVO tvo = new TreeVO();
            BeanUtil.copyBean(vo, tvo, "fid", "path", "name", "link", "icon");
            tvo.setOwid(Integer.parseInt(vo.get("owid").toString()));
            tvo.setBean(vo);
            _vos.add(tvo);
        }
        return getTrees(_vos);
    }


    /**
     * <p>方法:getTrees 传入树形的节点，转为整个树 </p>
     * <ul>
     * <li> @param datas 树形节点</li>
     * <li>@return java.util.List<com.ourway.base.zk.models.TreeVO<org.apache.poi.ss.formula.functions.T>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/6 15:30  </li>
     * </ul>
     *
     * @param datas
     */
    public static List<TreeVO> getTrees(List<TreeVO> datas) {
        List<TreeVO> list = new ArrayList<TreeVO>();
        for (TreeVO treeNode : datas) {
            if (null != treeNode.getFid() && treeNode.getFid().intValue() == -1) {
                convertToTree(datas, treeNode);
                list.add(treeNode);
            } else
                continue;
        }
        return list;
    }

    public static void convertToTree(List<TreeVO> datas, TreeVO node) {
        for (TreeVO treeNode : datas) {
            if (null == treeNode.getFid())
                continue;
            if (node.getOwid().intValue() == treeNode.getFid().intValue()) {
                if (null == node.getSubTrees())
                    node.setSubTrees(new ArrayList<TreeVO>());
                node.setLeaf(true);
                node.getSubTrees().add(treeNode);
                convertToTree(datas, treeNode);
            } else
                continue;
        }
    }

    /**
     * <p>方法:addTreeItem 新加菜单节点 </p>
     * <ul>
     * <li> @param selItem TODO</li>
     * <li> @param win TODO</li>
     * <li> @param item TODO</li>
     * <li> @param menuVO TODO</li>
     * <li> @param rightClick TODO</li>
     * <li> @param click TODO</li>
     * <li> @param openCloseFlag TODO</li>
     * <li> @param moved TODO</li>
     * <li> @param popup TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/23 22:24  </li>
     * </ul>
     */
    public static void addTreeItem(Treeitem selItem, BaseWindow win, TreeVO treeVO, boolean rightClick, boolean click, boolean openCloseFlag, boolean moved, Menupopup popup) {
        Treechildren treechildren = selItem.getTreechildren();
        if (null == treechildren) {
            treechildren = new Treechildren();
            treechildren.setParent(selItem);
        }
        Treeitem newItem = new Treeitem();
        newItem.setValue(treeVO);
        newItem.setParent(treechildren);
        Treerow row = new Treerow();
        row.setParent(newItem);
        Treecell cell = new Treecell();
        cell.setLabel(I18nUtil.getLabelContent(treeVO.getIcon()));
        cell.setParent(row);
        if (rightClick)
            newItem.addEventListener(Events.ON_RIGHT_CLICK, win);
        if (click)
            newItem.addEventListener(Events.ON_CLICK, win);
        if (null != popup)
            newItem.setContext(popup);
        if (moved) {
            newItem.setDraggable("true");
            newItem.setDroppable("true");
            newItem.addEventListener(Events.ON_DROP, win);
        }
    }


    public static void addTreeItem(Treeitem selItem, BaseWindow win, AreaVO areaVOVO, boolean rightClick, boolean click, boolean openCloseFlag, boolean moved, Menupopup popup) {
        Treechildren treechildren = selItem.getTreechildren();
        if (null == treechildren) {
            treechildren = new Treechildren();
            treechildren.setParent(selItem);
        }
        Treeitem newItem = new Treeitem();
        newItem.setValue(areaVOVO);
        newItem.setParent(treechildren);
        Treerow row = new Treerow();
        row.setParent(newItem);
        Treecell cell = new Treecell();
        cell.setLabel(I18nUtil.getLabelContent(areaVOVO.getAreaName()));
        cell.setParent(row);
        if (rightClick)
            newItem.addEventListener(Events.ON_RIGHT_CLICK, win);
        if (click)
            newItem.addEventListener(Events.ON_CLICK, win);
        if (null != popup)
            newItem.setContext(popup);
        if (moved) {
            newItem.setDraggable("true");
            newItem.setDroppable("true");
            newItem.addEventListener(Events.ON_DROP, win);
        }
    }

    public static void addTreeItems(Treeitem selItem, BaseWindow win, DepartVo departVo, boolean rightClick, boolean click, boolean openCloseFlag, boolean moved, Menupopup popup) {
        Treechildren treechildren = selItem.getTreechildren();
        if (null == treechildren) {
            treechildren = new Treechildren();
            treechildren.setParent(selItem);
        }
        Treeitem newItem = new Treeitem();
        newItem.setValue(departVo);
        newItem.setParent(treechildren);
        Treerow row = new Treerow();
        row.setParent(newItem);
        Treecell cell = new Treecell();
//        cell.setLabel(I18nUtil.getLabelContent(departVo.getIcon()));
        cell.setParent(row);
        if (rightClick)
            newItem.addEventListener(Events.ON_RIGHT_CLICK, win);
        if (click)
            newItem.addEventListener(Events.ON_CLICK, win);
        if (null != popup)
            newItem.setContext(popup);
        if (moved) {
            newItem.setDraggable("true");
            newItem.setDroppable("true");
            newItem.addEventListener(Events.ON_DROP, win);
        }
    }

    public static void showTree(BaseWindow win, Treechildren content, List<TreeVO> tree, boolean rightClick, boolean click, boolean openCloseFlag, boolean moved, Menupopup popup) {
        content.getChildren().clear();
        for (TreeVO treeNode : tree) {
            Treeitem item = new Treeitem();
//            item.setValue(treeNode.getBean());
            item.setValue(treeNode);
            item.setParent(content);
            item.setId("treeItem_" + treeNode.getOwid());//增加一个主键

            Treerow row = new Treerow();
            row.setParent(item);
            Treecell cell = new Treecell();
            if (null != ZKSessionUtils.getZkUser() && ZKSessionUtils.getZkUser().getEmpId().equalsIgnoreCase("root"))
                cell.setLabel(treeNode.getName());
            else
                cell.setLabel(I18nUtil.getLabelContent(treeNode.getIcon()));
            cell.setParent(row);
            // 判断是否有叶子
            if (null != treeNode.getSubTrees() && treeNode.getSubTrees().size() > 0) {
                item.setOpen(openCloseFlag);
                Treechildren children = new Treechildren();
                children.setParent(item);
                showTree(win, children, treeNode.getSubTrees(), rightClick, click, openCloseFlag, moved, popup);
            }
//            if (rightClick)
            item.addEventListener(Events.ON_RIGHT_CLICK, win);
            if (click)
                item.addEventListener(Events.ON_CLICK, win);
            if (null != popup)
                item.setContext(popup);
            if (moved) {
                item.setDraggable("true");
                item.setDroppable("true");
                item.addEventListener(Events.ON_DROP, win);
            }

        }
    }

    //处理树菜单右键
    public static void doCreateMenuRight(BaseTree tree, BaseWindow window, Map<String, PageControlVO> pageControlVOMap) {
        //创建每行的鼠标右键

        Menupopup popup = new Menupopup();
        popup.setId("rightPopup");
        popup.setParent(window);
        Menu menu = new Menu();
        menu.setParent(popup);
        menu.setLabel(I18nUtil.getLabelContent(ERCode.TREE_NODE_OPERATE));
        Menupopup menupopup = new Menupopup();
        menupopup.setParent(menu);
        Menuitem item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_ALLNODE_OPEN));
        item.setId("openAll");
        item.addEventListener(Events.ON_CLICK, window);
        item.setParent(menupopup);
        item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_ALLNODE_CLOSE));
        item.setId("closeAll");
        item.addEventListener(Events.ON_CLICK, window);
        item.setParent(menupopup);
        item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_NODE_CLOSE));
        item.setId("closeMy");
        item.addEventListener(Events.ON_CLICK, window);
        item.setParent(menupopup);
        item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_NODE_OPEN));
        item.setId("openMy");
        item.addEventListener(Events.ON_CLICK, window);
        item.setParent(menupopup);
        if (null != tree.getAttribute("addTree") && (Boolean) tree.getAttribute("addTree")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("addTree").getLayoutComponent().getKjConstraintKey()));
            item.setId("treeAction");
            item.setAttribute("label", pageControlVOMap.get("addTree").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("addSameTree") && (Boolean) tree.getAttribute("addSameTree")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("addSameTree").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupAddSameLevel");
            item.setAttribute("label", pageControlVOMap.get("addSameTree").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("updateTree") && (Boolean) tree.getAttribute("updateTree")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("updateTree").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupUpdataNode");
            item.setAttribute("label", pageControlVOMap.get("updateTree").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("delTree") && (Boolean) tree.getAttribute("delTree")) {
            item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_REMOVE_NODE));
            item.setId("rightPopupDelMenu");
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick1") && (Boolean) tree.getAttribute("rightClick1")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick1").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick1");
            item.setAttribute("label", pageControlVOMap.get("rightClick1").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick2") && (Boolean) tree.getAttribute("rightClick2")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick2").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick2");
            item.setAttribute("label", pageControlVOMap.get("rightClick2").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick3") && (Boolean) tree.getAttribute("rightClick3")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick3").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick3");
            item.setAttribute("label", pageControlVOMap.get("rightClick3").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick4") && (Boolean) tree.getAttribute("rightClick4")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick4").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick4");
            item.setAttribute("label", pageControlVOMap.get("rightClick4").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick5") && (Boolean) tree.getAttribute("rightClick5")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick5").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick5");
            item.setAttribute("label", pageControlVOMap.get("rightClick5").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick6") && (Boolean) tree.getAttribute("rightClick6")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick6").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick6");
            item.setAttribute("label", pageControlVOMap.get("rightClick6").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        if (null != tree.getAttribute("rightClick7") && (Boolean) tree.getAttribute("rightClick7")) {
            item = new Menuitem(I18nUtil.getLabelContent(pageControlVOMap.get("rightClick7").getLayoutComponent().getKjConstraintKey()));
            item.setId("rightPopupClick7");
            item.setAttribute("label", pageControlVOMap.get("rightClick7").getLayoutComponent().getKjConstraintKey());
            item.addEventListener(Events.ON_CLICK, window);
            item.setParent(popup);
        }
        tree.setRightPopup(popup);
    }

    public static void treeAction(BaseWindow window, BaseTree tree, Treeitem treeitem, PageControlVO pageControlVO) {
        if (com.ourway.base.utils.TextUtils.isEmpty(pageControlVO.getLayoutComponent().getEvnetFormula()))
            return;
        try {
            Object _obj = Class.forName(pageControlVO.getLayoutComponent().getEvnetFormula()).newInstance();
            TreeListinerSer compAction = (TreeListinerSer) _obj;
            compAction.doAction(window, null, tree, treeitem, pageControlVO);
        } catch (Exception e) {
            e.printStackTrace();
            AlterDialog.alert(pageControlVO.getLayoutComponent().getEvnetFormula() + "执行错误");
        }
    }

    //控制节点关闭
    public static void treeOpenOrCloseAction(BaseTree tree, Treeitem treeitem, int type) {
        switch (type) {
            case 1:
                //openAll
                Collection<Treeitem> menuitems = tree.getItems();
                for (Treeitem item : menuitems) {
                    item.setOpen(true);
                }
                break;
            case 2:
                //openAll
                menuitems = tree.getItems();
                for (Treeitem item : menuitems) {
                    item.setOpen(false);
                }
                break;
            case 3:
                treeitem.setOpen(false);
                break;
            case 4:
                treeitem.setOpen(true);
                break;
        }
    }

    //增加子节点
    public static void doCreateSubTreeItem(TreeVO treeVO, Treeitem treeitem, BaseTree tree, BaseWindow window) {
        if (null == treeitem.getTreechildren()) {
            Treechildren tc = new Treechildren();
            tc.setParent(treeitem);
        }
        Treeitem item = doCreateItem(treeVO, tree, window);
//        treeVO.setPx(treeitem.getTreechildren().getChildren().size() + 1);
        item.setValue(treeVO);
        treeitem.getTreechildren().appendChild(item);


    }

    public static void doCreateSameLevelTreeItem(TreeVO treeVO, Treeitem treeitem, BaseTree tree, BaseWindow window) {
        Treeitem item = doCreateItem(treeVO, tree, window);
//        treeVO.setPx(((Treechildren) treeitem.getParent()).getChildren().size() + 1);
        item.setValue(treeVO);
        ((Treechildren) treeitem.getParent()).appendChild(item);
    }

    public static Treeitem doCreateItem(TreeVO treeVO, BaseTree tree, BaseWindow window) {
        Treeitem treeitem = new Treeitem();
        Treerow row = new Treerow();
        row.setParent(treeitem);
        Treecell cell = new Treecell();
        cell.setLabel(treeVO.getName());
        cell.setParent(row);
        //是否有oclick事件
        if (null != tree.getAttribute("onClickTree") && (Boolean) tree.getAttribute("onClickTree")) {
            cell.addEventListener(Events.ON_CLICK, window);
        }
        if (null != tree.getAttribute("rightClick") && (Boolean) tree.getAttribute("rightClick")) {
            treeitem.addEventListener(Events.ON_RIGHT_CLICK, window);
            treeitem.setContext(tree.getRightPopup());
        }
        if (null != tree.getAttribute("moveTree") && (Boolean) tree.getAttribute("moveTree")) {
            treeitem.setDraggable("true");
            treeitem.setDroppable("true");
            treeitem.addEventListener(Events.ON_DROP, window);
        }
        return treeitem;
    }

    //显示多选框的树
    public void showMultipleTree(BaseWindow window, Treeitem selItem, BaseTree tree, List<TreeVO> datas, List<String> selNodes) {
        Treechildren tc = null;
        if (null == selItem) {
            tc = doCreateTreeChildren(tree);
            //表示树初始化，初始化多选的
            tree.setMultiple(true);
            tree.setCheckmark(true);
        } else {
            tc = doCreateTreeChildren(selItem);
        }
        tc.getChildren().clear();//清空
        if (null != datas && datas.size() > 0) {
            for (TreeVO treeNode : datas) {
                Treeitem item = doCreateTreeitem(tc, treeNode);
                item.setCheckable(true);

                item.addEventListener(Events.ON_CLICK, new TreeChecked());
                if (null != selNodes && selNodes.size() > 0 && selNodes.contains(treeNode.getOwid()))
                    item.setSelected(true);
                // 判断是否有叶子
                if (null != treeNode.getSubTrees() && treeNode.getSubTrees().size() > 0) {
                    item.setOpen(false);
                    //递归
                    showMultipleTree(window, item, tree, treeNode.getSubTrees(), selNodes);
                }
            }
        }
    }


    //复选框选中的事件
    private class TreeChecked implements EventListener {
        public void onEvent(Event arg0) throws Exception {
            Treeitem it = (Treeitem) arg0.getTarget();
            checkChild(it);
            checkParent(it);
        }
    }

    //判断子节点是否选择
    public void checkChild(Treeitem item) {
        if (item.getTreechildren() == null) {
            return;
        } else {
            Treechildren children = item.getTreechildren();
            for (Object obj : children.getItems()) {
                Treeitem childItem = (Treeitem) obj;
                childItem.setSelected(item.isSelected());
                checkChild(childItem);
            }
        }
    }

    public void checkParent(Treeitem item) {
        if (item.getParent().getParent() instanceof Tree) {
            return;
        } else {
            Treeitem parentItem = (Treeitem) item.getParent().getParent();
            if (!item.isSelected()) {
                Treechildren children = parentItem.getTreechildren();
                boolean isCheck = false;
                for (Object obj : children.getItems()) {
                    Treeitem childItem = (Treeitem) obj;
                    if (childItem.isSelected()) {
                        isCheck = true;
                        break;
                    }
                }
                parentItem.setSelected(isCheck);
            } else {
                parentItem.setSelected(true);
            }
            checkParent(parentItem);
        }
    }

    //创建单个treeItem
    private static Treeitem doCreateTreeitem(Treechildren tc, TreeVO treeNode) {
        Treeitem item = new Treeitem();
        item.setValue(treeNode);
        item.setParent(tc);
        item.setId("treeItem_" + treeNode.getOwid());
        Treerow row = new Treerow();
        row.setParent(item);
        Treecell cell = new Treecell();
        cell.setLabel(treeNode.getName());
        cell.setParent(row);
        return item;
    }

    //获取整个树的treechildren
    private static Treechildren doCreateTreeChildren(BaseTree tree) {
        Treechildren tc = tree.getTreechildren();
        if (null == tc) {
            tc = new Treechildren();
            tc.setParent(tree);
        }
        return tc;
    }

    //    获取整个节点的treechildren
    private static Treechildren doCreateTreeChildren(Treeitem treeItem) {
        Treechildren tc = treeItem.getTreechildren();
        if (null == tc) {
            tc = new Treechildren();
            tc.setParent(treeItem);
        }
        return tc;
    }

    //动态展示一棵树
    public static void showTree(BaseWindow win, Treeitem selItem, BaseTree tree, List<TreeVO> datas, Treeitem selItemBefore) {
        try {
            Treechildren treechildren = selItem.getTreechildren();
            treechildren.getChildren().clear();

            //如果动态的，处理点击事件
            if (null != tree.getAttribute("dynamicClick") && (Boolean) tree.getAttribute("dynamicClick")) {
                treechildren.addEventListener(Events.ON_CLICK, win);
            }
            if (null != datas && datas.size() > 0)
                for (TreeVO treeNode : datas) {
                    Treeitem item = new Treeitem();
                    item.setValue(treeNode);
                    item.setParent(treechildren);
                    item.setId("treeItem_" + treeNode.getOwid());
                    //根据id判断是否选中
                    if (!TextUtils.isEmpty(selItemBefore) && !TextUtils.isEmpty(selItemBefore.getId()) && selItemBefore.getId().equalsIgnoreCase(item.getId()))
                        item.setSelected(true);

                    Treerow row = new Treerow();
                    row.setParent(item);
                    Treecell cell = new Treecell();
                    cell.setLabel(treeNode.getName());
                    cell.setParent(row);
                    //是否有oclick事件
                    if (null != tree.getAttribute("onClickTree") && (Boolean) tree.getAttribute("onClickTree")) {
                        cell.addEventListener(Events.ON_CLICK, win);
                    }
                    if (null != tree.getAttribute("onDbClickTree") && (Boolean) tree.getAttribute("onDbClickTree")) {
                        item.addEventListener(Events.ON_DOUBLE_CLICK, win);
                    }
                    // 判断是否有叶子
                    if (null != treeNode.getSubTrees() && treeNode.getSubTrees().size() > 0) {
                        Treechildren children = new Treechildren();
                        children.setParent(item);
                        if (!item.isOpen())
                            item.setOpen(true);
                        if (null != tree.getAttribute("dynamicClick") && (Boolean) tree.getAttribute("dynamicClick")) {
                            treechildren.addEventListener(Events.ON_CLICK, win);
                        }
                        showTree(win, item, tree, treeNode.getSubTrees(), null);
                    } else if (null != treeNode.getChildLen() && treeNode.getChildLen() > 0) {
                        Treechildren children = new Treechildren();
                        children.setParent(item);
                        if (null != tree.getAttribute("dynamicClick") && (Boolean) tree.getAttribute("dynamicClick")) {
                            treechildren.addEventListener(Events.ON_CLICK, win);
                        }
                    }
//                if (null != tree.getAttribute("rightClick") && (Boolean) tree.getAttribute("rightClick")) {
                    item.addEventListener(Events.ON_RIGHT_CLICK, win);
                    item.setContext(tree.getRightPopup(treeNode.getDisableLabel()));
//                }
                    if (null != tree.getAttribute("moveTree") && (Boolean) tree.getAttribute("moveTree")) {
                        item.setDraggable("true");
                        item.setDroppable("true");
                        item.addEventListener(Events.ON_DROP, win);
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSubTree(BaseWindow win, Treeitem selItem, BaseTree tree, List<TreeVO> datas) {
        Treechildren treechildren = selItem.getTreechildren();
        treechildren.getChildren().clear();

        //如果动态的，处理点击事件
        if (null != tree.getAttribute("dynamicClick") && (Boolean) tree.getAttribute("dynamicClick")) {
            treechildren.addEventListener(Events.ON_CLICK, win);
        }
        if (null != datas && datas.size() > 0)
            for (TreeVO treeNode : datas) {
                Treeitem item = new Treeitem();
                item.setValue(treeNode);
                item.setParent(treechildren);

                Treerow row = new Treerow();
                row.setParent(item);
                Treecell cell = new Treecell();
                cell.setLabel(treeNode.getName());
                cell.setParent(row);
                //是否有oclick事件
                if (null != tree.getAttribute("onClickTree") && (Boolean) tree.getAttribute("onClickTree")) {
                    cell.addEventListener(Events.ON_CLICK, win);
                }
                // 判断是否有叶子
                if (null != treeNode.getSubTrees() && treeNode.getSubTrees().size() > 0) {
                    item.setOpen(true);
                    Treechildren children = new Treechildren();
                    children.setParent(item);
                    if (null != tree.getAttribute("dynamicClick") && (Boolean) tree.getAttribute("dynamicClick")) {
                        treechildren.addEventListener(Events.ON_CLICK, win);
                    }
                    showTree(win, item, tree, treeNode.getSubTrees(), null);
                } else if (null != treeNode.getChildLen() && treeNode.getChildLen() > 0) {
                    Treechildren children = new Treechildren();
                    children.setParent(item);
                    if (null != tree.getAttribute("dynamicClick") && (Boolean) tree.getAttribute("dynamicClick")) {
                        treechildren.addEventListener(Events.ON_CLICK, win);
                    }
                }
                if (null != tree.getAttribute("rightClick") && (Boolean) tree.getAttribute("rightClick")) {
                    item.addEventListener(Events.ON_RIGHT_CLICK, win);
                    item.setContext(tree.getRightPopup());
                }
                if (null != tree.getAttribute("moveTree") && (Boolean) tree.getAttribute("moveTree")) {
                    item.setDraggable("true");
                    item.setDroppable("true");
                    item.addEventListener(Events.ON_DROP, win);
                }
            }
    }


    public static void filterTree(BaseTree tree, String name) {
//        tree
        List<Component> comps = tree.getChildren();
        for (Component comp : comps) {

        }
    }


}
