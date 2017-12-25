package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseSpinner;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.template.utils.ComponentsBandbox;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.AlterDialog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;

import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ComponentFormSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentFormSelAction.class);


    //所有的控件，当前页面中的
    Map<String, BaseButton> componentMap = new HashMap<String, BaseButton>();
    PageComponentAction pageComponentAction;
    List<String> selComponents = new ArrayList<String>();
    Div buttonResult;//显示控件用的区域
    Panelchildren button_group1;//默认第一个分组

    @Override
    public Map<String, Object> getPpt() {
        bindAll2Ppt(false);
        Set<String> set = componentMap.keySet();
        if (null != set && set.size() > 0) {
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(set.size());
            for (String s : set) {
                BaseButton btn = componentMap.get(s);
                dataList.add(btn.getExternObject());
            }
            Collections.sort(dataList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    int fz1 = Integer.parseInt(o1.get("compStartRow").toString());
                    int fz2 = Integer.parseInt(o2.get("compStartRow").toString());
                    double order1 = Double.parseDouble(o1.get("compOrder").toString());
                    double order2 = Double.parseDouble(o2.get("compOrder").toString());
                    int fzz = fz1 - fz2;
                    if (fzz != 0)
                        return fzz;
                    else
                        return (int) ((order1 - order2) * 100);
                }
            });
            int index = 1;
            int oldfz = 1;
            for (Map<String, Object> map : dataList) {
                if (Integer.parseInt(map.get("compStartRow").toString()) != oldfz) {
                    index = 1;
                    oldfz = Integer.parseInt(map.get("compStartRow").toString());
                }
                map.put("compOrder", index);
                index++;
            }
            ppt.put("dataList", dataList);
        }
