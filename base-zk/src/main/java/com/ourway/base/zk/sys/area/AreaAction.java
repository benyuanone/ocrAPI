package com.ourway.base.zk.sys.area;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.AreaVO;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.*;

/**
 * <p>方法 AreaAction : <p>
 * <p>说明:菜單管理</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/23 10:18
 * </pre>
 */
public class AreaAction extends BaseWindow {
    protected Log info = LogFactory.getLog(AreaAction.class);
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
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 140) + "px");
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
            Menuitem item = new Menuitem("新增子菜单");
            item.setId("treeAction");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
            item = new Menuitem("新增同级菜单");
            item.setId("rightPopupAddSameLevel");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
            item = new Menuitem("删除菜单");
            item.setId("rightPopupDelMenu");
            item.addEventListener(Events.ON_CLICK, this);
            item.setParent(popup);
        } else
            popup = (Menupopup) getFellowIfAny("rightPopup");
        return popup;
    }

    //初始化菜单
    private void initTree() {
        String url = "/areaApi/listAreas";
        ResponseMessage mess = JsonPostUtils.executeAPI(null, url, 1, 0);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            List<AreaVO> areavos = new ArrayList<AreaVO>();
            Map<String, Object> dataMap = (Map<String, Object>) mess.getBean();
            if (null != dataMap.get("records")) {
                List<Map<String, Object>> datas = (List<Map<String, Object>>) dataMap.get("records");
                for (Map<String, Object> map : datas) {
                    areavos.add(BeanUtil.map2Obj(map, AreaVO.class));
                }
                List<TreeVO> treeVOS = TreeUtils.convertAreaTree(areavos);
                TreeUtils.showTree(this, treeChild, treeVOS, true, true, true, true, menupopup);
            }
        }

    }
    private void save() {
        bindAll2Ppt(true);
        String url = "/areaApi/saveApi";
        ResponseMessage mess = JsonPostUtils.executeAPI(ppt, url);
        if (null != mess && mess.getBackCode() == 0) {
            AlterDialog.alert("操作成功");
            ppt = (Map<String, Object>) mess.getBean();
            AreaVO areavo = BeanUtil.map2Obj(ppt, AreaVO.class);
            switch (czlx) {
                case 0:
                    selItem.setValue(areavo);
                    ((Treecell) selItem.getTreerow().getFirstChild()).setLabel(areavo.getAreaName());
                    break;
                case 1:
                    TreeUtils.addTreeItem(selItem, this, areavo, true, true, true, true, menupopup);
                    break;
                case 2:
                    TreeUtils.addTreeItem(selItem.getParentItem(), this, areavo, true, true, true, true, menupopup);
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
        AreaVO menuVO = (AreaVO) selItem.getValue();
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", menuVO.getOwid());
        String url = "/areaApi/removeArea";
        ResponseMessage mess = JsonPostUtils.executeAPI(params, url);
        if (null != mess & mess.getBackCode() == 0) {
            AlterDialog.alert("删除成功");
            selItem.setVisible(false);
            selItem.getParentItem().getChildren().remove(selItem);
            selItem = null;
        }
    }



    private void setNullPpt() {
        Set<String> set = ppt.keySet();
        for (String s : set) {
            ppt.put(s, null);
        }
    }


    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof Treeitem) {
            if (event.getName().equals(Events.ON_CLICK)) {
                //单击事件
                Treeitem item = (Treeitem) comp;
                if (null == item.getValue()) {
                    ppt.put("areaName", "地区维护");
                    ppt.put("fid", -1);
                    ppt.put("path", "-1/");
                    ppt.put("visiable",0);
                } else {
                    AreaVO memuvo = (AreaVO) item.getValue();
                    BeanUtil.obj2Map(memuvo, ppt);
                }
                selItem = item;
                czlx = 0;
                bind2Page();
            }
            if (event.getName().equals(Events.ON_RIGHT_CLICK)) {
                selItem = (Treeitem) comp;
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
                } else {
                    AreaVO menuVO = selItem.getValue();
                    ppt.put("fid", menuVO.getOwid());
                    ppt.put("path", menuVO.getPath() + "/");
                }
                bind2Page();
            }
            if (comp.getId().equals("rightPopupAddSameLevel")) {
                setNullPpt();
                if (null == selItem.getValue()) {
                    //表示根节点
                    ppt.put("fid", -1);
                    ppt.put("path", "-1/");
                } else {
                    AreaVO menuVO = selItem.getValue();
                    ppt.put("fid", menuVO.getFid());
                    ppt.put("path", menuVO.getPath().substring(0, menuVO.getPath().lastIndexOf("/")) + "/");
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
