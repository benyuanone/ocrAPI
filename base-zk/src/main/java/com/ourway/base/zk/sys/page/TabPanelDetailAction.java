package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

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
public class TabPanelDetailAction extends BaseWindow {
    protected Log info = LogFactory.getLog(TabPanelDetailAction.class);
    BaseIntbox compStartRow;
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
        compStartRow = (BaseIntbox) getFellowIfAny("compStartRow");
        compColor = (BaseTextbox) getFellowIfAny("compColor");
        compCss = (BaseTextbox) getFellowIfAny("compCss");
        if (!TextUtils.isEmpty(ppt.get("compStartRow")))
            compStartRow.setText(ppt.get("compStartRow").toString());
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
        if (null == compStartRow.getValue() || compStartRow.getValue() == 0)
            throw new WrongValueException(compStartRow, "请填写行");
        ppt.put("compStartRow", compStartRow.getValue());
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
