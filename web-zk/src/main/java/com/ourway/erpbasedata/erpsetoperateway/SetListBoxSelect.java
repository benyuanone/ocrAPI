package com.ourway.erpbasedata.erpsetoperateway;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.bandbox.TreeBandbox;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.syszk.utils.DicCascadeUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Row;

import java.util.*;

/*<p>方法 SetListBoxSelect : <p>
*<p>说明:作业方式管理动态选项</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-26 13:39
</pre>
*/
public class SetListBoxSelect implements ComponentListinerSer{

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);

        Map<String, Object> selVal = null;
        TreeBandbox treeBandbox = null;
        try {
            if (null != event && event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                treeBandbox = (TreeBandbox) keyEvent.getTarget();
                List<Map<String,Object>> objects = treeBandbox.getSelVals();
                if (null != objects && objects.size() > 0) {
                    selVal = (Map<String, Object>) objects.get(0);
                }
            }
            if (null == selVal)
                return;

            Row row = getCurrentRow(treeBandbox);
            List<Component> list = row.getChildren();
            for (Component component : list) {
                setValueToRow(component, selVal.get("path").toString(), _params);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Row getCurrentRow(Component component) {
        if (component.getParent() instanceof Row)
            return (Row) component.getParent();
        else
            return getCurrentRow(component.getParent());
    }

    //设置值到row
    private void setValueToRow(Component component, String selVal, Map<String, Object> windowParams) {
        if (!TextUtils.isEmpty(selVal)) {
            if (component instanceof BaseListbox) {
                BaseListbox listbox = (BaseListbox) component;
                for (String key:windowParams.keySet()) {
                    int type = Integer.parseInt(key.split("\\|")[0]);
                    List<String> propertyList = Arrays.asList(key.split("\\|")[1].split(","));
                    for (String property:propertyList) {
                        if (property.equals(listbox.getProperty())) {
                            DicCascadeUtil.autoSwitchNewListbox(windowParams.get(key).toString(),selVal,component,type);
                        }
                    }
                }
            }
        }
    }
}
