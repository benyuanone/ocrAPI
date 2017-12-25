package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.template.utils.ComponentsBandbox;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
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
public class ComponentTreeSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentTreeSelAction.class);


    //所有的控件，当前页面中的
    Map<String, BaseButton> componentMap = new HashMap<String, BaseButton>();
    PageComponentAction pageComponentAction;
    List<String> selComponents = new ArrayList<String>();
    Div buttonResult;//显示控件用的区域
    Panelchildren button_group1;//默认第一个分组

    BaseCombobox loadEvent;
    BaseCombobox loadEvent2;
    BaseCombobox loadEvent3;
    BaseCombobox loadEvent4;
    BaseCombobox loadEvent5;
    BaseCombobox loadEvent6;
    BaseCombobox loadEvent7;
    BaseCombobox loadEvent8;
    BaseCombobox loadEvent9;
    BaseCombobox loadEvent10;
    BaseCombobox loadEvent11;
    BaseCombobox loadEvent12;
    BaseCombobox loadEvent13;
    BaseCombobox loadEvent14;
    BaseCombobox loadEvent15;
    BaseTextbox eventDataContent1;
    BaseTextbox eventDataContent2;
    BaseTextbox eventDataContent3;
    BaseTextbox eventDataContent4;
    BaseTextbox eventDataContent5;
    BaseTextbox eventDataContent6;
    BaseTextbox eventDataContent7;
    BaseTextbox eventDataContent8;
    BaseTextbox eventDataContent9;
    BaseTextbox eventDataContent10;
    BaseTextbox eventDataContent11;
    BaseTextbox eventDataContent12;
    BaseTextbox eventDataContent13;
    BaseTextbox eventDataContent14;
    BaseTextbox eventDataContent15;
    BaseTextbox kjConstraintKey4;
    BaseTextbox kjConstraintKey5;
    BaseTextbox kjConstraintKey7;
    BaseTextbox kjConstraintKey8;
    BaseTextbox kjConstraintKey9;
    BaseTextbox kjConstraintKey10;
    BaseTextbox kjConstraintKey11;
    BaseTextbox kjConstraintKey12;
    BaseTextbox kjConstraintKey13;
    BaseTextbox kjConstraintKey14;
    BaseTextbox kjConstraintKey15;

    @Override
    public Map<String, Object> getPpt() {
        bindAll2Ppt(false);
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(6);
        Map<String, Object> data = new HashMap<String, Object>(3);
        data.put("compId", "loadTree");
        data.put("evnetFormula", loadEvent.getText());
        data.put("eventDataContent", eventDataContent1.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(3);
        data.put("compId", "dynamicTree");
        data.put("evnetFormula", loadEvent2.getText());
        data.put("eventDataContent", eventDataContent2.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(3);
        data.put("compId", "onClickTree");
        data.put("evnetFormula", loadEvent3.getText());
        data.put("eventDataContent", eventDataContent3.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(3);
        data.put("compId", "addTree");
        data.put("evnetFormula", loadEvent4.getText());
        data.put("eventDataContent", eventDataContent4.getText());
        data.put("compOrder", -1);
        data.put("kjConstraintKey", kjConstraintKey4.getText());
        dataList.add(data);

        data = new HashMap<String, Object>(3);
        data.put("compId", "delTree");
        data.put("evnetFormula", loadEvent5.getText());
        data.put("eventDataContent", eventDataContent5.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(3);
        data.put("compId", "moveTree");
        data.put("evnetFormula", loadEvent6.getText());
        data.put("eventDataContent", eventDataContent6.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(4);
        data.put("compId", "addSameTree");
        data.put("evnetFormula", loadEvent7.getText());
        data.put("kjConstraintKey", kjConstraintKey7.getText());
        data.put("eventDataContent", eventDataContent7.getText());
        data.put("compOrder", -1);
        dataList.add(data);
        data = new HashMap<String, Object>(4);
        data.put("compId", "updateTree");
        data.put("evnetFormula", loadEvent8.getText());
        data.put("kjConstraintKey", kjConstraintKey8.getText());
        data.put("eventDataContent", eventDataContent8.getText());
        data.put("compOrder", -1);
        dataList.add(data);
        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick1");
        data.put("evnetFormula", loadEvent9.getText());
        data.put("kjConstraintKey", kjConstraintKey9.getText());
        data.put("eventDataContent", eventDataContent9.getText());
        data.put("compOrder", -1);
        dataList.add(data);
        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick2");
        data.put("evnetFormula", loadEvent10.getText());
        data.put("kjConstraintKey", kjConstraintKey10.getText());
        data.put("eventDataContent", eventDataContent10.getText());
        data.put("compOrder", -1);
        dataList.add(data);
        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick3");
        data.put("evnetFormula", loadEvent11.getText());
        data.put("kjConstraintKey", kjConstraintKey11.getText());
        data.put("eventDataContent", eventDataContent11.getText());
        data.put("compOrder", -1);
        dataList.add(data);
        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick4");
        data.put("evnetFormula", loadEvent12.getText());
        data.put("kjConstraintKey", kjConstraintKey12.getText());
        data.put("eventDataContent", eventDataContent12.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick5");
        data.put("evnetFormula", loadEvent13.getText());
        data.put("kjConstraintKey", kjConstraintKey13.getText());
        data.put("eventDataContent", eventDataContent13.getText());
        data.put("compOrder", -1);
        dataList.add(data);
        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick6");
        data.put("evnetFormula", loadEvent14.getText());
        data.put("kjConstraintKey", kjConstraintKey14.getText());
        data.put("eventDataContent", eventDataContent14.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        data = new HashMap<String, Object>(4);
        data.put("compId", "rightClick7");
        data.put("evnetFormula", loadEvent15.getText());
        data.put("kjConstraintKey", kjConstraintKey15.getText());
        data.put("eventDataContent", eventDataContent15.getText());
        data.put("compOrder", -1);
        dataList.add(data);

        ppt.put("dataList", dataList);
        return ppt;
    }

    /*获取其上级页面*/
    public PageComponentAction getParentAction(Component component) {
        if (component instanceof PageComponentAction)
            return (PageComponentAction) component;
        else
            return getParentAction(component.getParent());
    }


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        loadEvent = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula1");
        loadEvent2 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula2");
        loadEvent3 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula3");
        loadEvent4 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula4");
        loadEvent5 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula5");
        loadEvent6 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula6");
        loadEvent7 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula7");
        loadEvent8 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula8");
        loadEvent9 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula9");
        loadEvent10 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula10");
        loadEvent11 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula11");
        loadEvent12 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula12");
        loadEvent13 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula13");
        loadEvent14 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula14");
        loadEvent15 = (BaseCombobox) getFellowIfAny("loadEvent_evnetFormula15");
        eventDataContent1 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent1");
        eventDataContent2 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent2");
        eventDataContent3 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent3");
        eventDataContent4 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent4");
        eventDataContent5 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent5");
        eventDataContent6 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent6");
        eventDataContent7 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent7");
        eventDataContent8 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent8");
        eventDataContent9 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent9");
        eventDataContent10 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent10");
        eventDataContent11 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent11");
        eventDataContent12 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent12");
        eventDataContent13 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent13");
        eventDataContent14 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent14");
        eventDataContent15 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent15");
        kjConstraintKey4 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey4");
        kjConstraintKey5 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey5");
        kjConstraintKey7 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey7");
        kjConstraintKey8 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey8");
        kjConstraintKey9 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey9");
        kjConstraintKey10 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey10");
        kjConstraintKey11 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey11");
        kjConstraintKey12 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey12");
        kjConstraintKey13 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey13");
        kjConstraintKey14 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey14");
        kjConstraintKey15 = (BaseTextbox) getFellowIfAny("loadEvent_kjConstraintKey15");
        initGridDbclickEvent();
        pageComponentAction = getParentAction(this.getParent());
        if (null != event.getArg()) {
            ppt = (Map<String, Object>) event.getArg();
            initPage();
        }

    }


    private void initGridDbclickEvent() {
        List<Map<String, Object>> datas = DicUtil.listDic(14);
        loadEvent.getChildren().clear();
        loadEvent2.getChildren().clear();
        loadEvent3.getChildren().clear();
        loadEvent4.getChildren().clear();
        loadEvent5.getChildren().clear();
        loadEvent6.getChildren().clear();
        loadEvent7.getChildren().clear();
        loadEvent8.getChildren().clear();
        loadEvent9.getChildren().clear();
        loadEvent10.getChildren().clear();
        loadEvent11.getChildren().clear();
        loadEvent12.getChildren().clear();
        loadEvent13.getChildren().clear();
        loadEvent14.getChildren().clear();
        loadEvent15.getChildren().clear();
        if (null != datas)
            for (Map<String, Object> data : datas) {
                Comboitem ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent2);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent3);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent4);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent5);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent6);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent7);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent8);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent9);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent10);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent11);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent12);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent13);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent14);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent15);
            }
    }

    private void initPage() {
        if (null == ppt)
            return;
        bind2Page();
        if (null != ppt.get("dataList")) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) ppt.get("dataList");
            for (Map<String, Object> data : datas) {
                if (data.get("compId").equals("loadTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent1.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("dynamicTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent2.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent2.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("onClickTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent3.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent3.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("addTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent4.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey4.setText(data.get("kjConstraintKey").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent4.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("addSameTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent7.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey7.setText(data.get("kjConstraintKey").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent7.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("updateTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent8.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey8.setText(data.get("kjConstraintKey").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent8.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("delTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent5.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent5.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey5.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("moveTree")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent6.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent6.setText(data.get("eventDataContent").toString());
                }
                if (data.get("compId").equals("rightClick1")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent9.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent9.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey9.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("rightClick2")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent10.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent10.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey10.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("rightClick3")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent11.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent11.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey11.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("rightClick4")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent12.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent12.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey12.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("rightClick5")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent13.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent13.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey13.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("rightClick6")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent14.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent14.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey14.setText(data.get("kjConstraintKey").toString());
                }
                if (data.get("compId").equals("rightClick7")) {
                    if (!TextUtils.isEmpty(data.get("evnetFormula")))
                        loadEvent15.setText(data.get("evnetFormula").toString());
                    if (!TextUtils.isEmpty(data.get("eventDataContent")))
                        eventDataContent15.setText(data.get("eventDataContent").toString());
                    if (!TextUtils.isEmpty(data.get("kjConstraintKey")))
                        kjConstraintKey15.setText(data.get("kjConstraintKey").toString());
                }

            }
        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();

    }


}
