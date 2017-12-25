package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/*<p>方法 RemoveDetailAction <p>
*<p>说明:详情页面内删除</p>
*<pre>
*@author zhou_xtian
*@date 2017-06-02 10:01
</pre>
*/
public class RemoveInDetailPageAction implements ComponentListinerSer {
    //[{"gridId":"","url":"","tree":"treeid","freshTree":"","reset":"0 closepage 1reset"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("url"))) {
            AlterDialog.alert("请输入需要删除的API");
            return;
        }
        if (TextUtils.isEmpty(_params.get("reset"))) {
            AlterDialog.alert("请输入页面操作方式");
            return;
        }
        if (!AlterDialog.corfirm("确定需要删除"))
            return;
        Map<String, Object> ppt = window.getPpt();
        ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
        if (null == message || message.getBackCode() != 0) {
            AlterDialog.alert("操作失败");
            return;
        } else {
            if (!TextUtils.isEmpty("freshTree")) {
                refreshTree(window);
            }
            int reset = Integer.parseInt(_params.get("reset").toString());
            switch (reset) {
                case 0:
                    claseTab(window);
                    break;
                case 1:
                    PageUtils.resetGridComponent(window);
                    break;
            }
        }
    }

    private void refreshTree(BaseWindow window) {
        window.loadTreeData();
    }

    private void claseTab(BaseWindow window) {
        Component _comp = Path.getComponent("/mainWin");
        MainAction root = null;
        if (_comp instanceof MainAction) {
            root = (MainAction) _comp;
        }
        try {
            if (null != window.getBaseGrid().getDbRow()) {
                window.getGridUtils().refreshRow(window.getPpt(), window.getBaseGrid(), window.getBaseGrid().getDbRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        root.closeTabWin(window);
    }
}
