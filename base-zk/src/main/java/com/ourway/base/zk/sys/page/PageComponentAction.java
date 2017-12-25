package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.*;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.oscache.OSCacheFactory;
import com.ourway.base.zk.oscache.ZKBaseCache;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.BillIdUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.CreateEvent;
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
public class PageComponentAction extends BaseWindow {
    protected Log info = LogFactory.getLog(PageComponentAction.class);
    BaseGrid componentGrid;
    /*所有的控件列表*/
    Map<String, Map<String, Object>> pageComponents = new HashMap<String, Map<String, Object>>();
    //控件布局列表
    Map<String, Map<String, Object>> layoutComponents = new HashMap<String, Map<String, Object>>();
    Map<String, BaseWindow> winComponents = new HashMap<String, BaseWindow>();
    BaseButton rightBtn = null;
    ComponentFormSelAction formEdit = null;//表单管理端
    GridUtils gridUtils;


    private String oldTemplateId = "";//老的模板编号

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        setWindowType(2);
        Tabbox tabbox = (Tabbox)getFellowIfAny("tabbox");
        tabbox.setWidth((ZKSessionUtils.getScreenWidth()-210)+"px");
        gridUtils = new GridUtils();
        init();
        BaseButton addBtn = (BaseButton) getFellowIfAny("addBtn");
        BaseButton removeBtn = (BaseButton) getFellowIfAny("removeBtn");
        BaseButton clsBtn = (BaseButton) getFellowIfAny("clsBtn");
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton importBtn = (BaseButton) getFellowIfAny("importBtn");
        BaseButton classBtn = (BaseButton) getFellowIfAny("classBtn");
        BaseButton saveAsBtn = (BaseButton) getFellowIfAny("saveAsBtn");
        BaseButton exportMbBtn = (BaseButton) getFellowIfAny("exportMbBtn");
        Listbox pageTemplate = (Listbox) getFellowIfAny("baseInfo_pageTemplate");
        componentGrid = (BaseGrid) getFellowIfAny("componentGrid");
        componentGrid.setSizedByContent(true);
        componentGrid.setSpan(true);
        initComponentGridHeader();
        addBtn.addEventListener(Events.ON_CLICK, this);
        removeBtn.addEventListener(Events.ON_CLICK, this);
        clsBtn.addEventListener(Events.ON_CLICK, this);
        saveBtn.addEventListener(Events.ON_CLICK, this);
        pageTemplate.addEventListener(Events.ON_SELECT, this);
        importBtn.addEventListener(Events.ON_CLICK, this);
        classBtn.addEventListener(Events.ON_CLICK, this);
        saveAsBtn.addEventListener(Events.ON_CLICK, this);
        exportMbBtn.addEventListener(Events.ON_CLICK, this);
        if (null != event.getArg().get("ppt"))
            init((Map<String, Object>) event.getArg().get("ppt"));
        if (!TextUtils.isEmpty(ppt.get("pageTemplate")))
            oldTemplateId = ppt.get("pageTemplate").toString();

