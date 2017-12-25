package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.BillIdUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-12.
 */
public class GetCurrentDateAction implements ComponentInitSer {

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        DataBinder.obj2Component(dateStr,component,null);
    }
}
