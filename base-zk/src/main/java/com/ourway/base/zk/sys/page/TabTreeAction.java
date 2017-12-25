package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.*;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:tabpanell配置</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class TabTreeAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TabTreeAction.class);
    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Treecell rootCell;
    private Menupopup menupopup;
    private Treeitem selItem = null; //选中的节点
    private Treechildren treeChild;

    List<Map<String, Object>> datas = null; //所有的数据
    Map<String, Map<String, Object>> identityListMap = new HashMap<String, Map<String, Object>>();

    BaseListbox tabType;
    BaseTextbox tabParent;
    BaseTextbox tabId;
    BaseTextbox tabName;
    BaseTextbox tabLabel;
    BaseIntbox tabGroup;
    BaseListbox tabClose;


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        tabType = (BaseListbox) getFellowIfAny("tabType");
        tabParent = (BaseTextbox) getFellowIfAny("tabParent");
        tabId = (BaseTextbox) getFellowIfAny("tabId");
        tabName = (BaseTextbox) getFellowIfAny("tabName");
        tabLabel = (BaseTextbox) getFellowIfAny("tabLabel");
        tabGroup = (BaseIntbox) getFellowIfAny("tabGroup");
        tabClose = (BaseListbox) getFellowIfAny("tabClose");

        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 200) + "px");
        tree.setHeight((ZKSessionUtils.getScreenHeight() - 240) + "px");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        rootItem.setValue(null);
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        rootCell = (Treecell) getFellowIfAny("rootCell");
        westLayout.setTitle("Tab-Panel树维护");
        rootCell.setLabel("Tab-Panel树维护");
        datas = new ArrayList<Map<String, Object>>();
        if (null != event.getArg().get("tabList")) {
            datas = (List<Map<String, Object>>) event.getArg().get("tabList");
//            Set<String> keys = level.keySet();
//            for (String key : keys) {
//                datas.add(level.get(key));
//            }
        }
        if (datas.size() > 0)
            initTtree(datas);

        BaseButton generateBtn = (BaseButton) getFellowIfAny("generateBtn");
        BaseButton confirmBtn = (BaseButton) getFellowIfAny("confirmBtn");
        BaseButton removeBtn = (BaseButton) getFellowIfAny("removeBtn");
        BaseButton clearBtn = (BaseButton) getFellowIfAny("clearBtn");
        clearBtn.addEventListener(Events.ON_CLICK, this);
        generateBtn.addEventListener(Events.ON_CLICK, this);
        confirmBtn.addEventListener(Events.ON_CLICK, this);
        removeBtn.addEventListener(Events.ON_CLICK, this);
    }

    private List<Map<String, Object>> doTreeMap(List<Map<String, Object>> allDatas) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Map<String, Object>> _firstLevel = new HashMap<String, Map<String, Object>>();
        //这里处理两级的tab或者panel
        for (Map<String, Object> map : allDatas) {
            if (TextUtils.isEmpty(map.get("controlParentId"))) {
                map.put("subLevel", new ArrayList<Map<String, Object>>());
                _firstLevel.put(map.get("controlId").toString(), map);
            }
        }
        for (Map<String, Object> map : allDatas) {
            if (!TextUtils.isEmpty(map.get("controlParentId"))) {
                Map<String, Object> _firstLevetTmp = _firstLevel.get(map.get("controlParentId").toString());
                if (null == _firstLevetTmp.get("subLevel"))
                    _firstLevetTmp.put("subLevel", new ArrayList<Map<String, Object>>());
                ((List<Map<String, Object>>) _firstLevetTmp.get("subLevel")).add(map);
                _firstLevel.put(map.get("controlParentId").toString(), _firstLevetTmp);
            }
        }
        Set<String> set = _firstLevel.keySet();
        for (String s : set) {
            result.add(_firstLevel.get(s));
        }
        return result;
    }

    private void initTtree(List<Map<String, Object>> allDatas) {
        List<Map<String, Object>> displayDatas = doTreeMap(allDatas);
        treeChild.getChildren().clear();
        identityListMap = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> displayData : displayDatas) {
            Treeitem item = new Treeitem();
            item.addEventListener(Events.ON_CLICK, this);
            item.setId(displayData.get("controlId").toString());
            if (displayData.get("controlClass").toString().equals("0"))
                item.setLabel("Tab页：" + displayData.get("controlSplitpage") + "组" + displayData.get("controlId") + "-" + displayData.get("controlSclass"));
            else
                item.setLabel("Panel页：" + displayData.get("controlId") + "-" + displayData.get("controlSclass"));

            item.setParent(treeChild);
            item.setValue(displayData);
            if (null != selItem && selItem.getId().equalsIgnoreCase(item.getId()))
                item.setSelected(true);
            identityListMap.put(item.getId(), displayData);

            if (null != displayData.get("subLevel")) {
                Treechildren _tc = new Treechildren();
                _tc.setParent(item);
                item.setOpen(true);
                List<Map<String, Object>> subLevelList = (List<Map<String, Object>>) displayData.get("subLevel");
                for (Map<String, Object> map : subLevelList) {
                    item = new Treeitem();
                    item.setParent(_tc);
                    item.setValue(map);
                    item.addEventListener(Events.ON_CLICK, this);
                    if (map.get("controlClass").toString().equals("0"))
                        item.setLabel("Tab页：" + map.get("controlSplitpage") + "组" + map.get("controlId") + "-" + map.get("controlSclass"));
                    else
                        item.setLabel("Panel页：" + map.get("controlId") + "-" + map.get("controlSclass"));
                    item.setId(map.get("controlId").toString());
                    if (null != selItem && selItem.getId().equalsIgnoreCase(item.getId()))
                        item.setSelected(true);
                    identityListMap.put(item.getId(), map);
                }
            }
        }
    }

    //判断是否可以作为父亲节点
    private boolean isParent(String id) {
        Collection<Treeitem> allItems = tree.getItems();
        for (Treeitem item : allItems) {
            if (item.getId().equalsIgnoreCase(id) && item.getParentItem().getId().equals("rootItem"))
                return true;
        }
        return false;

    }

    private void saveAll() {

        this.setClosePage(true);
        detach();
    }


    public Map<String, Object> getPpt() {
        ppt.put("TabDatas", datas);
        return ppt;
    }

    private void displayItemData(Map<String, Object> itemVal) {
        tabType.setSelectedIndex(Integer.parseInt(itemVal.get("controlClass").toString()));
        tabId.setText(itemVal.get("controlId").toString());
        tabName.setText(itemVal.get("controlSclass").toString());
        tabLabel.setText(itemVal.get("controlLabel").toString());
        tabParent.setText(TextUtils.isEmpty(itemVal.get("controlParentId")) ? "" : itemVal.get("controlParentId").toString());
        if(TextUtils.isEmpty(itemVal.get("controlHeight")))
            tabClose.setSelectedIndex(0);
        else
            tabClose.setSelectedIndex(Integer.parseInt(itemVal.get("controlHeight").toString()));
        if (!TextUtils.isEmpty(itemVal.get("controlSplitpage")))
            tabGroup.setText(itemVal.get("controlSplitpage").toString());
    }

    private void saveLeftItem() {
        if (TextUtils.isEmpty(tabId.getText())) {
            throw new WrongValueException(tabId, "必填");
        }
        if (TextUtils.isEmpty(tabName.getText())) {
            throw new WrongValueException(tabName, "必填");
        }
        if (TextUtils.isEmpty(tabLabel.getText())) {
            throw new WrongValueException(tabLabel, "必填");
        }
        if (null == selItem) {
            //表示新增，不能重复
            if (null != identityListMap.get(tabId.getText())) {
                throw new WrongValueException(tabId, "控件id重复,请更换");
            }
        } else {
            //表示修改，如果修改的id的话
            if (!selItem.getId().equalsIgnoreCase(tabId.getText())) {
                if (null != identityListMap.get(tabId.getText())) {
                    throw new WrongValueException(tabId, "控件id重复,请更换");
                }
            }
        }
        if (tabType.getSelectedIndex() == 0 && TextUtils.isEmpty(tabGroup.getText())) {
            throw new WrongValueException(tabGroup, "Tab必须输入组号");
        }
        //判断，如果填写了父亲节点
        if (!TextUtils.isEmpty(tabParent.getText())) {
            if (null == identityListMap.get(tabParent.getText())) {
                throw new WrongValueException(tabParent, "不存在父控件id，请重输");
            }
            if (!isParent(tabParent.getText())) {
                throw new WrongValueException(tabParent, "最多只能两级，请重输");
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != selItem)
            map = (Map<String, Object>) selItem.getValue();
        map.put("controlClass", tabType.getSelectedIndex());
        map.put("controlId", tabId.getText());
        map.put("controlSclass", tabName.getText());
        map.put("controlLabel", tabLabel.getText());
        map.put("controlParentId", tabParent.getText());
        map.put("controlSplitpage", tabGroup.getText());
        map.put("controlHeight", tabClose.getSelectedIndex());
        map.put("controlType", -1);

        if (null == identityListMap.get(tabId.getText()))
            datas.add(map);
        else {
            //表示修改
            for (Map<String, Object> map1 : datas) {
                if (map1.get("controlId").toString().equalsIgnoreCase(tabId.getText())) {
                    map1.put("controlClass", tabType.getSelectedIndex());
                    map1.put("controlId", tabId.getText());
                    map1.put("controlSclass", tabName.getText());
                    map1.put("controlLabel", tabLabel.getText());
                    map1.put("controlParentId", tabParent.getText());
                    map1.put("controlSplitpage", tabGroup.getText());
                    map.put("controlHeight", tabClose.getSelectedIndex());
                    map1.put("controlType", -1);
                    break;
                }
            }
        }
        initTtree(datas);
        selItem = null;
        clear();
        AlterDialog.alert("操作成功");

    }

    private void clear() {
        tabType.setSelectedIndex(0);
        tabId.setText("");
        tabName.setText("");
        tabLabel.setText("");
        tabParent.setText("");
        tabGroup.setText("");
    }

    private void removeTab() {
        if (null != selItem) {
            Map<String, Object> map = (Map<String, Object>) selItem.getValue();
            Iterator<Map<String, Object>> iterator = datas.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> inMap = iterator.next();
                if (inMap.get("controlId").toString().equalsIgnoreCase(selItem.getId())) {
                    iterator.remove();
                }
                if(!TextUtils.isEmpty(inMap.get("controlParentId"))&&inMap.get("controlParentId").toString().equalsIgnoreCase(selItem.getId())){
                    iterator.remove();
                }
            }
            initTtree(datas);
            clear();
            AlterDialog.alert("操作成功");
        }
    }

    private void generateDataList(){
        BaseIntbox gridNum = (BaseIntbox)getFellowIfAny("gridNum");
        if(null==gridNum.getValue()&&gridNum.getValue()<=0){
            throw new WrongValueException(gridNum,"请输入grid的个数");
        }
        int num = gridNum.getValue();
        PageComponentAction pageComponentAction = getParentPage(getParent());
        pageComponentAction.doCreateMulDataList(num);
        AlterDialog.alert("操作成功");
    }

    private PageComponentAction getParentPage(Component component){
        if(component instanceof PageComponentAction){
            return (PageComponentAction)component;
        }else
            return getParentPage(component.getParent());
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (event.getTarget() instanceof Treeitem) {
            //如果是树
            Treeitem item = (Treeitem) event.getTarget();
            selItem = item;
            displayItemData((Map<String, Object>) item.getValue());
        }
        if (event.getTarget().getId().equals("confirmBtn")) {
            saveLeftItem();
        }
        if (event.getTarget().getId().equals("removeBtn")) {
            removeTab();
        }
        if (event.getTarget().getId().equals("clearBtn")) {
            selItem = null;
            clear();
        }
        if (event.getTarget().getId().equals("generateBtn")) {
           //产生dataGrid
            generateDataList();
        }


    }


}
