package com.ourway.erpcustoms.erpshipblacklist;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZkUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-15.
 */
public class RevokeBlacklist implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        try {
            Component _comp = Path.getComponent("/mainWin");
            MainAction root = null;
            if (_comp instanceof MainAction) {
                root = (MainAction) _comp;
            }
            Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            //TODO
            //弹窗换为输入撤销原因
            Component winEdit = Executions.createComponents(_params.get("url").toString(), null, null);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss"))) {
                    _win.setStyle("width:600px");
                }
                String tabId = root.openNewTab(_win, _params.get("title").toString());
//                List<String> tabList = ZkUtils.doHandleTabid(window,tabId);
//                _win.setTabId(tabList);
                _win.setTabId(tabId+"_window");
                _win.setTopWindow(window);
                _win.setParentPpt(_params);
                _win.setClosable(true);
                _win.doModal();
                if (_win.isClosePage()) {
                    Map<String,Object> dataMap = window.getPpt();
                    dataMap.put("revokeReason",_win.bindAll2Ppt(true).get("revokeReason").toString());
                    dataMap.put("state",1);
                    ResponseMessage responseMessage = JsonPostUtils.executeAPI(dataMap,"/shipBlacklistApi/saveErpShipBlacklist");
                    if (null != responseMessage && responseMessage.getBackCode() >= 0) {
                        root.closeTabWin(_win);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
