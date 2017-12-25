package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * <p>方法: 新增弹出新界面的方法 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class FormUpdateAction implements ComponentListinerSer {
    //[{"pageType":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        int type = 3;
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (!TextUtils.isEmpty(windowParams)) {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (!TextUtils.isEmpty(_params.get("pageType")))
                type = Integer.parseInt(_params.get("pageType").toString());
        }
        try {
            ComponentUtils.setEditable(window);
            window.setWindowType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ComponentUtils.doCheckButtonStatus(window);

    }
}
