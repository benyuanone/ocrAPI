package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Panelchildren;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ComponentTreeBandboxSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentTreeBandboxSelAction.class);


    //所有的控件，当前页面中的
    Map<String, BaseButton> componentMap = new HashMap<String, BaseButton>();
    PageComponentAction pageComponentAction;
    List<String> selComponents = new ArrayList<String>();
    Div buttonResult;//显示控件用的区域
    Panelchildren button_group1;//默认第一个分组

    BaseCombobox loadEvent;
    BaseCombobox loadEvent2;
    BaseTextbox eventDataContent1;
    BaseTextbox eventDataContent2;


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
        eventDataContent1 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent1");
        eventDataContent2 = (BaseTextbox) getFellowIfAny("loadEvent_eventDataContent2");
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

        if (null != datas)
            for (Map<String, Object> data : datas) {
                Comboitem ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent);
                ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(loadEvent2);
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
            }
        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();

    }


}
