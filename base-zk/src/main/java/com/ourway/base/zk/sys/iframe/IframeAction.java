package com.ourway.base.zk.sys.iframe;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseComboitem;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Iframe;

import java.util.List;
import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class IframeAction extends BaseWindow {
    protected Log info = LogFactory.getLog(IframeAction.class);
    Iframe iframePage;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        Map map = event.getArg();
        if (null == map) {
            AlterDialog.alert("无参数");
            detach();
        }

        iframePage = (Iframe) getFellowIfAny("iframePage");
        iframePage.setStyle("width:100%;border:0px;height:98%;");
//        iframePage.setHeight((ZKSessionUtils.getScreenHeight() - 125) + "px");
        if (!TextUtils.isEmpty(map.get("url")))
            iframePage.setSrc(map.get("url").toString());
    }


    @Override
    public void onEvent(Event event) throws Exception {
    }

}
