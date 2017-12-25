package com.ourway.base.zk.sys.develop;

import com.ourway.base.zk.component.BaseWindow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;


public class DevelopAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DevelopAction.class);


    public void onCreate(CreateEvent event) {
        super.onCreate(event);

    }


    @Override
    public void onEvent(Event event) throws Exception {

    }

}
