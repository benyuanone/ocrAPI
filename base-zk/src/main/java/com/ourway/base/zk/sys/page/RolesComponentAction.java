package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.*;


/**
 * <p>方法 RolesComponentAction : <p>
 * <p>说明:角色详情</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/29 16:23
 * </pre>
 */
public class RolesComponentAction extends BaseWindow {

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        init();
        Object obj = event.getArg().get("ppt");
        if (null != obj) {
            ppt = (Map<String, Object>) obj;
            bind2Page();
        }
        btnAddEvent("saveBtn");
        btnAddEvent("filterBtn");
        btnAddEvent("cancelBtn");
        btnAddEvent("addInBtn");
        btnAddEvent("moveOutBtn");
        initUserPri("");
        initRoleFun();
    }

    /**
     * <p>方法:btnAddEvent TODO 按钮添加事件</p>
     * <ul>
     * <li> @param compId TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/5/31 20:28  </li>
     * </ul>
     */
    private void btnAddEvent(String compId) {
        BaseButton baseBtn = (BaseButton) getFellowIfAny(compId);
        baseBtn.addEventListener(Events.ON_CLICK, this);
    }

    /**
     * <p>方法:initRoleFun TODO 初始化功能-角色</p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/31 13:07  </li>
     * </ul>
     */
    private void initRoleFun() {
        Treechildren child = (Treechildren) this
                .getFellowIfAny("privTreeContent");
        Map<String, Object> param = new HashMap<>();
        Object obj = ppt.get("owid");
        param.put("roleRefOwId", ppt.get("owid"));
        ResponseMessage messSelmenu = JsonPostUtils.executeAPI(param, "/sysMenusApi/roleMenu");
        ResponseMessage messAllmenu = JsonPostUtils.executeAPI("/sysMenusApi/getAllMenu");
        List<Integer> sels = new ArrayList<Integer>();
        if (null == messSelmenu || messSelmenu.getBackCode() != 0)
            return;
        else if (null != messSelmenu.getBean() && !messSelmenu.getBean().equals("null")) {
            List<Map> objsels = (List<Map>) messSelmenu.getBean();
            for (Map map : objsels) {
                Integer owid = Integer.parseInt(map.get("menuRefId").toString());
                sels.add(owid);
            }
        }
        if (null != messAllmenu && messAllmenu.getBackCode() == 0) {
            List<Map> data = (List<Map>) messAllmenu.getBean();
            List<TreeVO> treeVOS = TreeUtils.mapConvertTree(data);
            this.showMenus(-1, child, sels, treeVOS);
        }
    }

    /**
     * <p>方法:initUserPri TODO 初始化用户-角色</p>
     * <ul>
     * <li> @param empName TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/5/31 20:28  </li>
     * </ul>
     */
    private void initUserPri(String empName) {
        findUser("noused", empName, 0);
        findUser("used", empName, 1);
    }

    /**
     * <p>方法:showMenus TODO 传入menu编号，递归所有的菜单项目</p>
     * <ul>
     * <li> @param menuId TODO</li>
     * <li> @param comp TODO</li>
     * <li> @param sels TODO</li>
     * <li> @param menus TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/6/1 15:21  </li>
     * </ul>
     */
    private void showMenus(Integer menuId, Component comp, List sels,
                           List<TreeVO> menus) {
        // 改用程序实现递归
        for (TreeVO menu : menus) {
            if (menu.getFid() == menuId.intValue()) {
                Treeitem item = new Treeitem();
                item.setLabel(menu.getName());
                item.setValue(menu);
                item.setOpen(false);
                if (sels.contains(menu.getOwid().intValue()))
                    item.setSelected(true);
                item.setParent(comp);
                item.setCheckable(true);
                item.addEventListener(Events.ON_CLICK, new TreeChecked(this));
                // 递归调用
                if (null != menu.getSubTrees() && menu.getSubTrees().size() > 0) {
                    Treechildren child = new Treechildren();
                    child.setParent(item);
                    showMenus(menu.getOwid(), child, sels, menu.getSubTrees());
                }
            }

        }

    }

    /**
     * <p>方法:findUser TODO 查询分配与否的员工</p>
     * <ul>
     * <li> @param compId TODO</li>
     * <li> @param empName TODO</li>
     * <li> @param isUse TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/5/31 20:30  </li>
     * </ul>
     */
    private void findUser(String compId, String empName, int isUse) {
        Map<String, Object> param = new HashMap<>();
        param.put("empName", empName);
        param.put("isUse", isUse);//表示没分配过
        param.put("owid", ppt.get("owid"));
        ResponseMessage mess = JsonPostUtils.executeAPI(param, "/rolesApi/getEmpRoles");
        Listbox listbox = (Listbox) this.getFellowIfAny(compId);
        listbox.getChildren().clear();
        if (null != mess && mess.getBackCode() == 0) {
            List<Map> data = (List<Map>) mess.getBean();
            for (Map map : data) {
                boolean flag = true;
                if (isUse == 0)
                    flag = !"root".equals(map.get("empId"));
                if (flag) {
                    Listitem item = new Listitem();
                    item.setLabel((null == map.get("empId") ? "未定" : map.get("empId")) + "-" + map.get("empName"));
                    item.setValue(map.get("owid"));
                    item.setParent(listbox);
                }
            }
        }
    }

    /**
     * <p>方法:init TODO 初始化角色类型</p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/5/31 20:29  </li>
     * </ul>
     */
    private void init() {
        BaseListbox pageInfo_roleType = (BaseListbox) getFellowIfAny("pageInfo_roleType");
        ResponseMessage mess = JsonPostUtils.executeAPI("/rolesApi/getRolesTypes");
        if (null != mess)
            if (mess.getBackCode() == 0) {
                List<Map> data = (List<Map>) mess.getBean();
                Listitem item = new Listitem("----请选择----");
                item.setSelected(true);
                item.setParent(pageInfo_roleType);
                if (null != data && data.size() > 0) {
                    for (Map value : data) {
                        item = new Listitem(value.get("dicVal1").toString());
                        item.setParent(pageInfo_roleType);
                        item.setValue(value.get("owid"));
                    }
                }
            } else {
                AlterDialog.alert(mess.getErrorMess());
            }
    }

    /**
     * <p>方法:filter TODO 查询事件</p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/5/31 20:30  </li>
     * </ul>
     */
    public void filter() {
        BaseTextbox empName = (BaseTextbox) this.getFellowIfAny("empName");
        findUser("noused", empName.getValue(), 0);
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("saveBtn")) {
                saveBaseInfo();
                Component comps = Path.getComponent("/mainWin");
                if (comps instanceof MainAction) {
                    MainAction root = (MainAction) comps;
                    root.closeTabWin(this);
                }
            }
            if (comp.getId().startsWith("cancelBtn")) {
                Component comps = Path.getComponent("/mainWin");
                if (comps instanceof MainAction) {
                    MainAction root = (MainAction) comps;
                    root.closeTabWin(this);
                }
            }
            if (comp.getId().equals("filterBtn")) {
                filter();
            }
            if (comp.getId().equals("addInBtn")) {
                addIn();
            }
            if (comp.getId().equals("moveOutBtn")) {
                addOut();
            }
        }
    }

    /**
     * <p>方法:saveBaseInfo 保存基本信息 </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/5/31 20:23  </li>
     * </ul>
     */
    private void saveBaseInfo() {
        bindAll2Ppt(true);
        //取用户分配
        Listbox used = (Listbox) this.getFellowIfAny("used");
        List<String> sels = new ArrayList<String>();
        List list = used.getItems();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Listitem it = (Listitem) iterator.next();
            sels.add(it.getValue().toString());
        }
        ppt.put("selEmps", sels);
        //取功能分配
        List<Integer> selMenus = new ArrayList<>();
        Tree tree = (Tree) this.getFellowIfAny("privTree");
        Set set = tree.getSelectedItems();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Treeitem it = (Treeitem) iterator.next();
            TreeVO menu = it.getValue();
            selMenus.add(menu.getOwid());
        }
        ppt.put("selMenus", selMenus);
        ResponseMessage mess = JsonPostUtils.executeAPI(ppt, "/rolesApi/saveRole");
        if (null != mess)
            if (mess.getBackCode() == 0) {
                AlterDialog.alert("页面添加成功");
                ppt.put("owid", mess.getBean().toString());
                getBaseGrid().refreshRow(getPpt(), getBaseGrid().getDbRow());
            } else {
                AlterDialog.alert(mess.getErrorMess());
            }
        else
            AlterDialog.alert("系统内部错误");
    }

    public void addIn() {
        Listbox noused = (Listbox) this.getFellowIfAny("noused");
        Listbox used = (Listbox) this.getFellowIfAny("used");
        List<Listitem> sels = new ArrayList<Listitem>();
        Set set = noused.getSelectedItems();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Listitem it = (Listitem) iterator.next();
            sels.add(it);
        }
        for (Listitem it : sels) {
            it.setParent(used);
        }
    }

    public void addOut() {
        Listbox noused = (Listbox) this.getFellowIfAny("noused");
        Listbox used = (Listbox) this.getFellowIfAny("used");
        List<Listitem> sels = new ArrayList<Listitem>();
        Set set = used.getSelectedItems();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Listitem it = (Listitem) iterator.next();
            sels.add(it);
        }
        for (Listitem it : sels) {
            it.setParent(noused);
        }
    }

    /**
     * <p>方法:checkChild TODO 递归检查子节点</p>
     * <ul>
     * <li> @param item TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/6/1 15:14  </li>
     * </ul>
     */
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

    /**
     * <p>方法:checkParent TODO 递归检查父节点</p>
     * <ul>
     * <li> @param item TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2017/6/1 15:14  </li>
     * </ul>
     */
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

    private class TreeChecked implements EventListener {
        private RolesComponentAction menu;

        public TreeChecked(RolesComponentAction menu) {
            this.menu = menu;
        }

        public void onEvent(Event arg0) throws Exception {
            Treeitem it = (Treeitem) arg0.getTarget();
            checkChild(it);
            checkParent(it);
        }
    }

}
