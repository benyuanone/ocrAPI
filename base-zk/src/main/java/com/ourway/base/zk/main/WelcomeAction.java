package com.ourway.base.zk.main;

import com.ourway.base.zk.component.BaseWindow;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;


public class WelcomeAction extends BaseWindow {

    private static final long serialVersionUID = 3654985667382327935L;
    private Tabs resourceTabs;
    private Tabpanels resourceTabpanels;
    private Tabbox resources;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        resourceTabs = (Tabs) this.getFellowIfAny("resourceTabs");
        resourceTabpanels = (Tabpanels) this.getFellowIfAny("resourceTabpanels");
        resources = (Tabbox) this.getFellowIfAny("resources");

//        initScript();
    }


}
