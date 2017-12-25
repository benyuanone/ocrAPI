package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.oscache.OSCacheFactory;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;


public class OscacheClearAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        try {
            OSCacheFactory.getInstance().removeAll();
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.OPERATE_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
