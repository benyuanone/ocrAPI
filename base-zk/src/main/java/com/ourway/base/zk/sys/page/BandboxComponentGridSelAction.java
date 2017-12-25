package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.template.utils.ComponentsBandbox;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
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
public class BandboxComponentGridSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(BandboxComponentGridSelAction.class);


    //所有的控件，当前页面中的
    PageComponentAction pageComponentAction;

    @Override
    public Map<String, Object> getPpt() {
        bindAll2Ppt(false);

        return ppt;
    }
    /*获取其上级页面*/
    public PageComponentAction getParentAction(Component component) {
        if (component instanceof PageComponentAction)
            return (PageComponentAction) component;
        else
            return getParentAction(component.getParent());
    }


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        pageComponentAction = getParentAction(this.getParent());
        if (null != event.getArg()) {
            ppt = (Map<String, Object>) event.getArg();
            initPage();
        }

    }




    private void initPage(){
        if(null==ppt)
            return ;
         bind2Page();

    }







    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();

    }


}
