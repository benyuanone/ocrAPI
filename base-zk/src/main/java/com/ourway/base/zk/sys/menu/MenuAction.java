package com.ourway.base.zk.sys.menu;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.bandbox.PageBandbox;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.MenuVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.treeutils.NodeUtils;
import com.ourway.base.zk.utils.treeutils.TreeMoveInt;
import com.ourway.base.zk.utils.treeutils.impl.MenuTreeMoveIntImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
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
public class MenuAction extends BaseWindow {
    protected Log info = LogFactory.getLog(MenuAction.class);

    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Menupopup menupopup;
    private Menupopup rightMenuPopup;//移动过去的时候进行弹出
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;
    private int czlx = 0;//操作类型

    private Treeitem dragItem = null; //移动前
    private Treeitem dropItem = null;//移动后


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        selItem = rootItem;
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 115) + "px");
        tree.setHeight((ZKSessionUtils.getScreenHeight() - 145) + "px");
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
        initRightMenu();
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

    //鼠标右键菜单，用户每个项目的店家右键
    private Menupopup createMenuRight() {
        //创建每行的鼠标右键
        Menupopup popup = null;
        if (null == getFellowIfAny("rightPopup")) {
            popup = new Menupopup();
            popup.setId("rightPopup");
            popup.setParent(this);
            Menuitem item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_NEW_SUBITEM));
            item.setId("addSubItem");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
            item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_NEW_SAMELEVELITEM));
            item.setId("addSameLeveItem");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
            item = new Menuitem(I18nUtil.getLabelContent(ERCode.TREE_REMOVE_ITEM));
            item.setId("removeItem");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
        } else
            popup = (Menupopup) getFellowIfAny("rightPopup");
        return popup;
    }

    //把内容置换为空
    private void setNullPpt() {
        Set<String> set = ppt.keySet();
        for (String s : set) {
            ppt.put(s, null);
        }
        //把pagebandbox设置为空
        PageBandbox baseInfo_pageId = (PageBandbox) getFellowIfAny("baseInfo_pageId");
        baseInfo_pageId.setNull();
    }

    //初始化菜单，展示树形
    private void initTree() {
        String url = "/sysMenusApi/listMenus";
        ResponseMessage mess = JsonPostUtils.executeAPI(null, url, 1, 0);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            List<MenuVO> menuvos = new ArrayList<MenuVO>();
            Map<String, Object> dataMap = (Map<String, Object>) mess.getBean();
            if (null != dataMap.get("records")) {
                List<Map<String, Object>> datas = (List<Map<String, Object>>) dataMap.get("records");
                for (Map<String, Object> map : datas) {
                    menuvos.add(BeanUtil.map2Obj(map, MenuVO.class));
                }
                List<TreeVO> treeVOS = TreeUtils.convertTree(menuvos);
                TreeUtils.showTree(this, treeChild, treeVOS, true, true, true, true, menupopup);
            }
        }

    }

    private void displayNodeDetail(Treeitem item) {
        setNullPpt();
        selItem = item;
        if (null == item.getValue()) {
            ppt.put("name", "菜单管理");
            ppt.put("fid", -1);
            ppt.put("pageId", null);
            ppt.put("path", "-1/");
            ppt.put("cc", 1);
            ppt.put("px", 1);
        } else {
            TreeVO treeVO = (TreeVO) item.getValue();
            getDetailMenu(treeVO.getOwid());
            // MenuVO memuvo = (MenuVO) treeVO.getBean();
            //BeanUtil.obj2Map(memuvo, ppt);
        }
        czlx = 0;
        bind2Page();
    }

    private void getDetailMenu(Integer owid) {
        String url = "/sysMenusApi/listOneMenu";
        Map<String, Object> _params = new HashMap<String, Object>(1);
        _params.put("owid", owid);
        ResponseMessage mess = JsonPostUtils.executeAPI(_params, url);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            ppt = (Map<String, Object>) mess.getBean();
            MenuVO menuVO = BeanUtil.map2Obj(ppt, MenuVO.class);
            BeanUtil.obj2Map(menuVO, ppt);
        }

    }

    //新增子节点
    private void addSubNewItem() {
        setNullPpt();
        czlx = 1;
        if (null == selItem || null == selItem.getValue()) {
            //表示根节点
            ppt.put("fid", -1);
            ppt.put("path", "-1/");
            ppt.put("type", 0);
            ppt.put("pageId", "");
            ppt.put("px", NodeUtils.getIndexInItem(selItem));//本节点序号中最大的
            ppt.put("cc", 1);//层次肯定为1
        } else {
            TreeVO treeVO = selItem.getValue();
            ppt.put("fid", treeVO.getOwid());
            ppt.put("path", treeVO.getPath() + "/");
            ppt.put("type", 0);
            ppt.put("pageId", "");
            ppt.put("px", NodeUtils.getIndexInItem(selItem));
            ppt.put("cc", treeVO.getCc() + 1);
        }
        bind2Page();
    }

    private void addSameLevelNewItem() {
        setNullPpt();
        czlx = 2;
        if (null == selItem || null == selItem.getValue()) {
            //表示根节点
            ppt.put("fid", -1);
            ppt.put("path", "-1/");
            ppt.put("type", 0);
            ppt.put("pageId", "");
            ppt.put("px", NodeUtils.getIndexInItem(selItem));//本节点序号中最大的
            ppt.put("cc", 1);//层次肯定为1
        } else {
            TreeVO treeVO = selItem.getValue();
            ppt.put("fid", treeVO.getFid());
            ppt.put("path", NodeUtils.getFpath(treeVO.getPath()) + "/");
            ppt.put("type", 0);
            ppt.put("pageId", "");
            ppt.put("px", NodeUtils.getIndexInItem(selItem.getParentItem()));
            ppt.put("cc", treeVO.getCc());
        }
        bind2Page();

    }

    private void save() {
        bindAll2Ppt(true);
        String url = "/sysMenusApi/saveMenu";
        //表示如果菜单选择是页面的话，必须关联页面配置
        if ("2".equals(ppt.get("type").toString())) {
            if (TextUtils.isEmpty(ppt.get("pageId"))) {
                throw new WrongValueException(getFellowIfAny("baseInfo_pageId"), I18nUtil.getLabelContent(ERCode.MENU_PAGE_ID_SEL));
            }
        }
        ResponseMessage mess = JsonPostUtils.executeAPI(ppt, url);
        if (null != mess && mess.getBackCode() == 0) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.OPERATE_SUCCESS));
            ppt = (Map<String, Object>) mess.getBean();
            MenuVO menuVO = BeanUtil.map2Obj(ppt, MenuVO.class);
            TreeVO _treevo = NodeUtils.convertFromMenuVo(menuVO);
            switch (czlx) {
                case 0://修改状态
                    selItem.setValue(_treevo);
                    ((Treecell) selItem.getTreerow().getFirstChild()).setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
                    break;
                case 1://新增为子节点
                    TreeUtils.addTreeItem(selItem, this, _treevo, true, true, true, true, menupopup);
                    break;
                case 2:
                    TreeUtils.addTreeItem(selItem.getParentItem(), this, _treevo, true, true, true, true, menupopup);
                    break;
                case 3:
                    break;
            }
        }
    }

    private void remove() {
        if (null == selItem || null == selItem.getValue()) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.MENU_ITEM_NOSEL));
            return;
        }
        if (null == selItem.getValue())
            return;
        TreeVO menuVO = (TreeVO) selItem.getValue();
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", menuVO.getOwid());
        String url = "/sysMenusApi/removeMenu";
        ResponseMessage mess = JsonPostUtils.executeAPI(params, url);
        if (null != mess & mess.getBackCode() == 0) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.OPERATE_SUCCESS));
            selItem.setVisible(false);
            selItem.getParentItem().getChildren().remove(selItem);
            selItem = null;
        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof Treeitem) {
            if (event.getName().equals(Events.ON_CLICK)) {
                //单击事件
                Treeitem item = (Treeitem) comp;
                displayNodeDetail(item);
            }
            //右键点击
            if (event.getName().equals(Events.ON_RIGHT_CLICK)) {
                selItem = (Treeitem) comp;
            }
            //如果是移动trieeite
            if (event.getName().equals(Events.ON_DROP)) {
                DropEvent dropEvent = (DropEvent) event;
                if (dropEvent.getDragged() instanceof Treeitem) {
                    dragItem = (Treeitem) dropEvent.getDragged();
                    dropItem = (Treeitem) dropEvent.getTarget();
                    rightMenuPopup.open(dropItem);//打开移动menu
                }
            }
        }
        if (comp instanceof BaseButton) {
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
        if (comp instanceof Menuitem) {
            if (comp.getId().equalsIgnoreCase("subItemAction")) {
                //移动过去变为子节点
                TreeMoveInt treeMoveInt = new MenuTreeMoveIntImpl();
                treeMoveInt.moveToSubTree(dragItem, dropItem,null);
                return;
            }
            if (comp.getId().equalsIgnoreCase("aboveItemAction")) {
                //移动过去变为子节点
                TreeMoveInt treeMoveInt = new MenuTreeMoveIntImpl();
                treeMoveInt.moveToPreTree(dragItem, dropItem,null);
                return;
            }
            if (comp.getId().equalsIgnoreCase("belowItemAction")) {
                //移动过去变为子节点
                TreeMoveInt treeMoveInt = new MenuTreeMoveIntImpl();
                treeMoveInt.moveToAfterTree(dragItem, dropItem,null);
                return;
            }
            //新增子节点
            if (comp.getId().equals("addSubItem")) {
                addSubNewItem();
                return;
            }
            //新增同级
            if (comp.getId().equals("addSameLeveItem")) {
                addSameLevelNewItem();
                return;
            }
            if (comp.getId().equals("removeItem")) {
                if (AlterDialog.corfirm(I18nUtil.getLabelContent(ERCode.TREE_REMOVE_NODETIP))) {
                    setNullPpt();
                    bind2Page();
                    remove();
                    czlx = 3;
                }
                return;
            }
        }
    }

}
