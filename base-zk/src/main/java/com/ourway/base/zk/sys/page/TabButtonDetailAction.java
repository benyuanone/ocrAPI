package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseIntbox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.AlterDialog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class TabButtonDetailAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TabButtonDetailAction.class);
    BaseIntbox compHbox;
    BaseTextbox compColor;
    BaseTextbox compCss;


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        Map map = event.getArg();
        if (null == map) {
            AlterDialog.alert("无按钮属性");
            detach();
        }
        ppt = (Map<String, Object>) map;
        compHbox = (BaseIntbox) getFellowIfAny("compHbox");
        compColor = (BaseTextbox) getFellowIfAny("compColor");
        compCss = (BaseTextbox) getFellowIfAny("compCss");
        if (!TextUtils.isEmpty(ppt.get("compHbox")))
            compHbox.setText(ppt.get("compHbox").toString());
        if (!TextUtils.isEmpty(ppt.get("compColor")))
            compColor.setText(ppt.get("compColor").toString());
        if (!TextUtils.isEmpty(ppt.get("compCss")))
            compCss.setText(ppt.get("compCss").toString());
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton clsBtn = (BaseButton) getFellowIfAny("clsBtn");
        BaseButton removeBtn = (BaseButton) getFellowIfAny("removeBtn");
        saveBtn.addEventListener(Events.ON_CLICK, this);
        clsBtn.addEventListener(Events.ON_CLICK, this);
        removeBtn.addEventListener(Events.ON_CLICK, this);
    }

    public void save() {
        if (null == compHbox.getValue() || compHbox.getValue() == 0)
            throw new WrongValueException(compHbox, "请填写组");
        ppt.put("compHbox", compHbox.getValue());
        ppt.put("compColor", compColor.getText());
        ppt.put("compCss", compCss.getText());
        setDelete(false);
        setClosePage(true);
        detach();
    }

    @Override
    public void onEvent(Event event) throws Exception {
        if (event.getTarget().getId().equals("saveBtn")) {
            //保存
            save();
        }
        if (event.getTarget().getId().equals("removeBtn")) {
            //删除
            if (AlterDialog.corfirm("确定需要删除该行")) {
                ppt.put("operateFlag", 2);
                setDelete(true);
                setClosePage(true);
                detach();
            }
        }
        if (event.getTarget().getId().equals("clsBtn")) {
            detach();
        }
    }

}
