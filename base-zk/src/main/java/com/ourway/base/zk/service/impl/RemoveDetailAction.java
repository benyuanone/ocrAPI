package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
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
public class RemoveDetailAction implements ComponentListinerSer {
//[{"gridId":"","url":"","tree":"treeid","freshTree":"","reset":"0 closepage 1reset"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Component _comp = Path.getComponent("/mainWin");
        MainAction root = null;
        if (_comp instanceof MainAction) {
            root = (MainAction) _comp;
        }
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            //获取所有主表中当个控件的值
            Map<String, Object> ppt = window.bindAll2Ppt(false);
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (!AlterDialog.corfirm("确定需要删除选中的数据"))
                return;
            if (TextUtils.isEmpty(_params.get("url"))) {
                AlterDialog.alert("请定义需要删除的API接口");
                return;
            }
            if (!TextUtils.isEmpty(_params.get("gridId"))) {
                String[] _ids = _params.get("gridId").toString().split("\\,");
                for (String s : _ids) {
                    BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(s);
                    if (null != baseGrid)
                        ppt.put(s, GridUtils.instance().getAllDatas(baseGrid,true));
                }
            }
            //调用接口进行保存
            ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
            if (null == message) {
                AlterDialog.alert("操作失败");
                return;
            }
            if (message.getBackCode() == 0) {
                if (message.getBean() instanceof Map)
                    window.setPpt((Map<String, Object>) message.getBean());
                if (null != window.getBaseGrid().getDbRow()) {
                    window.getGridUtils().removeRow(window.getBaseGrid(),window.getSelRowId());
//                    window.getGridUtils().refreshRow(window.getPpt(), window.getBaseGrid(), window.getBaseGrid().getDbRow());
                    root.closeTabWin(window);
                }
            } else {
                AlterDialog.alert(message.getErrorMess());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