        BaseTextbox pageNo = (BaseTextbox) getFellowIfAny("baseInfo_pageNo");
        pageNo.clearErrorMessage();
        if (TextUtils.isEmpty(pageNo.getText())) {
            pageNo.setText(BillIdUtils.getBillId("pageListId"));
        }
    }

    //页面传递过来的值，双击选中的
    private void init(Map<String, Object> rowData) {
        List<Object> params = new ArrayList<Object>();
        if (null == rowData.get("owid") || TextUtils.isEmpty(rowData.get("owid"))) {
            AlterDialog.alert("未传入owid");
            return;
        }
        params.add(rowData.get("owid").toString());
        FilterModel model = FilterModel.instance("owid", FilterModel.EQUALS, params);
        List<FilterModel> models = new ArrayList<FilterModel>(1);
        models.add(model);
        ResponseMessage mess = JsonPostUtils.executeAPI(models, "/sysPageApi/detailPage");
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            Map<String, Map<String, Object>> _componentMap = new HashMap<String, Map<String, Object>>();
            //获取配置成功
            ppt = (Map<String, Object>) mess.getBean();
            if (!TextUtils.isEmpty(ppt.get("pageParams"))) {
                if (ppt.get("pageParams") instanceof ArrayList) {
                    ppt.put("pageParams", com.ourway.base.utils.JsonUtil.toJson(((List) ppt.get("pageParams")).toArray()));
                }
            }
            if (null != ppt.get("components")) {
                List<Map<String, Object>> componentList = (List<Map<String, Object>>) ppt.get("components");
                for (Map<String, Object> map : componentList) {

                    if (!TextUtils.isEmpty(map.get("kjInitData"))) {
                        if (map.get("kjInitData") instanceof ArrayList) {
                            map.put("kjInitData", com.ourway.base.utils.JsonUtil.toJson(((List) map.get("kjInitData")).toArray()));
                        }
                    }

                    if (!TextUtils.isEmpty(map.get("kjDefaultData"))) {
                        if (map.get("kjDefaultData") instanceof ArrayList) {
                            map.put("kjDefaultData", com.ourway.base.utils.JsonUtil.toJson(((List) map.get("kjDefaultData")).toArray()));
                        }
                    }
                    if (!TextUtils.isEmpty(map.get("kjKzsx"))) {
                        if (map.get("kjKzsx") instanceof ArrayList) {
                            map.put("kjKzsx", com.ourway.base.utils.JsonUtil.toJson(((List) map.get("kjKzsx")).toArray()));
                        }
                    }
                    pageComponents.put(map.get("kjAttribute").toString(), map);
                    _componentMap.put(map.get("owid").toString(), map);
                }
            }
            if (null != ppt.get("layoutList")) {
                List<Map<String, Object>> layoutList = (List<Map<String, Object>>) ppt.get("layoutList");
                for (Map<String, Object> map : layoutList) {
                    if (!TextUtils.isEmpty(map.get("controlDbInt"))) {
                        if (map.get("controlDbInt") instanceof ArrayList) {
                            map.put("controlDbInt", com.ourway.base.utils.JsonUtil.toJson(((List) map.get("controlDbInt")).toArray()));
                        }
                    }
                    if (!TextUtils.isEmpty(map.get("controlDbclickEventParam"))) {
                        if (map.get("controlDbclickEventParam") instanceof ArrayList) {
                            map.put("controlDbclickEventParam", com.ourway.base.utils.JsonUtil.toJson(((List) map.get("controlDbclickEventParam")).toArray()));
                        }
                    }
                    if (!TextUtils.isEmpty(map.get("controlRowCss"))) {
                        if (map.get("controlRowCss") instanceof ArrayList) {
                            map.put("controlRowCss", com.ourway.base.utils.JsonUtil.toJson(((List) map.get("controlRowCss")).toArray()));
                        }
                    }
                    if (!TextUtils.isEmpty(map.get("components"))) {
                        List<Map<String, Object>> comps = (List<Map<String, Object>>) map.get("components");
                        for (Map<String, Object> comp : comps) {
                            //特殊处理几个json格式的字段，进行展现转换
                            if (!TextUtils.isEmpty(comp.get("eventDataContent"))) {
                                if (comp.get("eventDataContent") instanceof ArrayList) {
                                    comp.put("eventDataContent", com.ourway.base.utils.JsonUtil.toJson(((List) comp.get("eventDataContent")).toArray()));
                                }
                            }
                            if (!TextUtils.isEmpty(comp.get("compFontCss"))) {
                                if (comp.get("compFontCss") instanceof ArrayList) {
                                    comp.put("compFontCss", com.ourway.base.utils.JsonUtil.toJson(((List) comp.get("compFontCss")).toArray()));
                                }
                            }
                            if (!TextUtils.isEmpty(comp.get("pageRefOwid"))) {
                                if (null != _componentMap.get(comp.get("pageRefOwid").toString())) {
                                    Map<String, Object> _objMap = _componentMap.get(comp.get("pageRefOwid").toString());
                                    comp.put("pageRefOwid", _objMap.get("kjAttribute").toString());
                                    comp.put("pageRefOwidLabel", _objMap.get("kjName").toString());
                                }
                            }
                        }
                        map.put("dataList", comps);
                        map.remove("components");
                    }
                    layoutComponents.put(map.get("controlId").toString(), map);
                }
            }
            //ppt中的tab和group树形配置
            if (!TextUtils.isEmpty(ppt.get("tabList"))) {
                Map<String, Object> allMap = new HashMap<String, Object>(1);
                List<Map<String, Object>> tabList = (List<Map<String, Object>>) ppt.get("tabList");
                allMap.put("tabList", tabList);
//                Map<String,Map<String,Object>> _firstLevel = new HashMap<String,Map<String,Object>>();
                //这里处理两级的tab或者panel
//                for(Map<String,Object> map:tabList){
//                    if(TextUtils.isEmpty(map.get("controlParentId"))){
//                        _firstLevel.put(map.get("controlId").toString(),map);
//                    }
//                }
//                for(Map<String,Object> map:tabList){
//                    if(!TextUtils.isEmpty(map.get("controlParentId"))){
//                        Map<String,Object> _firstLevetTmp = _firstLevel.get(map.get("controlParentId").toString());
//                        if(null==_firstLevetTmp.get("subLevel"))
//                            _firstLevetTmp.put("subLevel",new ArrayList<Map<String,Object>>());
//                        ((List<Map<String,Object>>)_firstLevetTmp.get("subLevel")).add(map);
//                        _firstLevel.put(map.get("controlParentId").toString(),_firstLevetTmp);
//                    }
//                }
//                allMap.put("tabList",_firstLevel);
                layoutComponents.put("tabList", allMap);
            }
            bind2Page();
            displayPageComponents();
            if (!TextUtils.isEmpty(ppt.get("pageTemplate"))) {
                doCreateTabFromTemplate(ppt.get("pageTemplate").toString());
            }
        }
    }

    //创建控件列表
    private void initComponentGridHeader() {
        PageLayoutVO vo = new PageLayoutVO();
        vo.setControlCheck(1);
        vo.setControlIsdb(2);
        List<PageControlVO> pgvos = new ArrayList<PageControlVO>();

        PageControlVO vo1 = PageControlVO.instance(1, "中文名", "kjName", "中文名", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(2, "语言标签", "kjLabelid", "语言标签", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(3, "属性名", "kjAttribute", "属性名", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(4, "显示属性名", "kjAttributeDisplay", "显示属性名", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(5, "绑定属性名", "kjBindKey", "绑定属性名", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(6, "控件类型", "kjTypeLabel", "控件类型", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(7, "默认样式", "kjDefaultcss", "默认样式", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(8, "前缀", "kjPre", "前缀", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(9, "后缀", "kjAfter", "后缀", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(10, "实现类", "kjClass", "实现类", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(11, "控件格式", "kjTranslate", "控件格式", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(12, "数据类型", "kjDataTypeLabel", "数据类型", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(13, "报表参数", "kjReportParam", "报表参数", 8, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(14, "报表字段", "kjReportField", "报表字段", 8, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(15, "初始化", "kjDatasource", "初始化", 8, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(16, "API接口", "kjInterface", "API接口", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(17, "接口参数", "kjInitData", "接口参数", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(18, "自定义类", "kjClassImpl", "自定义类", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(19, "查询类型", "kjFilterTypeLabel", "查询类型", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(20, "隐藏", "kjHiddenLabel", "隐藏", 0, 0);
        pgvos.add(vo1);
        vo1 = PageControlVO.instance(21, "默认数据", "kjDefaultData", "默认数据", 0, 0);
        pgvos.add(vo1);
        Map<String, List<PageControlVO>> map = new HashMap<String, List<PageControlVO>>(1);
        map.put("1", pgvos);
        vo.setLayOutComponents(map);
        componentGrid.setLayoutVO(vo);
        gridUtils.setGridHeader(componentGrid, vo);

//        Columns cls = new Columns();
//        cls.setParent(componentGrid);
//        cls.setSizable(true);
//        createColumn(cls, "", "");
//        createColumn(cls, "中文名", "kjName");
//        createColumn(cls, "语言标签", "kjLabelid");
//        createColumn(cls, "属性名", "kjAttribute");
//        createColumn(cls, "显示属性名", "kjAttributeDisplay");
//        createColumn(cls, "控件类型", "kjTypeLabel");
//        createColumn(cls, "默认样式", "kjDefaultcss");
//        createColumn(cls, "前缀", "kjPre");
//        createColumn(cls, "后缀", "kjAfter");
//        createColumn(cls, "实现类", "kjClass");
//        createColumn(cls, "控件格式", "kjTranslate");
//
//        createColumn(cls, "数据类型", "kjDataTypeLabel");
//        createColumn(cls, "报表参数", "kjReportParamLabel");
//        createColumn(cls, "报表字段", "kjReportFieldLabel");
//
//        createColumn(cls, "初始化", "kjDatasourceLabel");
//        createColumn(cls, "API接口", "kjInterface");
//        createColumn(cls, "接口参数", "kjInitData");
//        createColumn(cls, "自定义类", "kjClassImpl");
//        createColumn(cls, "查询类型", "kjFilterTypeLabel");
//        createColumn(cls, "隐藏", "kjHiddenLabel");
//        createColumn(cls, "默认数据", "kjDefaultData");
    }

    //进入页面获取template的初始化信息
    private void init() {
        BaseListbox pageTemplate = (BaseListbox) getFellowIfAny("baseInfo_pageTemplate");
        ResponseMessage mess = JsonPostUtils.executeAPI("/sysPageTemplateApi/listTemplate");
        Listitem item = new Listitem("---请选择---", "");
        item.setParent(pageTemplate);
        if (TextUtils.isEmpty(ppt.get("pageTemplate")))
            item.setSelected(true);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            List<Map<String, Object>> objs = (List<Map<String, Object>>) mess.getBean();
            for (Map<String, Object> obj : objs) {
                item = new Listitem(obj.get("templateName").toString(), obj.get("owid").toString());
                item.setParent(pageTemplate);
                if (!TextUtils.isEmpty(ppt.get("pageTemplate")) && ppt.get("pageTemplate").toString().equals(obj.get("templateName").toString())) {
                    item.setSelected(true);
                }
            }
        }

        initPageInit();
    }

    private void initPageInit() {
        List<Map<String, Object>> datas = DicUtil.listDic(2);
        BaseCombobox pageInit = (BaseCombobox) getFellowIfAny("baseInfo_pageInit");
        pageInit.getChildren().clear();
        if (null != datas)
            for (Map<String, Object> data : datas) {
                Comboitem ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(pageInit);
            }
    }

    //传入grid，删除选中的内容
    private void removeComponetGrid() {
        List<Row> rows = componentGrid.getSelectRows();
        for (Row row : rows) {
            Map<String, Object> map = (Map<String, Object>) row.getValue();
            pageComponents.remove(map.get("kjAttribute").toString());
            componentGrid.removeRow(row);
        }
    }

    //导出jasper模板
    private void exportDetailJasperMb() {
        BaseTextbox baseInfo_pageCa = (BaseTextbox) getFellowIfAny("baseInfo_pageCa");
        BaseTextbox baseInfo_pageNo = (BaseTextbox) getFellowIfAny("baseInfo_pageNo");
        if (TextUtils.isEmpty(baseInfo_pageCa.getText()) || TextUtils.isEmpty(baseInfo_pageNo.getText())) {
            AlterDialog.alert("必须输入页面编号和页面CA");
            return;
        }
        int flag = JasperXMLUtils.generateDefault(baseInfo_pageCa.getText(), baseInfo_pageNo.getText());
        if (flag != 0) {
            AlterDialog.alert("不能生成报表，未保存或是列表");
            return;
        }
        AlterDialog.alert("生成成功");
        // getPageCA()
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("addBtn")) {
                componentDetailPage(null, null);
            }
            if (comp.getId().equals("exportMbBtn")) {
                exportDetailJasperMb();
            }
            if (comp.getId().equals("closeBtn") || comp.getId().equals("clsBtn")) {
                //关闭
                Component _comp = Path.getComponent("/mainWin");
                MainAction root = null;
                if (_comp instanceof MainAction) {
                    root = (MainAction) _comp;
                }
                root.closeTabWin(this);
            }
            if (comp.getId().equals("removeBtn")) {
                //删除选中的row
                removeComponetGrid();
            }
            if (comp.getId().equals("saveBtn") && event.getName().equals(Events.ON_CLICK)) {
                //调用保存接口，保存页面配置
                saveAll();
            }
            if (comp.getId().equals("saveAsBtn") && event.getName().equals(Events.ON_CLICK)) {
                //调用保存接口，保存页面配置
                saveAsAll();
            }
            if (comp.getId().equals("importBtn") && event.getName().equals(Events.ON_CLICK)) {
                //调用保存接口，保存页面配置
                importBtnList();
            }
            if (comp.getId().equals("classBtn") && event.getName().equals(Events.ON_CLICK)) {
                //从公用表中导入类相关属性
                classImport();
            }
        }
        if (comp instanceof BaseListbox) {
            if (comp.getId().equals("baseInfo_pageTemplate")) {
                BaseListbox pageTemplate = (BaseListbox) comp;
                templateChangeEvent(pageTemplate);
            }
        }
        if (comp instanceof Row) {
            //表示是表格双击
            rowDbClickEvent((Row) comp);
        }

    }


    //选择模板后，触发相应的事件
    private void templateChangeEvent(BaseListbox pageTemplate) {
        String tempId = pageTemplate.getSelectedItem().getValue();
        if (!oldTemplateId.equals(tempId)) {
            if (!TextUtils.isEmpty(oldTemplateId)) {
                removeAllIntabAndTabpanel(oldTemplateId);
            }
        }
        oldTemplateId = tempId;
        doCreateTabFromTemplate(tempId);
    }

    //删除指定模板的内容
    private void removeAllIntabAndTabpanel(String tabsId) {
        Tabs tabs = (Tabs) getFellowIfAny("tabs");
        Tabpanels tabpanels = (Tabpanels) getFellowIfAny("tabpanels");
        List<Tab> subTabs = tabs.getChildren();
        List<Tabpanel> tabpanels1 = tabpanels.getChildren();
        List<Tab> removeTabs = new ArrayList<Tab>();
        List<Tabpanel> removeTabpanels = new ArrayList<Tabpanel>();
        for (Tab subTab : subTabs) {
            if (!subTab.getId().equals("baseInfo")) {
                removeTabs.add(subTab);
            }
        }
        if (null != tabpanels1 && tabpanels1.size() > 0) {
            for (Tabpanel subTab : tabpanels1) {
                if (!subTab.getId().equals("baseInfo_panel")) {
                    removeTabpanels.add(subTab);
                }
            }
        }
        if (null != removeTabpanels && removeTabpanels.size() > 0) {
            for (Tabpanel removeTabpanel : removeTabpanels) {
                tabpanels.removeChild(removeTabpanel);
            }
        }
        if (null != removeTabs && removeTabs.size() > 0) {
            for (Tab removeTab : removeTabs) {
                tabs.removeChild(removeTab);
            }
        }
        layoutComponents.clear();
    }

    //根据所选择的模板来创建动态的tab页面
    private void doCreateTabFromTemplate(String templateId) {
        Map<String, Object> _params = new HashMap<String, Object>(1);
        _params.put("pageTemplate", templateId);
        ResponseMessage mess = JsonPostUtils.executeAPI(_params, "/sysPageTemplateApi/detailTemplate");
        if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
            Map<String, Object> _bean = (Map<String, Object>) mess.getBean();
            if (null != _bean.get("dataList")) {
                createTab((List<Map<String, Object>>) _bean.get("dataList"));
            }
        }
    }

    //双击事件
    private void rowDbClickEvent(Row row) {
        BaseGrid baseGrid = ZkUtils.getBaseGrid(row);
        if (baseGrid.getId().equals("componentGrid")) {
            Map<String, Object> rowData = row.getValue();
            componentDetailPage(rowData, row);
        }
    }

    //打开控件详情
    private void componentDetailPage(Object param, Row row) {
        try {
            Map<String, Object> _params = new HashMap<String, Object>();
            if (null != param)
                _params.put("ppt", param);
            Component winEdit = Executions.createComponents("/sys/page/component.do", null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                _win.setClosable(true);
                _win.doModal();
                if (_win.isClosePage()) {
                    if (TextUtils.isEmpty(_win.getPpt().get("kjAttribute"))) {
                        AlterDialog.alert("未传入控件属性");
                        return;
                    }
                    if (null == row) {
                        if (null != pageComponents.get(_win.getPpt().get("kjAttribute"))) {
                            AlterDialog.alert("传入重复的控件属性");
                            return;
                        }
                        //表示新增
                        gridUtils.addNewLastRow(_win.getPpt(), componentGrid, this);
//                        componentGrid.addNewRow(_win.getPpt(), 1, true);
                    } else {
                        //表示修改
                        //若控件属性不同，则去掉老的控件属性
                        Map<String, Object> _tmpObj = (Map<String, Object>) row.getValue();
                        if (!_tmpObj.get("kjAttribute").toString().equals(_win.getPpt().get("kjAttribute").toString())) {
                            pageComponents.remove(_tmpObj.get("kjAttribute").toString());
                        }
                        gridUtils.refreshRow(_win.getPpt(), componentGrid, row);
//                        componentGrid.refreshRow(_win.getPpt(), row);
                        row.setValue(_win.getPpt());
                    }
                    pageComponents.put(_win.getPpt().get("kjAttribute").toString(), _win.getPpt());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加新控件后返回列表
    private void displayPageComponents() {
        Set<String> keys = pageComponents.keySet();
        componentGrid.clearRows();
        for (String key : keys) {
            gridUtils.addNewLastRow(pageComponents.get(key), componentGrid, this);
        }
    }

    //导入系统中所需要的所有按钮到控件列表中
    private void importBtnList() {
        List<Map<String, Object>> datas = DicUtil.listDic(10);
        for (Map<String, Object> data : datas) {
            if (TextUtils.isEmpty(data.get("dicVal3")))
                continue;
            if (null != pageComponents.get(data.get("dicVal3").toString())) {
                continue;
            }
            Map<String, Object> componentMap = new HashMap<String, Object>();
            componentMap.put("kjAttribute", data.get("dicVal3").toString());
            componentMap.put("kjName", data.get("dicVal1"));
            componentMap.put("kjLabelid", data.get("dicVal2"));
            //按钮的扩展属性放入的临时cache中
            OSCacheFactory.getInstance().put(data.get("dicVal3").toString(),data.get("dicVal7"));
            if (!TextUtils.isEmpty(data.get("dicVal5"))) {
                if (data.get("dicVal5").toString().equalsIgnoreCase("BaseMultipFileUpload")) {
                    componentMap.put("kjTypeLabel", "文件中心");
                    componentMap.put("kjType", 20);
                } else {
                    componentMap.put("kjTypeLabel", "Button");
                    componentMap.put("kjType", 2);
                }
            } else {
                componentMap.put("kjTypeLabel", "Button");
                componentMap.put("kjType", 2);
            }
            componentMap.put("kjDataTypeLabel", "String");
            componentMap.put("kjDataType", 0);
            componentMap.put("kjDefaultcss", TextUtils.isEmpty(data.get("dicVal4")) ? "" : data.get("dicVal4").toString());
            componentMap.put("kjIconclass", TextUtils.isEmpty(data.get("dicVal6")) ? "" : data.get("dicVal6").toString());
            gridUtils.addNewLastRow(componentMap, componentGrid, this);
            pageComponents.put(data.get("dicVal3").toString(), componentMap);
        }
    }

    //整体保存提交
    private void saveAll() {
        //Map<String, Object> params = new HashMap<String, Object>();
        bindAll2Ppt(true);

        Set<String> keys = pageComponents.keySet();
        List<Map<String, Object>> components = new ArrayList<Map<String, Object>>(keys.size());
        for (String key : keys) {
            components.add(pageComponents.get(key));
        }
        ppt.put("component", components);
        //控件布局列表
        keys = layoutComponents.keySet();
//        List<Map<String, Object>> layoutComponent = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> layoutList = new ArrayList<Map<String, Object>>(keys.size());
        for (String key : keys) {
            BaseWindow window = winComponents.get(key);
            if (null == window) {
                continue;
                //AlterDialog.alert("未配置");
                //return;
            }
            Map<String, Object> objectMap = window.getPpt();
            layoutList.add(objectMap);
        }
        ppt.put("layout", layoutList);

        ResponseMessage mess = JsonPostUtils.executeAPI(ppt, "/sysPageApi/savePage");
        if (null != mess)
            if (mess.getBackCode() == 0) {
                AlterDialog.alert("页面添加成功");
                ppt.put("owid", mess.getBean().toString());
                if (null != getBaseGrid()) {
                    if (null == getBaseGrid().getDbRow())
                        getBaseGrid().addNewRow(getPpt());
                    else
                        getBaseGrid().refreshRow(getPpt(), getBaseGrid().getDbRow());
                }
                Component _comp = Path.getComponent("/mainWin");
                MainAction root = null;
                if (_comp instanceof MainAction) {
                    root = (MainAction) _comp;
                }
                root.closeTabWin(this);
            } else {
                AlterDialog.alert(mess.getErrorMess());
            }
        else
            AlterDialog.alert("系统内部错误");
    }

    public void saveAsAll() {
        if (TextUtils.isEmpty(ppt.get("owid"))) {
            AlterDialog.alert("您还未保存，不能使用另存功能");
            return;
        }
        ResponseMessage mess = JsonPostUtils.executeAPI(ppt, "/sysPageApi/saveAsPage");
        if (null == mess || mess.getBackCode() != 0) {
            AlterDialog.alert("复制失败");
            return;
        }
        String newOwid = mess.getBean().toString();
        Map<String, Object> newPpt = new HashMap<String, Object>(1);
        newPpt.put("owid", newOwid);
        try {
            Map<String, Object> _params = new HashMap<String, Object>();
            _params.put("ppt", newPpt);
            _params.put("pageCa", "/sys/page/pageComponent.do");
            _params.put("pageType", 5);
            _params.put("gridId", "dataList");
            _params.put("title", "配置另存");
            Component comp = Path.getComponent("/mainWin");
            MainAction root = null;
            if (comp instanceof MainAction) {
                root = (MainAction) comp;
            }
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }

            Component winEdit = Executions.createComponents("/sys/page/pageComponent.do", null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss")))
                    _win.setStyle(_params.get("windowCss").toString());
                String tabId = root.openNewTab(_win, _params.get("title").toString());
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(this);
//                List<String> tabList = ZkUtils.doHandleTabid(this,tabId);
//                _win.setTabId(tabList);
//                _win.setTabId(tabId);
                if (_win.isClosePage()) {
                    root.closeTabWin(_win);
                }
                if (_win.isDetach()) {
                    root.closeTabWin(_win);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //处理多tab页面的时候，有多个dataList
    private void doDataListInTab(List<Map<String, Object>> controls) {
        Set<String> set = layoutComponents.keySet();
        Map<String, Object> _map = new HashMap<String, Object>();
        for (Map<String, Object> map : controls) {
            _map.put(map.get("controlId").toString(), map);
        }
        for (String s : set) {
            if (s.startsWith("dataList") && null == _map.get(s)) {
                //表示不再配置文件中，需要动态添加
                Map<String, Object> controlMap = new HashMap<String, Object>();
                controlMap.put("controlId", s);
                controlMap.put("controlName", s);
                controlMap.put("controlDesc", s);
                controlMap.put("controlType", 1);
                controls.add(controlMap);
            }
        }
    }

    //根据模板创建不同的tab页面
    private void createTab(List<Map<String, Object>> controls) {
        doDataListInTab(controls);
        Tabs tabs = (Tabs) getFellowIfAny("tabs");
        Collections.sort(controls, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return o1.get("controlId").toString().compareTo(o2.get("controlId").toString());
            }
        });
        //做删除tab的准备
        List<Component> removeTabs = new ArrayList<Component>();
        for (Map<String, Object> control : controls) {
            if (null != getFellowIfAny(control.get("controlId").toString())) {
                Tab _tab = (Tab) getFellowIfAny(control.get("controlId").toString());
                removeTabs.add(_tab);
                removeTabs.add(getFellowIfAny(control.get("controlId").toString() + "_panel"));
            }
        }
        if (removeTabs.size() > 0) {
            for (Component comp : removeTabs) {
                tabs.removeChild(comp);
            }
        }
        for (Map<String, Object> control : controls) {
            Tab tab = PageSetUtils.doCreateTab(control);
            tab.setParent(tabs);
            createTabpanel(control);
        }

    }

    //共多tab页面调用，产生多个datalist
    public void doCreateMulDataList(int num) {
        if (num > 10) {
            AlterDialog.alert("最多创建10个grid");
            return;
        }

        String tabId = "";
        Tabs tabs = (Tabs) getFellowIfAny("tabs");
        for (int index = 1; index <= num; index++) {
            tabId = "dataList" + index;
            Tab _tab = (Tab) getFellowIfAny(tabId);
            if (null == _tab) {
                //如果不存在，则进行新加
                Map<String, Object> control = new HashMap<String, Object>();
                control.put("controlId", tabId);
                control.put("controlName", tabId);
                control.put("controlDesc", tabId);
                control.put("controlType", 1);
                Tab tab = PageSetUtils.doCreateTab(control);
                tab.setParent(tabs);
                createTabpanel(control);
            }
        }
        //删除多余num的值
        if (num < 10) {
            List<Component> removeTabs = new ArrayList<Component>();
            for (int i = num + 1; i <= 10; i++) {
                if (null != getFellowIfAny("dataList" + i)) {
                    Tab _tab = (Tab) getFellowIfAny("dataList" + i);
                    removeTabs.add(_tab);
                    removeTabs.add(getFellowIfAny("dataList" + i + "_panel"));
                }
            }
            if (removeTabs.size() > 0) {
                for (Component comp : removeTabs) {
                    tabs.removeChild(comp);
                }
            }
        }

    }

    //创建TabPanel的内容，根据不同的控件类别来创建
    private void createTabpanel(Map<String, Object> control) {
        String tabPanelId = control.get("controlId").toString();
        Map<String, Object> data = layoutComponents.get(tabPanelId);
        if (null == data)
            data = new HashMap<String, Object>();
        data.put("controlId", tabPanelId);
        layoutComponents.put(tabPanelId, data);
        Tabpanel tabpanel = new Tabpanel();
        tabpanel.setId(tabPanelId + "_panel");
        tabpanel.setParent(getFellowIfAny("tabpanels"));

        //根据grid的属性类配置不同的界面，grid分为数据grid和布局grid
        if (null == control.get("controlType"))
            return;
        int _type = Integer.parseInt(control.get("controlType").toString());
        switch (_type) {
            case 0:
                //创建布局
//                createViewGrid(tabpanel);
                ComponentFormSelAction formEdit = (ComponentFormSelAction) Executions.createComponents("/sys/page/mainFormComponentSelect.do", null, data);
                tabpanel.appendChild(formEdit);
                winComponents.put(tabPanelId, formEdit);
                break;
            case 1:
                //创建数据表格
//                createDataGrid(tabpanel, data);
                ComponentGridSelAction gridEdit = (ComponentGridSelAction) Executions.createComponents("/sys/page/gridComponentSelect.do", null, data);
                tabpanel.appendChild(gridEdit);
                winComponents.put(tabPanelId, gridEdit);
                break;
            case 2:
                //groupbox的定义
//                createGroupBoxViewGrid(tabpanel, data);
                ComponentPanelSelAction panelEdit = (ComponentPanelSelAction) Executions.createComponents("/sys/page/panelComponentSelect.do", null, data);
                tabpanel.appendChild(panelEdit);
                winComponents.put(tabPanelId, panelEdit);
                break;
            case 3://主表内的细表，没有具体的功能
                ComponentGridSelAction smallGrid = (ComponentGridSelAction) Executions.createComponents("/sys/page/gridComponentSelect.do", null, data);
                tabpanel.appendChild(smallGrid);
                winComponents.put(tabPanelId, smallGrid);
//                createSmallDataGrid(tabpanel, data);
                break;
            case 4://鼠标右键操作

                break;
            case 5://设置按钮区
                ComponentButtonSelAction winEdit = (ComponentButtonSelAction) Executions.createComponents("/sys/page/buttonComponentSelect.do", null, data);
                tabpanel.appendChild(winEdit);
                winComponents.put(tabPanelId, winEdit);
                break;
            case 6://设置按钮区
                ComponentTreeSelAction treeEdit = (ComponentTreeSelAction) Executions.createComponents("/sys/page/treeComponentSelect.do", null, data);
                tabpanel.appendChild(treeEdit);
                winComponents.put(tabPanelId, treeEdit);
                break;
            case 7://bandbox
                BandboxComponentGridSelAction bandboxEdit = (BandboxComponentGridSelAction) Executions.createComponents("/sys/page/bandboxComponentSelect.do", null, data);
                tabpanel.appendChild(bandboxEdit);
                winComponents.put(tabPanelId, bandboxEdit);
                break;
            case 8://bandboxGrid
                ComponentBandboxGridSelAction bandboxGridSelActionEdit = (ComponentBandboxGridSelAction) Executions.createComponents("/sys/page/gridBandboxComponentSelect.do", null, data);
                tabpanel.appendChild(bandboxGridSelActionEdit);
                winComponents.put(tabPanelId, bandboxGridSelActionEdit);
                break;
            case 9://bandboxTree
                ComponentTreeBandboxSelAction bandboxTreeSelActionEdit = (ComponentTreeBandboxSelAction) Executions.createComponents("/sys/page/treeBandboxComponentSelect.do", null, data);
                tabpanel.appendChild(bandboxTreeSelActionEdit);
                winComponents.put(tabPanelId, bandboxTreeSelActionEdit);
                break;
            case 10://bandboxTree
                TabTreeAction tabTreeAction = (TabTreeAction) Executions.createComponents("/sys/page/tabTreeComponent.do", null, data);
                tabpanel.appendChild(tabTreeAction);
                winComponents.put(tabPanelId, tabTreeAction);
                break;
            case 11://ComponentTabFormSelAction
                ComponentTabFormSelAction tabFormSelActionAction = (ComponentTabFormSelAction) Executions.createComponents("/sys/page/tabFormComponentSelect.do", null, data);
                tabpanel.appendChild(tabFormSelActionAction);
                winComponents.put(tabPanelId, tabFormSelActionAction);
                break;
            case 12://ComponentTabFormSelAction
                TabComponentButtonSelAction tabComponentButtonSelAction = (TabComponentButtonSelAction) Executions.createComponents("/sys/page/tabbuttonComponentSelect.do", null, data);
                tabpanel.appendChild(tabComponentButtonSelAction);
                winComponents.put(tabPanelId, tabComponentButtonSelAction);
                break;

        }

//        displayGridData(tabPanelId);
    }

    public Map<String, Map<String, Object>> getPageComponents() {
        return pageComponents;
    }

    public void setPageComponents(Map<String, Map<String, Object>> pageComponents) {
        this.pageComponents = pageComponents;
    }

    public Map<String, Map<String, Object>> getLayoutComponents() {
        return layoutComponents;
    }

    public void setLayoutComponents(Map<String, Map<String, Object>> layoutComponents) {
        this.layoutComponents = layoutComponents;
    }

    private void classImport() {
        try {
            ComponentClassSelect _win = (ComponentClassSelect) Executions.createComponents("/sys/page/inputClassName.do", null, null);

            _win.setClosable(true);
            _win.doModal();
            if (_win.isClosePage()) {
                Map<String, Object> datas = _win.getPpt();
                if (null != datas && null != datas.get("datas")) {
                    List<Map<String, Object>> _dts = (List<Map<String, Object>>) datas.get("datas");
                    Map<String, Object> _ppt = new HashMap<String, Object>();
                    for (Map<String, Object> map : _dts) {
                        _ppt = new HashMap<String, Object>();
                        _ppt.put("kjName", TextUtils.isEmpty(map.get("attributeChname")) ? "" : map.get("attributeChname").toString());
//                        _ppt.put("kjLabelid", TextUtils.isEmpty(map.get("attributeName")) ? "" : map.get("attributeName").toString());
                        if (TextUtils.isEmpty(datas.get("classPreName")))
                            _ppt.put("kjAttribute", TextUtils.isEmpty(map.get("attributeName")) ? "" : map.get("attributeName").toString());
                        else
                            _ppt.put("kjAttribute", TextUtils.isEmpty(map.get("attributeName")) ? "" : datas.get("classPreName").toString() + "." + map.get("attributeName").toString());
                        //绑定属性为同一个
                        _ppt.put("kjBindKey",_ppt.get("kjAttribute"));
                        if (!TextUtils.isEmpty(map.get("attributeLabelId")))
                            _ppt.put("kjLabelid", map.get("attributeLabelId").toString());
                        else
                            _ppt.put("kjLabelid", map.get("attributeName").toString());
                        if (!TextUtils.isEmpty(map.get("atttributeKjtype"))) {
                            _ppt.put("kjType", map.get("atttributeKjtype").toString());

                            if (map.get("atttributeKjtype").toString().equalsIgnoreCase("4")) {
                                _ppt.put("kjTypeLabel", "Datebox");
                            }
                            if (map.get("atttributeKjtype").toString().equalsIgnoreCase("0")) {
                                _ppt.put("kjTypeLabel", "Textbox");
                            }
                            if (map.get("atttributeKjtype").toString().equalsIgnoreCase("5")) {
                                _ppt.put("kjTypeLabel", "Intbox");
                            }
                            if (map.get("atttributeKjtype").toString().equalsIgnoreCase("6")) {
                                _ppt.put("kjTypeLabel", "Decimalbox");
                            }
                        }
                        if (!TextUtils.isEmpty(map.get("attributeDataType"))) {
                            if (map.get("attributeDataType").toString().equals("String")) {
                                _ppt.put("kjDataTypeLabel", "String");
                            }
                            if (map.get("attributeDataType").toString().equals("Date")) {
                                _ppt.put("kjDataTypeLabel", "String");
                            }
                            if (map.get("attributeDataType").toString().equals("Integer")) {
                                _ppt.put("kjDataTypeLabel", "Integer");
                            }
                            if (map.get("attributeDataType").toString().equals("Byte")) {
                                _ppt.put("kjDataTypeLabel", "Byte");
                            }
                            if (map.get("attributeDataType").toString().equals("Double")) {
                                _ppt.put("kjDataTypeLabel", "Double");
                            }
                            if (map.get("attributeDataType").toString().equals("Decimal")) {
                                _ppt.put("kjDataTypeLabel", "Decimal");
                            }
                        }
                        if (null != pageComponents.get(map.get("attributeName").toString())) {
                            String className = _win.getClassName().toLowerCase() + ".";
                            _ppt.put("kjAttribute", className + _ppt.get("kjAttribute"));
//                            componentGrid.addNewRow(_ppt, 1, true);
                        } else {
//                            componentGrid.addNewRow(_ppt, 1, true);
                        }
                        gridUtils.addNewLastRow(_ppt, componentGrid, this);
                        pageComponents.put(_ppt.get("kjAttribute").toString(), _ppt);
                    }
                }
                //表示新增
//                    componentGrid.addNewRow(_win.getPpt(), 1, true);
//
//                    pageComponents.put(_win.getPpt().get("kjAttribute").toString(), _win.getPpt());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
