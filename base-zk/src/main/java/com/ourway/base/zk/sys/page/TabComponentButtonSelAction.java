package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseSpinner;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.template.utils.ComponentsBandbox;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ComponentUtils;
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
public class TabComponentButtonSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TabComponentButtonSelAction.class);


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
                    int fz1 = Integer.parseInt(o1.get("compHbox").toString());
                    int fz2 = Integer.parseInt(o2.get("compHbox").toString());
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
                if (Integer.parseInt(map.get("compHbox").toString()) != oldfz) {
                    index = 1;
                    oldfz = Integer.parseInt(map.get("compHbox").toString());
                }
                map.put("compOrder", index);
                index++;
            }
            //把所有panel的data的配置也加进去。
            List<Panel> panels = buttonResult.getChildren();
            if (null != panels && panels.size() > 0) {
                for (Panel panel : panels) {
                    Map<String, Object> map = (Map<String, Object>) panel.getAttribute("comp");
                    map.put("compOrder", -1);
                    dataList.add(map);
                }
            }
            ppt.put("dataList", dataList);
        }
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

    private List<Map<String, Object>> initTab(List<Map<String, Object>> datas) {
        List<Map<String, Object>> realDatas = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : datas) {
            if (!TextUtils.isEmpty(map.get("compOrder")) && map.get("compOrder").toString().equalsIgnoreCase("-1")) {
                rows.add(map);
            } else
                realDatas.add(map);
        }
        if (rows.size() > 0) {
            Collections.sort(rows, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return (Integer) o1.get("compHbox") - (Integer) o2.get("compHbox");
                }
            });
            //创建tab
            for (Map<String, Object> row : rows) {
                doCreateChildren((Integer) row.get("compHbox"), row);
            }

        }
        return realDatas;
    }

    private void initPage() {
        if (null == ppt) {
            return;
        }
        Panelchildren pc = null;
        bind2Page();
        if (null != ppt.get("dataList")) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) ppt.get("dataList");
            datas = initTab(datas);
            Collections.sort(datas, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return (Integer) o1.get("compOrder") - (Integer) o2.get("compOrder");
                }
            });
            for (Map<String, Object> data : datas) {
                BaseButton button = PageSetUtils.doCreateButton(data.get("compId").toString(), data.get("pageRefOwidLabel").toString(), "");
                if (TextUtils.isEmpty(data.get("compHbox")))
                    pc = doCreateChildren(1,null);
                else
                    pc = doCreateChildren((Integer) data.get("compHbox"),null);
                button.setParent(pc);
                button.setDraggable("true");
                button.setDroppable("true");
                button.setMold("bs");
                button.setSclass("btn-success");

                button.addEventListener(Events.ON_DROP, this);
                button.addEventListener(Events.ON_DOUBLE_CLICK, this);
                data.put("kjName", data.get("pageRefOwidLabel").toString());
                button.setExternObject(data);
                componentMap.put(data.get("compId").toString(), button);
                selComponents.add(data.get("compId").toString());
            }
        }
    }

    Panelchildren doCreateChildren(int rowNum, Map<String, Object> panelMap) {
        Panelchildren btnGroup = (Panelchildren) getFellowIfAny("button_group" + rowNum);
        if (null == btnGroup) {
            Panel panel = new Panel();
            panel.setSclass("panel-primary");
            panel.setTitle("第" + rowNum + "组");
            panel.setParent(buttonResult);
            panel.addEventListener(Events.ON_DOUBLE_CLICK, this);
            if (null == panelMap) {
                panelMap = new HashMap<String, Object>();
                panelMap.put("compCss", "");
                panelMap.put("compHbox", rowNum);
                panelMap.put("compColor","");
            }
            panel.setAttribute("comp", panelMap);
            Panelchildren pc = new Panelchildren();
            pc.setId("button_group" + rowNum);
            pc.setParent(panel);
            return pc;
        } else {
            return btnGroup;
        }
    }

    private void saveAll() {
//
    }

    //    添加新的控件
    void addButton2Grid() {
        ComponentsBandbox bandbox = (ComponentsBandbox) getFellowIfAny("button_controlList");
        BaseSpinner rowNum = (BaseSpinner) getFellowIfAny("button_rowNum");
        if (null == rowNum.getValue() || rowNum.getValue() <= 0) {
            throw new WrongValueException(rowNum, "请输入大于0的行数");
        }
        Panelchildren pn = doCreateChildren(rowNum.getValue(),null);
        List<Map<String, Object>> datas = bandbox.getSelItems();
        if (null != datas && datas.size() > 0) {
            for (Map<String, Object> data : datas) {
                BaseButton button = PageSetUtils.doCreateButton(data.get("kjAttribute").toString(), data.get("kjName").toString(), data.get("kjTypeLabel").toString());
                button.setParent(pn);
                button.setDraggable("true");
                button.setDroppable("true");
                button.setMold("bs");
                button.setSclass("btn-success");
                button.addEventListener(Events.ON_DROP, this);
                button.addEventListener(Events.ON_DOUBLE_CLICK, this);
                Map<String, Object> _data = new HashMap<String, Object>();
                _data.put("compStartRow", 1);
                _data.put("compOrder", ComponentUtils.getMaxOrder(pn));
                _data.put("compCols", 0);
                _data.put("compHbox", rowNum.getValue());
                _data.put("layoutRefOwid", data.get("layoutRefOwid"));
                _data.put("pageRefOwid", data.get("pageRefOwid"));
                _data.put("compId", data.get("kjAttribute"));
                _data.put("kjAttribute", data.get("kjAttribute"));
                _data.put("kjName", data.get("kjName"));
                _data.put("kjTypeLabel", data.get("kjTypeLabel"));
                button.setExternObject(_data);
                componentMap.put(data.get("kjAttribute").toString(), button);
                selComponents.add(data.get("kjAttribute").toString());
            }
        } else {
            AlterDialog.alert("请选择需要加入的按钮控件");
        }
    }

    //删除控件
    void removeButton(String kjAttribute) {
        BaseButton bt = findButton(kjAttribute);
        if (null != bt) {
            bt.getParent().removeChild(bt);
            componentMap.remove(kjAttribute);
            selComponents.remove(kjAttribute);
        }
    }

    void updateButton(String kjAttribute, Map<String, Object> data) {
        BaseButton bt = findButton(kjAttribute);


        Panelchildren btnGroup = (Panelchildren) getFellowIfAny("button_group" + data.get("compHbox").toString());
        if (null == btnGroup) {
            Panel panel = new Panel();
            panel.setSclass("panel-primary");
            panel.setTitle("第" + data.get("compHbox").toString() + "组");
            panel.setParent(buttonResult);
            Panelchildren pc = new Panelchildren();
            pc.setId("button_group" + data.get("compHbox").toString());
            pc.setParent(panel);
            data.put("compOrder", 1);
            bt.setExternObject(data);
            bt.setParent(pc);
        } else {
            bt.setExternObject(data);
            bt.setParent(btnGroup);
        }


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
//            if (bt.getCols() != dragBtn.getCols()) {
//                AlterDialog.alert("两个控件所占的列数不同，不能替换");
//                return;
//            }
            Map<String, Object> sourBtn = dragBtn.getExternObject();
            Map<String, Object> destBtn = bt.getExternObject();
            double destOrder = Double.parseDouble(destBtn.get("compOrder").toString());
            int destRow = Integer.parseInt(destBtn.get("compStartRow").toString());
            int hbox = Integer.parseInt(destBtn.get("compHbox").toString());
            BaseButton prebtn = findPreButton(bt);
            if (null == prebtn) {
                sourBtn.put("compOrder", destOrder - 0.1);
                sourBtn.put("compStartRow", destRow);
                sourBtn.put("compHbox", hbox);
            } else {
                double sorder = Double.parseDouble(prebtn.getExternObject().get("compOrder").toString());
                double sorder1 = (sorder + destOrder) / 2;
                sourBtn.put("compOrder", sorder1);
                sourBtn.put("compStartRow", destRow);
                sourBtn.put("compHbox", hbox);
            }
            dragBtn.setExternObject(sourBtn);
            bt.getParent().insertBefore(dragBtn, bt);

        }
    }

    void detailButton(BaseButton button) {
        ButtonComponentDetailAction winEdit = (ButtonComponentDetailAction) Executions.createComponents("/sys/page/componentDetail.do", null, button.getExternObject());
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
            System.out.println("====");
        }
        if (comp instanceof BaseButton&&event.getName().equals(Events.ON_DOUBLE_CLICK)) {
            //双击按钮
            BaseButton baseButton = (BaseButton) comp;
            detailButton(baseButton);
        }
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("button_addBtn")) {
                addButton2Grid();

            }
        }
        if (comp instanceof Panel) {
            Panel panel = (Panel) comp;
            try {
                TabButtonDetailAction winEdit = (TabButtonDetailAction) Executions.createComponents("/sys/page/tabButtonDetail.do", null, (Map<String, Object>) panel.getAttribute("comp"));
                winEdit.setClosable(true);
                winEdit.doModal();
                if (winEdit.isClosePage()) {
                    Map<String, Object> map = winEdit.getPpt();
                    if (!TextUtils.isEmpty(map.get("operateFlag")) && map.get("operateFlag").toString().equalsIgnoreCase("2")) {
                        //删除这个panel
                        List<BaseButton> buttons = panel.getPanelchildren().getChildren();
                        for (BaseButton button : buttons) {
                            String attr = button.getExternObject().get("compId").toString();
                            componentMap.remove(attr);
                            selComponents.remove(attr);
                        }
                        panel.getParent().removeChild(panel);
                    } else
                        panel.setAttribute("comp", winEdit.getPpt());
                }
            } catch (Exception e) {

            }
        }
    }


}
