package com.ourway.base.zk.main;


import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseLabel;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.MenuVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.LoginUtil;
import com.ourway.base.zk.utils.data.MenusDataUtil;
import com.ourway.base.zk.utils.data.MessUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/2/1.
 */
public class MainAction extends BaseWindow {

    private static final long serialVersionUID = 3654985667382327935L;
    private Tabs resourceTabs;
    private Tabpanels resourceTabpanels;
    private Tabbox resources;

    @Override
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        resourceTabs = (Tabs) this.getFellowIfAny("resourceTabs");
        resourceTabpanels = (Tabpanels) this
                .getFellowIfAny("resourceTabpanels");
        resources = (Tabbox) this.getFellowIfAny("resources");
        resourceTabs.setWidth((ZKSessionUtils.getScreenWidth() - 196) + "px");

//        resources.setWidth((ZKSessionUtils.getScreenWidth() * 0.98 - 196) + "px");
//        resources.setWidth((ZKSessionUtils.getScreenWidth() * 0.98-190) + "px");

        Hlayout hlt = (Hlayout) this.getFellowIfAny("main");
//        hlt.setHeight((ZKSessionUtils.getScreenHeight()-180) + "px");
//        hlt.setStyle("overflow-y:hidden !important;");
//        hlt.setStyle("overflow:auto");
        initMenus();
        listAllWaitTask();
        initJsCookie();
        //初始化websocket
        initWebSocket();
        initFirstPage();

    }

    //初始化用户登录的首页面
    private void initFirstPage() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if (null != employVO) {
            if (!TextUtils.isEmpty(employVO.getEmpIndex())) {
                openFunByPageCa(I18nUtil.getLabelContent(ERCode.INDEX_PAGE), employVO.getEmpIndex());
            }
        }
    }

    //初始化cookie
    private void initJsCookie() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if (null != employVO)
            Clients.evalJavaScript("zkSetCookie('" + BaseConstants.URL_HOST + "','" + employVO.getEmpId() + "','" + employVO.getEmpName() + "','" + employVO.getLanguage() + "','" + BaseConstants.APP_KEY + "','" + ZKSessionUtils.getCookie() + "')");
    }

    private void initWebSocket() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if (null != employVO)
            Clients.evalJavaScript("connection('" + BaseConstants.WEBSOCKET_URL + "','" + BaseConstants.WEBSOCKET_SOCKET_URL + "','" + employVO.getEmpId() + "')");
    }

    //调用前端js传递过来的参数
    public void invokeByJs() {
        BaseTextbox hiddenMessage = (BaseTextbox) getFellowIfAny("hiddenMessage");
        if (TextUtils.isEmpty(hiddenMessage.getText()))
            return;
        String content = hiddenMessage.getText();
        if (content.startsWith("alert")) {
            AlterDialog.alert(content.replaceAll("alert", ""));
        }
        if (content.startsWith("open")) {
            String pageCa = content.replaceAll("open", "");
            String title = pageCa.split("\\$")[0];
            String _pageCa = pageCa.split("\\$")[1];
            openFunByPageCa(title, _pageCa);
        }
        if (content.startsWith("newMess")) {
            listAllWaitTask();
        }

    }

    /**
     * @deprecated:清除打开的界面
     */
    public void clearTab() {
        resourceTabs.getChildren().clear();
        resourceTabpanels.getChildren().clear();
    }

    /**
     * <p>
     * 退出系统，清除所有的session
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: MainAction.java,v 0.1 Dec 4, 2010 10:10:20 AM Jack Exp $
     */
    public void logout() {
        ZKSessionUtils.clearUser();
        LoginUtil.logOut();
        Executions.sendRedirect("/login.do");
    }


    public void openClose() {
        Navbar navbar = (Navbar) getFellowIfAny("navbar");
        A toggler = (A) getFellowIfAny("toggler");
        Div sidebar = (Div) getFellowIfAny("sidebar");
        int width = 0;
        if (navbar.isCollapsed()) {
            sidebar.setSclass("sidebar");
            sidebar.setWidth("194px");
            navbar.setCollapsed(false);
            toggler.setIconSclass("z-icon-angle-double-left");
            width = ZKSessionUtils.getScreenWidth() - ZKConstants.SYSTEM_WINDOW_WIDTH;
            resources.setWidth(width + "px");
            changeAllWindowWidth(width);
        } else {
            sidebar.setSclass("sidebar");
            sidebar.setWidth("48px");
            navbar.setCollapsed(true);
            toggler.setIconSclass("z-icon-angle-double-right");
            width = ZKSessionUtils.getScreenWidth() - ZKConstants.SYSTEM_WINDOW_CUSTMAXWIDTH;
            resources.setWidth(width + "px");
            changeAllWindowWidth(width);
        }

    }

    //改变所有窗体的宽度
    private void changeAllWindowWidth(int width) {
        List<Tabpanel> _tabpanels = resourceTabpanels.getChildren();
        for (Tabpanel tabpanel : _tabpanels) {
            if (tabpanel.getFirstChild() instanceof BaseWindow) {
                BaseWindow win = (BaseWindow) tabpanel.getFirstChild();
                win.setWidth((width - 5) + "px");
            }
        }
    }

    //根据用户初始化菜单
    private void initMenus() {
        if (null == ZKSessionUtils.getZkUser()) {
            AlterDialog.alert("您无权操作本系统或者您已经登录过去，请登录");
            Executions.sendRedirect("/login.do");
        } else
            addMenuItems();
    }

    //创建nav
    public Nav createNewNav(TreeVO menu, Component navbar) {
        Nav nav = new Nav();
        nav.setId("nav" + menu.getOwid());
        MenuVO menuVO = (MenuVO) menu.getBean();
        if (ZKSessionUtils.getZkUser().getEmpId().equalsIgnoreCase("root"))
            nav.setLabel(menu.getName());
        else
            nav.setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
        if (!TextUtils.isEmpty(menuVO.getIconClass()))
            nav.setIconSclass(menuVO.getIconClass());
//        if (!TextUtils.isEmpty(menu.getIcon()))
//            nav.setIconSclass(menu.getIcon());
        nav.setAttribute("menuBean", menu.getBean());
        nav.setParent(navbar);
        nav.addEventListener(Events.ON_CLICK, this);
        return nav;
    }

    //创建navitem
    public Navitem createNewNavItem(TreeVO vo, Component navbar) {
        Navitem item = new Navitem();
        item.setId("navItem" + vo.getOwid());
        MenuVO menuVO = (MenuVO) vo.getBean();
        if (ZKSessionUtils.getZkUser().getEmpId().equalsIgnoreCase("root"))
            item.setLabel(vo.getName());
        else
            item.setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
        if (!TextUtils.isEmpty(menuVO.getIconClass()))
            item.setIconSclass(menuVO.getIconClass());
        item.setParent(navbar);
        item.setAttribute("menuBean", vo.getBean());
        item.addEventListener(Events.ON_CLICK, this);
        return item;
    }

    //自动添加用户所能看到的菜单
    public void addMenuItems() {
        int _menuIndex = 0;//若index是0 ，默认打开
        Navbar navbar = (Navbar) getFellowIfAny("navbar");
        List<TreeVO> menus = MenusDataUtil.getMenus();
        if (null == menus || menus.size() <= 0)
            return;
        for (TreeVO menu : menus) {

            //只是单个的navitem
            if (null == menu.getSubTrees() || menu.getSubTrees().size() <= 0) {
                Navitem item = (Navitem) getFellowIfAny("navItem" + menu.getOwid());
                if (null == item) {
                    item = createNewNavItem(menu, navbar);
                }
                continue;
            }
            //表示有第二级的菜单
            Nav nav = (Nav) this.getFellowIfAny("nav" + menu.getOwid());
            if (null == nav) {
                nav = createNewNav(menu, navbar);
            }
            if (_menuIndex == 0)
                nav.setOpen(true);
            _menuIndex++;
            //处理二级
            if (null != menu.getSubTrees() && menu.getSubTrees().size() > 0) {
                for (TreeVO treeVO : menu.getSubTrees()) {
                    //处理第三级
                    if (null != treeVO.getSubTrees() && treeVO.getSubTrees().size() > 0) {
                        Nav subNav = (Nav) this.getFellowIfAny("subNav" + treeVO.getOwid());
                        if (null == subNav) {
                            subNav = createNewNav(treeVO, nav);
                        }
                        for (TreeVO vo : treeVO.getSubTrees()) {
                            if (null != vo.getSubTrees() && vo.getSubTrees().size() > 0) {
                                //处理第四级和第五级的菜单
                            } else {
                                Navitem item = (Navitem) getFellowIfAny("navItem" + vo.getOwid());
                                if (null == item) {
                                    item = createNewNavItem(vo, subNav);
                                }
                            }
                        }
                    } else {
                        Navitem item = (Navitem) getFellowIfAny("navItem" + treeVO.getOwid());
                        if (null == item) {
                            item = createNewNavItem(treeVO, nav);
                        }
                    }
                }
            }

        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        if (event.getTarget() instanceof Navitem) {
            Navitem item = (Navitem) event.getTarget();
            openFun(item.getAttribute("menuBean"));
            //菜单链接触发
        }
        if (event.getTarget() instanceof Nav) {
            //菜单链接触发
            Nav item = (Nav) event.getTarget();
            openFun(item.getAttribute("menuBean"));
        }

    }


    /**
     * <p>
     * 打开新的tab
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: MainAction.java,v 0.1 2013-4-27 下午10:18:26 Jack Exp $
     */
    @SuppressWarnings("unchecked")
    private Tab preparedTab(String title) {
        final Tab tab = new Tab(title);
        tab.setClosable(true);
        tab.addEventListener(Events.ON_CLOSE, new TabClose());
        List tbs = resourceTabs.getChildren();
        for (Object object : tbs) {
            if (object instanceof Tab) {
                Tab tb2 = (Tab) object;
                if (tb2.getLabel().equals(title)) {
                    resources.setSelectedTab(tb2);
                    return null;
                }
            }
        }
        tab.setId(TextUtils.getUUID());
        return tab;
    }

    //新增多个同名的tab窗口
    private Tab newTab(String title) {
        int index = 0;
        String _tmp = "";
        List<Tab> tbs = resourceTabs.getChildren();
        for (Tab tb : tbs) {
            if (tb.getLabel().startsWith(title + "-")) {
                _tmp = tb.getLabel().replaceAll(title + "-", "");
                if (Integer.parseInt(_tmp) > index)
                    index = Integer.parseInt(_tmp);
            }
        }
        Tab tab = new Tab(title + "-" + (index + 1));
        tab.setClosable(true);
        tab.addEventListener(Events.ON_CLOSE, new TabClose());
        tab.setId(TextUtils.getUUID());
        return tab;
    }

    //根据菜单的对象，进行触发相应的事件
    public void openFun(Object menuBean) {
        // 保存上次点击的连接，作为刷新处理
        ZKSessionUtils.setLastLink(menuBean);
        if (null == menuBean)
            return;
        MenuVO menuVO = (MenuVO) menuBean;
        Tab tab = preparedTab(menuVO.getName());
        if (null == tab)
            return;
        resourceTabs.appendChild(tab);
        Tabpanel tabpanel = newTabPanel(tab);
        Map<String, Object> params = new HashMap<String, Object>();
        if (!TextUtils.isEmpty(menuVO.getLink())) {
            params = prepareMenuItem(menuVO);
            doCreateWindow(menuVO.getLink(), tab, tabpanel, params);
            resourceTabpanels.appendChild(tabpanel);
            resources.setSelectedTab(tab);
        }
    }

    //传入参数，创建新窗体
    private void doCreateWindow(String link, Tab tab, Tabpanel tabpanel, Map<String, Object> params) {
        Component nowcomp = Executions.createComponents(link, null, params);
        BaseWindow win = (BaseWindow) nowcomp;
        win.setId("tabWin" + TextUtils.getUUID());
        win.setTabId(tab.getId() + "_window");
        int width = ZKSessionUtils.getScreenWidth() - ZKConstants.SYSTEM_WINDOW_WIDTH;
        win.setStyle("width:" + width + "px;height:98%;overflow-y:auto !important");

        tabpanel.appendChild(win);
    }

    //创建新的tabpanel
    private Tabpanel newTabPanel(Tab tab) {
        Tabpanel tabpanel = new Tabpanel();
        tabpanel.setId(tab.getId() + "_panel");
        tabpanel.setSclass("z-tabpanel-index");
        return tabpanel;
    }

    //根据pageCa来打开页面
    public void openFunByPageCa(String tabName, String pageCa) {
        String[] _pageCa;
        Tab tab = preparedTab(tabName);
        if (null == tab)
            return;
        resourceTabs.appendChild(tab);
        Tabpanel tabpanel = newTabPanel(tab);
        //作为pageCa获取文件的路径
        Map<String, Object> params = MessUtil.tranfLink(pageCa);
        if (pageCa.indexOf("?") >= 0) {
            _pageCa = pageCa.split("\\?");
            pageCa = _pageCa[0];
            //其它参数处理
            doHandleUrlParams(_pageCa[1], params);
        }
        String link = MessUtil.getLinkByPageCa(pageCa);
        params.put("pageCa", pageCa);
        params.put("pageType", 2);
        if (TextUtils.isEmpty(link))
            link = pageCa;
        doCreateWindow(link, tab, tabpanel, params);
        resourceTabpanels.appendChild(tabpanel);
        resources.setSelectedTab(tab);

    }

    private void doHandleUrlParams(String urlParams, Map<String, Object> params) {
        String[] cs = urlParams.split("\\&");
        String[] _vals;
        for (String c : cs) {
            if (!TextUtils.isEmpty(c)) {
                _vals = c.split("\\=");
                params.put(_vals[0], _vals[1]);
            }
        }
    }

    //新增窗口在tab页面中打开，可以打开多个，用序号分隔
    public String openNewTab(BaseWindow window, String title) {
        Tab tab = newTab(title);
        if (null == tab)
            return "";
        resourceTabs.appendChild(tab);
        Tabpanel tabpanel = newTabPanel(tab);
        window.setStyle("width:100%;height:100%;overflow-y:auto !important");
        //window.setStyle("overflow-y:auto");
//        window.setHeight((ZKSessionUtils.getScreenHeight()-110)+"px");
        tabpanel.appendChild(window);
        resourceTabpanels.appendChild(tabpanel);
        resources.setSelectedTab(tab);
        return tab.getId();
    }

    //关闭tab页面
    public void closeTab(String tabid) {
        Tab tab = (Tab) getFellowIfAny(tabid);
        Tabpanel tabpanel = (Tabpanel) getFellowIfAny(tabid + "_panel");
        resourceTabpanels.removeChild(tabpanel);
        resourceTabs.removeChild(tab);
        if (resourceTabs.getChildren().size() > 0)
            resources.setSelectedIndex(resourceTabs.getChildren().size() - 1);
    }

    //级联删除tab页面
    public void closeTabWin(BaseWindow window) {
        if (TextUtils.isEmpty(window.getTabId()))
            return;
        String tabid = window.getTabId().replaceAll("_window", "");
        Tab tab = (Tab) getFellowIfAny(tabid);
        Tabpanel tabpanel = (Tabpanel) getFellowIfAny(tabid + "_panel");
        resourceTabpanels.removeChild(tabpanel);
        resourceTabs.removeChild(tab);
        Tab paranentTab = getParentTabByWindow(window.getTopWindow());
        if (null == paranentTab) {
            if (resourceTabs.getChildren().size() > 0)
                resources.setSelectedIndex(resourceTabs.getChildren().size() - 1);
        } else
            resources.setSelectedTab(paranentTab);
    }

    public Tab getParentTabByWindow(BaseWindow window) {
        if (null == window)
            return null;
        if (TextUtils.isEmpty(window.getTabId()))
            return null;
        else {
            String tabid = window.getTabId().replaceAll("_window", "");
            Tab tab = (Tab) getFellowIfAny(tabid);
            if (null == tab) {
                return getParentTabByWindow(window.getTopWindow());
            } else
                return tab;
        }
    }

    public void closeOneTab(String tabid) {
        Tab tab = (Tab) getFellowIfAny(tabid);
        Tabpanel tabpanel = (Tabpanel) getFellowIfAny(tabid + "_panel");
        resourceTabpanels.removeChild(tabpanel);
        resourceTabs.removeChild(tab);
    }

//    public void closeTab(List<String> tabids) {
//        if (tabids.size() == 1) {
//            //直接关闭，开启最近的一个
//            closeTab(tabids.get(0));
//        } else {
//            //关闭当前的然后逐步向前检索关闭
//            closeOneTab(tabids.get(tabids.size() - 1));
//            Tab tab = getParentTab(tabids, tabids.size() - 2);
//            if (null == tab) {
//                if (resourceTabs.getChildren().size() > 0)
//                    resources.setSelectedIndex(resourceTabs.getChildren().size() - 1);
//            } else
//                resources.setSelectedTab(tab);
//        }
//    }

    public Tab getParentTab(List<String> tabids, int index) {
        String tabid = tabids.get(index);
        Tab tab = (Tab) getFellowIfAny(tabid);
        if (null == tab) {
            index = index - 1;
            if (index < 0)
                return null;
            else
                return getParentTab(tabids, --index);
        } else
            return tab;
    }


    //组装菜单所需要的传递的属性
    private Map<String, Object> prepareMenuItem(MenuVO menuVO) {
        if (TextUtils.isEmpty(menuVO.getLink()))
            return null;
        String link = menuVO.getLink();
        Map<String, Object> menuParams = new HashMap<String, Object>();
        menuParams.put("pageCa", menuVO.getPageCa());
        menuParams.put("pageType", menuVO.getPageType());
        if (link.contains("?")) {
            link = link.split("\\?")[1];
            String[] params = link.split("\\&");
            String[] _pt;
            for (String param : params) {
                _pt = param.split("\\=");
                menuParams.put(_pt[0], _pt[1]);
            }
        }
        return menuParams;
    }

    public void listAllWaitTask() {
        String title = "";
        String content = "";
        Map<String, Object> objs = MessUtil.listWaitTask();
        BaseLabel anotiNumLabel = (BaseLabel) getFellowIfAny("anotiNumLabel");
        A notippLabel = (A) getFellowIfAny("notippLabel");
        Vlayout taskList = (Vlayout) getFellowIfAny("taskList");
        taskList.getChildren().clear();
        if (null != objs && null != objs.get("records")) {
            anotiNumLabel.setValue(objs.get("totalCount") + "");
            notippLabel.setLabel(objs.get("totalCount") + I18nUtil.getLabelContent(ERCode.INDEX_WAIT_TASK));
            for (Map<String, Object> obj : (List<Map<String, Object>>) objs.get("records")) {
                A a = new A();
                a.addEventListener(Events.ON_CLICK, new AClick(obj));
                a.setIconSclass("btn btn-xs no-hover btn-pink z-icon-comment");
                a.setParent(taskList);
                BaseLabel _label = new BaseLabel();
                if (!TextUtils.isEmpty(obj.get("messTitle")))
                    title = obj.get("messTitle").toString();
                else
                    title = ERCode.MESSAGE_DEFAULT_TITLE;
                if (!TextUtils.isEmpty(obj.get("messContent")))
                    content = obj.get("messContent").toString();
                else
                    content = "";
                _label.setValue("[" + I18nUtil.getLabelContent(title) + "] " + content);
                _label.setParent(a);
                Span span = new Span();
                span.setParent(a);
                span.setClass("z-icon-arrow-right");
            }
        }
    }


    class AClick implements EventListener {
        Map<String, Object> obj;

        public AClick(Map<String, Object> obj) {
            this.obj = obj;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getTarget() instanceof A) {
                MessUtil.readMessage(obj.get("owid").toString());
                if (!TextUtils.isEmpty(obj.get("messBizUrl"))) {
                    openFunByPageCa(I18nUtil.getLabelContent(obj.get("messTitle").toString()), obj.get("messBizUrl").toString());
                }
                listAllWaitTask();
            }
        }
    }

    //修改tab名称
    public void changeTabLable(String tabId, String label) {
        Tab tab = (Tab) getFellowIfAny(tabId);
        if (null != tab)
            tab.setLabel(label);
    }

    class TabClose implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getTarget() instanceof Tab) {
                String tabId = ((Tab) event.getTarget()).getId();
                Tabpanel tabpanel = (Tabpanel) getFellowIfAny(tabId + "_panel");
                if (null == tabpanel)
                    return;
                BaseWindow window = (BaseWindow) tabpanel.getFirstChild();
                //判断页面处理编辑状态下。
                if (window.getWindowType() == 3) {
                    if (AlterDialog.corfirm(I18nUtil.getLabelContent(ERCode.TAB_CLOSE_IP))) {
                        closeTabWin(window);
                    } else
                        return;
                }

            }
        }
    }
}