//            ppt.put("dataList", dataList);
//        }
        return ppt;
    }

    /*获取其上级页面*/
    public PageComponentAction getParentAction(Component component) {
        if (component instanceof PageComponentAction)
            return (PageComponentAction) component;
        else
            return getParentAction(component.getParent());
    }

    /*bandbox打开的时候，每次获取哪些控件选中了，哪些没有选中*/
    public List<Map<String, Object>> listNotUseComponents() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Map<String, Object>> components = pageComponentAction.getPageComponents();
        Set<String> set = components.keySet();
        for (String s : set) {
            if (!selComponents.contains(s))
                result.add(components.get(s));
        }
        return result;
    }

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        pageComponentAction = getParentAction(this.getParent());
        buttonResult = (Div) getFellowIfAny("button_result");
        button_group1 = (Panelchildren) getFellowIfAny("button_group1");
        if (null != event.getArg()) {
            ppt = (Map<String, Object>) event.getArg();
            initPage();
        }
        BaseButton addBtn = (BaseButton) getFellowIfAny("button_addBtn");
        addBtn.addEventListener(Events.ON_CLICK, this);
    }

    private void initPage() {
        if (null == ppt)
            return;
        bind2Page();
        int total = 0;
        double d = 0;
        if (null != ppt.get("dataList")) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) ppt.get("dataList");
            Collections.sort(datas, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    int flag = (Integer) o1.get("compStartRow") - (Integer) o2.get("compStartRow");
                    if (flag == 0)
                        return (Integer) o1.get("compOrder") - (Integer) o2.get("compOrder");
                    else
                        return flag;
                }
            });
            for (Map<String, Object> data : datas) {
                total = 1;
                if (!TextUtils.isEmpty(data.get("compLable")))
                    total = total + Integer.parseInt(data.get("compLable").toString());
                if (!TextUtils.isEmpty(data.get("compCols")))
                    total = total + Integer.parseInt(data.get("compCols").toString());
                d = total * 90 / 12;
                BaseButton button = PageSetUtils.doCreateButton(data.get("compId").toString(), data.get("pageRefOwidLabel").toString(), "");
                button_group1 = doCreateChildren((Integer) data.get("compStartRow"));
                button.setParent(button_group1);
                button.setDraggable("true");
                button.setDroppable("true");
                button.setMold("bs");
                button.setSclass("btn-success");
                button.setWidth(d + "%");
                button.addEventListener(Events.ON_DROP, this);
                button.addEventListener(Events.ON_DOUBLE_CLICK, this);
                data.put("kjName", data.get("pageRefOwidLabel").toString());
                button.setExternObject(data);
                componentMap.put(data.get("compId").toString(), button);
                selComponents.add(data.get("compId").toString());
            }
        }
    }


    Panelchildren doCreateChildren(int rowNum) {
        for (int i = 1; i <= rowNum; i++) {
            Panelchildren btnGroup = (Panelchildren) getFellowIfAny("button_group" + i);
            if (null == btnGroup) {
                Panelchildren pc = doCreatePanel(i);
                if (i == rowNum)
                    return pc;
            } else {
                if (i == rowNum)
                    return btnGroup;
            }
        }
        return null;
    }

    Panelchildren doCreatePanel(int rowNum) {
        Panel panel = new Panel();
        panel.setSclass("panel-primary");
        panel.setTitle("第" + rowNum + "行");
        panel.setParent(buttonResult);
        Panelchildren pc = new Panelchildren();
        pc.setId("button_group" + rowNum);
        pc.setParent(panel);
        return pc;
    }

    //    添加新的控件
    void addButton2Grid() {
        ComponentsBandbox bandbox = (ComponentsBandbox) getFellowIfAny("button_controlList");
        BaseSpinner rowNum = (BaseSpinner) getFellowIfAny("button_rowNum");
        if (null == rowNum.getValue() || rowNum.getValue() <= 0) {
            throw new WrongValueException(rowNum, "请输入大于0的行数");
        }
        Panelchildren pn = doCreateChildren(rowNum.getValue());
        List<Map<String, Object>> datas = bandbox.getSelItems();
        if (null != datas && datas.size() > 0) {
            for (Map<String, Object> data : datas) {
                if (selComponents.contains(data.get("kjAttribute").toString())) {
                    continue;
                }
                BaseButton button = PageSetUtils.doCreateButton(data.get("kjAttribute").toString(), data.get("kjName").toString(), data.get("kjTypeLabel").toString());
                button.setParent(pn);
                button.setDraggable("true");
                button.setDroppable("true");
                button.setMold("bs");
                button.setSclass("btn-success");
                button.addEventListener(Events.ON_DROP, this);
                button.addEventListener(Events.ON_DOUBLE_CLICK, this);
                Map<String, Object> _data = new HashMap<String, Object>();
                _data.put("compStartRow", rowNum.getValue());
                _data.put("compOrder", getMaxOrder(pn));
                _data.put("compCols", 0);
                _data.put("compHbox", 0);
                _data.put("compLable", 0);
                _data.put("layoutRefOwid", data.get("layoutRefOwid"));
                _data.put("pageRefOwid", data.get("pageRefOwid"));
                _data.put("compId", data.get("kjAttribute"));
                _data.put("kjAttribute", data.get("kjAttribute"));
                _data.put("kjName", data.get("kjName"));
                _data.put("kjTypeLabel", data.get("kjTypeLabel"));
                if (!TextUtils.isEmpty(data.get("kjDataType")) && !data.get("kjDataType").toString().equals("0"))
                    _data.put("compPosition", 2);

                button.setExternObject(_data);
                componentMap.put(data.get("kjAttribute").toString(), button);
                selComponents.add(data.get("kjAttribute").toString());
            }
        } else {
            AlterDialog.alert("请选择需要加入的控件");
        }
    }

    double getMaxOrder(Panelchildren pn) {
        double maxOrder = 0;
        double _tmp = 0;
        List<Component> components = pn.getChildren();
        for (Component component : components) {
            if (component instanceof BaseButton) {
                BaseButton button = (BaseButton) component;
                if (null != button.getExternObject() && !TextUtils.isEmpty(button.getExternObject().get("compOrder"))) {
                    _tmp = new Double(button.getExternObject().get("compOrder").toString());
                    if (maxOrder < _tmp)
                        maxOrder = _tmp;
                }
            }
        }
        maxOrder++;
        return maxOrder;
    }

    //删除控件
    void removeButton(String kjAttribute) {
        BaseButton bt = findButton(kjAttribute);
        bt.getParent().removeChild(bt);
        componentMap.remove(kjAttribute);
        selComponents.remove(kjAttribute);
    }

    void updateButton(String kjAttribute, Map<String, Object> data) {
        BaseButton bt = findButton(kjAttribute);
        int total = 1;
        if (!TextUtils.isEmpty(data.get("compLable")))
            total = total + Integer.parseInt(data.get("compLable").toString());
        if (!TextUtils.isEmpty(data.get("compCols")))
            total = total + Integer.parseInt(data.get("compCols").toString());
        double d = total * 100 / 12;
        bt.setWidth(d + "%");
        Panelchildren pc = doCreateChildren(Integer.parseInt(data.get("compStartRow").toString()));
        if (!bt.getParent().getId().equals("button_group" + (Integer) data.get("compStartRow"))) {
            data.put("compOrder", pc.getChildren().size() + 1);
            bt.setParent(pc);
        }
        bt.setExternObject(data);
    }

    BaseButton findButton(String kjAttribute) {
        return componentMap.get(kjAttribute);
    }

    BaseButton findPreButton(BaseButton btn) {
        List<BaseButton> list = btn.getParent().getChildren();
        BaseButton preBtn = null;
        String attribute1 = btn.getExternObject().get("compId").toString();
        String attribute2 = "";

        for (BaseButton button : list) {
            attribute2 = button.getExternObject().get("compId").toString();
            if (attribute1.equals(attribute2)) {
                return preBtn;
            } else
                preBtn = button;
        }
        return preBtn;
    }

    /*处理两个控件移动*/
    private void doMoveButton(BaseButton bt, Event event) {
        DropEvent dropEvent = (DropEvent) event;
        if (dropEvent.getDragged() instanceof BaseButton) {
            BaseButton dragBtn = (BaseButton) dropEvent.getDragged();

            Map<String, Object> sourBtn = dragBtn.getExternObject();
            Map<String, Object> destBtn = bt.getExternObject();
            double destOrder = Double.parseDouble(destBtn.get("compOrder").toString());
            int destRow = Integer.parseInt(destBtn.get("compStartRow").toString());
//            int hbox = Integer.parseInt(destBtn.get("compHbox").toString());
            BaseButton prebtn = findPreButton(bt);
            if (null == prebtn) {
                sourBtn.put("compOrder", destOrder - 0.1);
                sourBtn.put("compStartRow", destRow);
//                sourBtn.put("compHbox",hbox);
            } else {
                double sorder = Double.parseDouble(prebtn.getExternObject().get("compOrder").toString());
                double sorder1 = (sorder + destOrder) / 2;
                sourBtn.put("compOrder", sorder1);
                sourBtn.put("compStartRow", destRow);
//                sourBtn.put("compHbox",hbox);
            }
            dragBtn.setExternObject(sourBtn);
//            dragBtn.setStyle("color:red;");
            bt.getParent().insertBefore(dragBtn, bt);

//            Map<String, Object> sourBtn = bt.getExternObject();
//            Map<String, Object> destBtn = dragBtn.getExternObject();
//            int sourOrder = Integer.parseInt(sourBtn.get("compOrder").toString());
//            int sourRow = Integer.parseInt(sourBtn.get("compStartRow").toString());
//            int sourCols = 0;
//            if (!TextUtils.isEmpty(sourBtn.get("compCols")))
//                sourCols = Integer.parseInt(sourBtn.get("compCols").toString());
//
//            int destOrder = Integer.parseInt(destBtn.get("compOrder").toString());
//            int destRow = Integer.parseInt(destBtn.get("compStartRow").toString());
//            int destCols = 0;
//            if (!TextUtils.isEmpty(destBtn.get("compCols")))
//                destCols = Integer.parseInt(destBtn.get("compCols").toString());
//
//            sourBtn.put("compOrder", destOrder);
//            sourBtn.put("compStartRow", destRow);
//            sourBtn.put("compCols", destCols);
//
//            destBtn.put("compOrder", sourOrder);
//            destBtn.put("compStartRow", sourRow);
//            destBtn.put("compCols", sourCols);
//
//            //更换两个button的属性
//            bt.setLabel(destBtn.get("kjName").toString());
//            bt.setExternObject(destBtn);
//            dragBtn.setLabel(sourBtn.get("kjName").toString());
//            dragBtn.setExternObject(sourBtn);
        }
    }

    void detailButton(BaseButton button) {
        FormComponentDetailAction winEdit = (FormComponentDetailAction) Executions.createComponents("/sys/page/formComponentDetail.do", null, button.getExternObject());
        if (winEdit instanceof BaseWindow) {
            BaseWindow _win = (BaseWindow) winEdit;
            _win.setClosable(true);
            _win.doModal();
            if (_win.isClosePage()) {
                Map<String, Object> lcomp = _win.getPpt();
                if (_win.isDelete()) {
                    //表示删除
                    removeButton(lcomp.get("compId").toString());
                } else {
                    updateButton(lcomp.get("compId").toString(), lcomp);
                }
            }
        }

    }


    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (event.getName().equals(Events.ON_DROP) && comp instanceof BaseButton) {
            //控件移动处理
            BaseButton baseButton = (BaseButton) comp;
            doMoveButton(baseButton, event);
        }
        if (event.getName().equals(Events.ON_DOUBLE_CLICK)) {
            //双击按钮
            BaseButton baseButton = (BaseButton) comp;
            detailButton(baseButton);
        }
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("button_addBtn")) {
                addButton2Grid();

            }
        }
    }


}
