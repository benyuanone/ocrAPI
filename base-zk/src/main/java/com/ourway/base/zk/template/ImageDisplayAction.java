package com.ourway.base.zk.template;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.GridFilterVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listitem;

import java.util.ArrayList;
import java.util.HashMap;
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
public class ImageDisplayAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ImageDisplayAction.class);
    private String imagePath;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        Map<String, Object> params = (Map<String, Object>) event.getArg();
        if (null == params)
            detach();
        imagePath = params.get("imagePath").toString();
        Image image = (Image)getFellowIfAny("image");
        image.setSrc(imagePath);
    }



    @Override
    public void onEvent(Event event) throws Exception {

    }

}
