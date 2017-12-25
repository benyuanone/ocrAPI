package com.ourway.base.zk.main;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Messagebox;

/**
 * Created by jack on 2017/10/12.
 */
public class CommandAction {
    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireEventListeners(view, this);
    }

    @Listen("onTest=#commandBtn")
    public void onTest(Event evt){
        Messagebox.show("1234");
    }

}
