package com.ourway.erpcustoms.erpshipblacklist;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 2017-09-21.
 */
public class SaveRevokeReason implements ComponentListinerSer{
    Map<String, Map<String, Object>> pageComponents = new HashMap<String, Map<String, Object>>();

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        try {
            window.setClosePage(true);
            Component comp = Path.getComponent("/mainWin");
            MainAction root = null;
            if (comp instanceof MainAction) {
                root = (MainAction) comp;
            }
            root.closeTab(window.getParent().getId().replaceAll("_panel", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
