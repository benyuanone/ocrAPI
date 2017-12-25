package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseWindow extends Window implements ComponentSer<String>, EventListener, AfterCompose, Serializable {
    public static final String COMP_CONTACT = "_";//布局控件和属性的组合体
    private TemplateUtils templateUtils;//整个页面的工具类
    private GridUtils gridUtils; //整个页面相关表格的工具类
    private String pageCA;//页面权限
    protected boolean parentEvent = true;
    /* 窗体类型 1表示新增，2表示查看 3 表示修改状态 4表示列表 5 表示复制 */
    protected int windowType = 4;
    //    protected BaseWindow parentWindow; //上级窗口对象
    protected Map<String, Object> parentPpt;//上级窗口ppt对象
    protected Map<?, ?> parentArgs;//上级传递过来的参数
    private BaseWindow topWindow; //如果弹出窗口的时候，上级窗口
    private String tabId; //当前window对应的tabid

    protected Map<String, Object> ppt = new HashMap<String, Object>();  //当前页面的对象
    public Map<String, Boolean> validatePpt = new HashMap<String, Boolean>();//控件及其功能的权限
    protected boolean isClosePage = false;//是否关闭弹出框
    protected boolean isDetach = false;
    protected boolean isDelete = false;//是否是删除
    private BaseGrid baseGrid;//需要更改的dataList列表
    //    private List<String> tabId;//tab页面的编号
    private String selRowId; //选中行的id
    private BaseTree baseTree; //当前的树
    private Treeitem baseTreeItem;//选中的树节点
    private Integer baseTreeType;//树操作类型

    public Integer getBaseTreeType() {
        return baseTreeType;
    }

    public void setBaseTreeType(Integer baseTreeType) {
        this.baseTreeType = baseTreeType;
    }

    public BaseTree getBaseTree() {
        return baseTree;
    }

    public void setBaseTree(BaseTree baseTree) {
        this.baseTree = baseTree;
    }

    public Treeitem getBaseTreeItem() {
        return baseTreeItem;
    }

    public void setBaseTreeItem(Treeitem baseTreeItem) {
        this.baseTreeItem = baseTreeItem;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getSelRowId() {
        return selRowId;
    }

    public void setSelRowId(String selRowId) {
        this.selRowId = selRowId;
    }

    public BaseWindow getTopWindow() {
        return topWindow;
    }

    public void setTopWindow(BaseWindow topWindow) {
        this.topWindow = topWindow;
    }
//    public BaseWindow getParentWindow() {
////        return parentWindow;
////    }
//
//    public void setParentWindow(BaseWindow parentWindow) {
//        this.parentWindow = parentWindow;
//    }

    public Map<String, Object> getParentPpt() {
        return parentPpt;
    }

    public void setParentPpt(Map<String, Object> parentPpt) {
        this.parentPpt = parentPpt;
    }

    public GridUtils getGridUtils() {
        return gridUtils;
    }

    public void setGridUtils(GridUtils gridUtils) {
        this.gridUtils = gridUtils;
    }

    public TemplateUtils getTemplateUtils() {
        return templateUtils;
    }

    public void setTemplateUtils(TemplateUtils templateUtils) {
        this.templateUtils = templateUtils;
    }

    public BaseGrid getBaseGrid() {
        return baseGrid;
    }

    public void setBaseGrid(BaseGrid baseGrid) {
        this.baseGrid = baseGrid;
    }

//    public List<String> getTabId() {
//        return tabId;
//    }
//
//    public void setTabId(List<String> tabId) {
//        this.tabId = tabId;
//    }

    public boolean isDetach() {
        return isDetach;
    }

    public void setDetach(boolean detach) {
        isDetach = detach;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Map<String, Object> getPpt() {
        return ppt;
    }

    public void setPpt(Map<String, Object> ppt) {
        this.ppt = ppt;
    }

    public boolean isClosePage() {
        return isClosePage;
    }

    public void setClosePage(boolean closePage) {
        isClosePage = closePage;
    }

    public int getWindowType() {
        return windowType;
    }

    public void setWindowType(int windowType) {
        this.windowType = windowType;
    }

    public String getPageCA() {
        return pageCA;
    }

    public void setPageCA(String pageCA) {
        this.pageCA = pageCA;
    }

    @Override
    public void init(Map<String, Object> pageObj, String property) {

    }

    @Override
    public String getPageValue() {
        return "";
    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public void afterCompose() {
        if (null != getAttribute("caname"))
            setPageCA(getAttribute("caname").toString());
        ZKSessionUtils.setUserAgent(request().getHeader("User-Agent"));
        System.out.println(getPageCA());
    }

    public void onCreate(CreateEvent event) {
        if (TextUtils.isEmpty(pageCA)) {
            AlterDialog.alert(ERCode.CA_NO);
            return;
        }
        parentArgs = event.getArg();
        setTemplateUtils(new TemplateUtils());
        setGridUtils(new GridUtils());
        //替换当前页面的pageCa
        if (null != event.getArg().get("pageCa"))
            setPageCA(event.getArg().get("pageCa").toString());
        if (null != event.getArg().get("pageType"))
            setWindowType(new Integer(event.getArg().get("pageType").toString()));
//        if (null != event.getArg().get("title"))
//            setTitle(I18nUtil.getLabelContent(event.getArg().get("title").toString()));
        if (null != event.getArg().get("css"))
            setStyle(event.getArg().get("css").toString());


        //TO-DO: 接收页面传递过来的参数，包括window的类型，修改的对象及其它数据
        initPageObj();

    }


    //初始化本页面的得对象
    private void initPageObj() {
        List<PageControlVO> pageControlList = new ArrayList<PageControlVO>();
        boolean flag = PageDataUtil.getControls(pageCA, pageControlList);
        if (!flag) {
//            AlterDialog.openAlart("不存在当前页面的配置信息");
            return;
        }
        for (PageControlVO pageControl : pageControlList) {
            ppt.put(pageControl.getKjAttribute(), null);
        }
    }


    @Override
    public void onEvent(Event event) throws Exception {
        System.out.println("=================");
        if (parentEvent) {
            Component comp = event.getTarget();
            if (comp instanceof BaseTextbox) {
                BaseTextbox textbox = (BaseTextbox) comp;
                textbox.doEvent(null, this, event);
            }
            if (comp instanceof BaseButton) {
                BaseButton button = (BaseButton) comp;
                button.doEvent(null, this, event);
            }
            if (comp instanceof BaseListbox) {
                BaseListbox button = (BaseListbox) comp;
                button.doEvent(null, this, event);
            }
            if (comp instanceof BaseCheckbox) {
                BaseCheckbox button = (BaseCheckbox) comp;
                button.doEvent(null, this, event);
            }
            if (comp instanceof BaseDecimalbox) {
                BaseDecimalbox button = (BaseDecimalbox) comp;
                button.doEvent(null, this, event);
            }
            if (comp instanceof BaseIntbox) {
                BaseIntbox button = (BaseIntbox) comp;
                button.doEvent(null, this, event);
            }
            if (comp instanceof BaseMenuitem) {
                BaseMenuitem button = (BaseMenuitem) comp;
                button.doEvent(null, this, event);
            }
            if (comp instanceof BaseMultipFileUpload) {
                BaseMultipFileUpload button = (BaseMultipFileUpload) comp;
                button.doEvent(null, this, event);
            }
        }
    }

    /**
     * <p>方法:bind2Page 把当前对象绑定到页面中 </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/3 1:00  </li>
     * </ul>
     */
    public void bind2Page() {
        Set<String> keys = ppt.keySet();
        Collection<Component> comps = getFellows();
        for (String key : keys) {
            for (Component comp : comps) {
                //进行按钮处理
                DataBinder.refreshSingleButton(ppt, comp);
                if (comp.getId().endsWith(COMP_CONTACT + key) || comp.getId().equals(key)) {
                    DataBinder.obj2Component(ppt.get(key), comp, ppt);
                }
                if (comp.getId().contains(COMP_CONTACT) && comp.getId().contains(".")) {
                    //表示多级
                    bindTreeComp(ppt, comp);
                }
                //出去，考虑多级别属性，用label标签 0912
//                else {
//                    //当如果存在多级别的属性，如 a.b.c,则进行拆分
//                    if (comp.getId().contains(COMP_CONTACT) && comp.getId().contains(".")) {
//                        //表示多级
//                        bindTreeComp(ppt, comp);
//                    }
//                }
            }
        }
    }

    //如果是多级别的属性，则逐级获取
    private void bindTreeComp(Map<String, Object> dataMap, Component comp) {
        if (comp.getId().contains(COMP_CONTACT) && comp.getId().contains(".")) {
            String[] ids = comp.getId().split(COMP_CONTACT);
            if (ids.length != 2) {
                return;
            }
            String compId = ids[1];
            if (!compId.contains("."))
                return;
            String[] _ids = compId.split("\\.");
            Object _dataObj = null;
            Map<String, Object> _dataMap = dataMap;
            for (int index = 0; index < _ids.length; index++) {
                if (index != _ids.length - 1) {
                    _dataObj = _dataMap.get(_ids[index]);
                    DataBinder.obj2Component(_dataObj, comp, dataMap);
                } else {
                    _dataObj = _dataMap.get(_ids[index]);
                    if (TextUtils.isEmpty(_dataObj))
                        return;
                    else
                        _dataMap = (Map<String, Object>) _dataObj;
                }
            }
        }
    }

    /**
     * <p>方法:bind2Ppt 从界面绑定信息 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/3 1:37  </li>
     * </ul>
     */
    protected Map<String, Object> bind2Ppt() {
        Set<String> keys = ppt.keySet();
        Object _obj = null;
        List<String> keylist = new ArrayList<String>(keys.size());
        for (String s : keys) {
            keylist.add(s);
        }
        Collection<Component> comps = getFellows();
        for (String key : keylist) {
            for (Component comp : comps) {
                if (comp.getId().endsWith(COMP_CONTACT + key) || comp.getId().equals(key)) {
                    _obj = DataBinder.component2Obj(comp);
                    ppt.put(key, _obj);
                    _obj = DataBinder.component2Label(comp);
                    if (!TextUtils.isEmpty(_obj)) {
                        ppt.put(key + "Label", _obj);
                    }
                    break;
                }
            }
        }
        return ppt;
    }

    /**
     * <p>方法:bindAll2Ppt 把输入控件的内容都放到ppt中，不影响ppt中已经存在的内容 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 18:27  </li>
     * </ul>
     */
    public Map<String, Object> bindAll2Ppt(boolean checkFlag) {
        String[] tmp = null;
        Object _obj = null;
        Collection<Component> comps = getFellows();
        for (Component comp : comps) {
            if (comp instanceof Treeitem || comp instanceof Menuitem)
                continue;
            if (comp.getId().indexOf(COMP_CONTACT) > 0) {
                if (comp instanceof ComponentSer) {
                    tmp = comp.getId().split("\\" + COMP_CONTACT);
                    if (tmp.length == 2) {
                        _obj = DataBinder.component2Obj(comp, true, this);
                        ComponentSer componentSer = (ComponentSer) comp;
                        if (null != componentSer.getPgvo() && !TextUtils.isEmpty(componentSer.getPgvo().getKjBindKey()))
                            ppt.put(componentSer.getPgvo().getKjBindKey(), _obj);
                        else
                            ppt.put(tmp[1], _obj);
                        _obj = DataBinder.component2Label(comp);
                        if (!TextUtils.isEmpty(_obj)) {
                            ppt.put(tmp[1] + "Label", _obj);
                        }
                    }
                }
            }
        }
        return ppt;
    }

    /**
     * <p>方法:bind2Object 把传递下面所有的取值控件绑定到map对象中 </p>
     * <ul>
     * <li> @param valueMap 绑定的map对象</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 13:01  </li>
     * </ul>
     */
    public Map<String, Object> bind2Object() {
        Object _obj = null;
        String[] tmp = null;
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Collection<Component> comps = getFellows();
        for (Component comp : comps) {
            if (comp.getId().indexOf(COMP_CONTACT) > 0) {
                tmp = comp.getId().split("\\" + COMP_CONTACT);
                if (tmp.length == 2) {
                    _obj = DataBinder.component2Obj(comp);
                    valueMap.put(tmp[1], _obj);
                    _obj = DataBinder.component2Label(comp);
                    if (!TextUtils.isEmpty(_obj)) {
                        valueMap.put(tmp[1] + "Label", _obj);
                    }
                }
            }
        }
        return valueMap;
    }

    //制定某个空间下面的属性绑定
    protected void bind2Ppt(String prexId, Map<String, Object> params) {
        Object _obj = null;
        String[] keys = null;
        Collection<Component> comps = getFellows();
        for (Component comp : comps) {
            if (comp.getId().startsWith(prexId + COMP_CONTACT) || comp.getId().endsWith(COMP_CONTACT + prexId)) {
                _obj = DataBinder.component2Obj(comp);
                keys = comp.getId().split("\\_");
                params.put(keys[keys.length - 1], _obj);
                _obj = DataBinder.component2Label(comp);
                if (!TextUtils.isEmpty(_obj)) {
                    params.put(keys[keys.length - 1] + "Label", _obj);
                }
            }
        }
    }

    //获取指定控件的值
    protected Map<String, Object> bind2Ppt(String controlIds) {
        Collection<Component> comps = getFellows();
//        Map<String, Object> data = new HashMap<String, Object>();
        if (controlIds.indexOf(",") > 0) {
            String[] _controls = controlIds.split("\\,");
            for (String control : _controls) {
                for (Component component : comps) {
                    if (component.getId().equals(control) || component.getId().endsWith(COMP_CONTACT + control) || component.getId().startsWith(control + COMP_CONTACT)) {
                        try {
                            ppt.put(component.getId().split("\\_")[1], DataBinder.component2Obj(component));
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }
        } else {
            for (Component component : comps) {
                if (component.getId().equals(controlIds) || component.getId().endsWith(COMP_CONTACT + controlIds) || component.getId().startsWith(controlIds + COMP_CONTACT)) {
                    try {
                        ppt.put(component.getId().split("\\_")[1], DataBinder.component2Obj(component));
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
        return ppt;
    }

    /**
     * <p>方法:bind2Filter 组装查询用的条件 </p>
     * <ul>
     * <li> @param controlIds 所需要熬的控件编号</li>
     * <li>@return java.util.List<com.ourway.base.zk.models.FilterModel>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/17 17:59  </li>
     * </ul>
     */
    public List<FilterModel> bind2Filter(String controlIds) {
        Collection<Component> comps = getFellows();
        List<FilterModel> models = new ArrayList<FilterModel>();
        if (controlIds.indexOf(",") > 0) {
            String[] _controls = controlIds.split("\\,");
            for (String control : _controls) {
                for (Component component : comps) {
                    if(component instanceof ComponentSer){
                        if (component.getId().equals(control) || component.getId().endsWith(COMP_CONTACT + control) || component.getId().startsWith(control + COMP_CONTACT)) {
                            try {
                                if (null != DataBinder.doFilterFromComponent(component)) {
                                    models.add(DataBinder.doFilterFromComponent(component));
                                }
                            } catch (Exception e) {
                                continue;
                            }
                        }

                    }
                }
            }
        } else {
            for (Component component : comps) {
                if (component.getId().equals(controlIds) || component.getId().endsWith(COMP_CONTACT + controlIds) || component.getId().startsWith(controlIds + COMP_CONTACT)) {
                    try {
                        if (null != DataBinder.doFilterFromComponent(component))
                            models.add(DataBinder.doFilterFromComponent(component));
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
        return models;
    }

    public HttpServletRequest request() {
        HttpServletRequest request = (HttpServletRequest) Executions
                .getCurrent().getNativeRequest();

        return request;
    }

    /**
     * <p>方法:createPpt 手工创建ppt </p>
     * <ul>
     * <li> @param args 参数</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/18 12:01  </li>
     * </ul>
     */
    public void createPpt(String... args) {
        for (String arg : args) {
            ppt.put(arg, null);
        }
    }

    /**
     * <p>方法:copyWin 拷贝 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 13:07  </li>
     * </ul>
     */
    public void copyWin() {
        ZKSessionUtils.setTempObject(this);
    }

    /**
     * <p>方法:pasteWin 黏贴 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 13:09  </li>
     * </ul>
     */
    public void pasteWin() {
        if (null != ZKSessionUtils.getTempObject()) {
            ppt = ZKSessionUtils.getTempObject();
            bind2Page();
        } else {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.MENU_COPY_NOCOPY));
        }
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {

    }

    @Override
    public void reset() {

    }

    //    显示或者隐藏控件
    public void showOrHide(boolean flag) {
//          templateUtils.showOrHideConditionDiv(flag,this,divIds);
    }

    /**
     * <p>方法:loadTreeData 加载或者刷新左边的树 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/9/14 0:05  </li>
     * </ul>
     */
    public void loadTreeData() {

    }

    //重新加载主界面信息
    public void reloadPpt(boolean flag) {
        //flag 为true的话，表示刷新，如果是false的话，表示不刷新
    }

    //重新加载所界面端的按钮状态，职位无效或者有效
    public void reloadButton() {
        ComponentUtils.doCheckButtonStatus(this);
    }

    @Override
    public PageControlVO getPgvo() {
        return null;
    }

    @Override
    public void setComponentDisable(boolean flag) {

    }

    @Override
    public void setPageValue(Object obj) {

    }
}
