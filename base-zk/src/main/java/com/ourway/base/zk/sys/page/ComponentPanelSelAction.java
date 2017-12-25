package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.template.utils.ComponentsBandbox;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.AlterDialog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;

import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ComponentPanelSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentPanelSelAction.class);

    public void onCreate(CreateEvent event) {
        super.onCreate(event);

        if(null!=event.getArg()) {
            ppt = (Map<String, Object>) event.getArg();
            bind2Page();
        }
    }

    //返回所有
    public Map<String,Object> getPanel(){
        bindAll2Ppt(false);
        return ppt;
    }

    @Override
    public Map<String, Object> getPpt() {
        bindAll2Ppt(false);
        return ppt;
    }
    @Override
    public void onEvent(Event event) throws Exception {

    }


}
