package com.ourway.base.zk.sys.department;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.DepartVo;
import com.ourway.base.zk.models.MenuVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
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
 * <p>方法 MenuAction : <p>
 * <p>说明:菜單管理</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/23 10:18
 * </pre>
 */
public class DepartAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DepartAction.class);
    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Menupopup menupopup;
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;
    private int czlx = 0;//操作类型


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        selItem = rootItem;
        treeChild = (Treechildren) getFellowIfAny("treeChild");

        rootItem.addEventListener(Events.ON_RIGHT_CLICK, this);
        rootItem.addEventListener(Events.ON_CLICK, this);
        menupopup = createMenuRight();
        rootItem.setContext(menupopup);
        rootItem.setSelected(true);
        rootItem.setValue(null);
        bind2Page();
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton copyBtn = (BaseButton) getFellowIfAny("copyBtn");
        BaseButton pasteBtn = (BaseButton) getFellowIfAny("pasteBtn");
        saveBtn.addEventListener(Events.ON_CLICK, this);
        copyBtn.addEventListener(Events.ON_CLICK, this);
        pasteBtn.addEventListener(Events.ON_CLICK, this);
        initTree();
    }

    private Menupopup createMenuRight() {
        //创建每行的鼠标右键

        Menupopup popup = null;
        if (null == getFellowIfAny("rightPopup")) {
            popup = new Menupopup();
            popup.setId("rightPopup");
            popup.setParent(this);
            Menuitem item = new Menuitem("新增子部门");
            item.setId("treeAction");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
            item = new Menuitem("新增同级部门");
            item.setId("rightPopupAddSameLevel");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
            item = new Menuitem("删除部门");
            item.setId("rightPopupDelMenu");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
        } else
            popup = (Menupopup) getFellowIfAny("rightPopup");
        return popup;
    }

    private void setNullPpt() {
        Set<String> set = ppt.keySet();
        for (String s : set) {
            ppt.put(s, null);
        }
    }

    //初始化菜单
    private void initTree() {
        String url = "/departApi/listDepat";
        ResponseMessage mess = JsonPostUtils.executeAPI(null, url, 1, 0);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            List<DepartVo> menuvos = new ArrayList<DepartVo>();
            Map<String, Object> dataMap = (Map<String, Object>) mess.getBean();
            if (null != dataMap.get("records")) {
                List<Map<String, Object>> datas = (List<Map<String, Object>>) dataMap.get("records");
                for (Map<String, Object> map : datas) {
                    menuvos.add(BeanUtil.map2Obj(map, DepartVo.class));
                }
                List<TreeVO> treeVOS = TreeUtils.convertTrees(menuvos);
                TreeUtils.showTree(this, treeChild, treeVOS, true, true, true, true, menupopup);
            }
        }

    }

    private void save() {
        bindAll2Ppt(true);
        String url = "/departApi/saveDepart";
        ResponseMessage mess = JsonPostUtils.executeAPI(ppt, url);
        if (null != mess && mess.getBackCode() == 0) {
            AlterDialog.alert("操作成功");
            ppt = (Map<String, Object>) mess.getBean();
            DepartVo departVo = BeanUtil.map2Obj(ppt, DepartVo.class);
            switch (czlx) {
                case 0:
                    selItem.setValue(departVo);
//                    ((Treecell) selItem.getTreerow().getFirstChild()).setLabel(I18nUtil.getLabelContent(departVo.getIcon()));
                    break;
                case 1:
                    TreeUtils.addTreeItems(selItem, this, departVo, true, true, true, true, menupopup);
                    break;
                case 2:
                    TreeUtils.addTreeItems(selItem.getParentItem(), this, departVo, true, true, true, true, menupopup);
                    break;
                case 3:
                    break;
            }
        }
    }

    private void remove() {
        if (null == selItem || null == selItem.getValue()) {
            AlterDialog.alert("请选择具体节点后删除");
            return;
        }
        DepartVo departVo = (DepartVo) selItem.getValue();
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", departVo.getOwid());
        String url = "/departApi/removeDepat";
        ResponseMessage mess = JsonPostUtils.executeAPI(params, url);
        if (null != mess & mess.getBackCode() == 0) {
            AlterDialog.alert("删除成功");
            selItem.setVisible(false);
            selItem.getParentItem().getChildren().remove(selItem);
            selItem = null;
        }
    }

    private void moveTreeItem(Treeitem sourItem, Treeitem destItem) {
        Component sourComp = sourItem.getParent();
        Component destComp = destItem.getParent();
        if (sourComp.equals(destComp)) {
            //两个节点是在同一个父亲下面
            changeSameLevelOrder(sourItem, destItem);
        } else {
            changeDiffLevelOrder(sourItem, destItem);
        }

    }

    //不同级别节点的移动
    private void changeDiffLevelOrder(Treeitem sourItem, Treeitem destItem) {
        if (null == sourItem.getValue()) {
            return;
        }
        MenuVO sourMenu = (MenuVO) sourItem.getValue();
        //移动到根节点之下
        if (null == destItem.getValue()) {
            sourMenu.setFid(-1);
//            sourMenu.setPx(destItem.getTreechildren().getChildren().size() + 1);
            List<Object> menus = new ArrayList<Object>(2);
            menus.add(sourMenu);
            ResponseMessage mess = JsonPostUtils.executeObjAPI(menus, "/sysMenusApi/updateMenu");
            if (null == mess || mess.getBackCode() != 0) {
                AlterDialog.alert("移动失败");
                return;
            }
            sourItem.setParent(destItem.getTreechildren());
        } else {
            //表示目标节点不为空
            MenuVO destMenu = (MenuVO) destItem.getValue();
            sourMenu.setFid(destMenu.getOwid());
//            if (null != destItem.getTreechildren()) {
////                sourMenu.setPx(destItem.getTreechildren().getChildren().size() + 1);
//            } else
//                sourMenu.setPx(1);
            List<Object> menus = new ArrayList<Object>(2);
            menus.add(sourMenu);
            ResponseMessage mess = JsonPostUtils.executeObjAPI(menus, "/sysMenusApi/updateMenu");
            if (null == mess || mess.getBackCode() != 0) {
                AlterDialog.alert("移动失败");
                return;
            }
            if (null != destItem.getTreechildren()) {
                sourItem.setParent(destItem.getTreechildren());
            } else {
                Treechildren tc = new Treechildren();
                tc.setParent(destItem);
                sourItem.setParent(tc);
            }

        }
    }

    //同级别之间节点的移动
    private void changeSameLevelOrder(Treeitem sourItem, Treeitem destItem) {
        if (null == sourItem.getValue() || null == destItem.getValue()) {
            return;
        }
        MenuVO sourMenu = (MenuVO) sourItem.getValue();
        MenuVO destMenu = (MenuVO) destItem.getValue();
        //更换顺序

//        int _sourceIndex = null==sourMenu.getPx()?0:sourMenu.getPx();
//        int _destIndex = null==destMenu.getPx()?0:destMenu.getPx();
//        sourMenu.setPx(_destIndex);
//        destMenu.setPx(_sourceIndex);
        //调用
        List<Object> menus = new ArrayList<Object>(2);
        menus.add(sourMenu);
        menus.add(destMenu);
        ResponseMessage mess = JsonPostUtils.executeObjAPI(menus, "/sysMenusApi/updateMenu");
        if (null == mess || mess.getBackCode() != 0) {
            AlterDialog.alert("移动失败");
            return;
        }
        //更换两个节点之间的数据
        sourItem.setLabel(destMenu.getName());
        sourItem.setValue(destMenu);
        destItem.setLabel(sourMenu.getName());
        destItem.setValue(sourMenu);
        Treechildren _sourChildren;
        Treechildren _destChildren;
        _sourChildren = sourItem.getTreechildren();
        _destChildren = destItem.getTreechildren();
        if (null != _sourChildren)
            _sourChildren.setParent(destItem);
        if (null != _destChildren)
            _destChildren.setParent(sourItem);


    }


    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof Treeitem) {
            if (event.getName().equals(Events.ON_CLICK)) {
                //单击事件
                Treeitem item = (Treeitem) comp;
                if (null == item.getValue()) {
                    ppt.put("name", "部门管理");
                    ppt.put("fid", -1);
                    ppt.put("pageId", null);
                    ppt.put("path", "-1/");
                } else {
                    DepartVo memuvo = (DepartVo) item.getValue();
                    BeanUtil.obj2Map(memuvo, ppt);
                }
                selItem = item;
                czlx = 0;
                bind2Page();
            }
            if (event.getName().equals(Events.ON_RIGHT_CLICK)) {
                selItem = (Treeitem) comp;
            }
            //如果是移动trieeite
            if (event.getName().equals(Events.ON_DROP)) {
                DropEvent dropEvent = (DropEvent) event;
                if (dropEvent.getDragged() instanceof Treeitem) {
                    Treeitem dragItem = (Treeitem) dropEvent.getDragged();
                    Treeitem dropItem = (Treeitem) dropEvent.getTarget();
                    moveTreeItem(dragItem, dropItem);
                }

            }
        }
        if (comp instanceof BaseButton) {
            if (event.getName().equals(Events.ON_CLICK)) {
                if (comp.getId().equals("saveBtn")) {
                    save();
                }
                if (comp.getId().equals("copyBtn")) {
                    copyWin();

                }
                if (comp.getId().equals("pasteBtn")) {
                    pasteWin();
                    ppt.put("owid", "");
                }
            }
        }
        if (comp instanceof Menuitem) {
            if (comp.getId().equals("treeAction")) {
                setNullPpt();
                czlx = 1;
                if (null == selItem || null == selItem.getValue()) {
                    //表示根节点
                    ppt.put("fid", -1);
                    ppt.put("path", "-1/");
                    ppt.put("type", 0);
                    ppt.put("pageId","");
                    ppt.put("px", rootItem.getTreechildren().getChildren().size() + 1);
                } else {
                    DepartVo menuVO = selItem.getValue();
                    ppt.put("fid", menuVO.getOwid());
                    ppt.put("path", menuVO.getPath() + "/");
                    ppt.put("type", 0);
                    ppt.put("pageId","");
                    if (null == selItem.getTreechildren())
                        ppt.put("px", 1);
                    else
                        ppt.put("px", selItem.getTreechildren().getChildren().size() + 1);
                }
                bind2Page();
            }
            if (comp.getId().equals("rightPopupAddSameLevel")) {
                setNullPpt();
                if (null == selItem.getValue()) {
                    //表示根节点
                    ppt.put("fid", -1);
                    ppt.put("path", "-1/");
                    ppt.put("type", 0);
                    ppt.put("pageId","");
                    ppt.put("px", rootItem.getTreechildren().getChildren().size() + 1);
                } else {
                    DepartVo departVo = selItem.getValue();
                    ppt.put("fid", departVo.getFid());
                    ppt.put("path", departVo.getPath().substring(0, departVo.getPath().lastIndexOf("/")) + "/");
                    ppt.put("type", 0);
                    ppt.put("pageId","");
                    ppt.put("px", selItem.getParentItem().getTreechildren().getChildren().size() + 1);
                }
                czlx = 2;
                bind2Page();
            }
            if (comp.getId().equals("rightPopupDelMenu")) {
                if (AlterDialog.corfirm("确定需要删除所选节点及下属节点")) {
                    setNullPpt();
                    bind2Page();
                    remove();
                    czlx = 3;
                }
            }
        }
    }

}

