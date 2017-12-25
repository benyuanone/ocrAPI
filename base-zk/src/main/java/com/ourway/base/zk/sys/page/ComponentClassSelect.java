package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.template.utils.ComponentsBandbox;
import com.ourway.base.zk.template.utils.PageSetUtils;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
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
public class ComponentClassSelect extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentClassSelect.class);
    List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

    public void onCreate(CreateEvent event) {
        super.onCreate(event);

        BaseButton addBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton clsBtn = (BaseButton) getFellowIfAny("clsBtn");
        addBtn.addEventListener(Events.ON_CLICK, this);
        clsBtn.addEventListener(Events.ON_CLICK, this);
    }

    public String getClassName() {
        BaseTextbox className = (BaseTextbox) getFellowIfAny("className");
        String name = className.getText();
        if (name.indexOf(".") >= 0) {
            String[] n1 = name.split("\\.");
            return n1[n1.length-1];
        }

        return "";

    }

    private void saveAll() {
        BaseTextbox className = (BaseTextbox) getFellowIfAny("className");
        BaseTextbox classPreName = (BaseTextbox) getFellowIfAny("classPreName");
        if (TextUtils.isEmpty(className.getText())) {
            throw new WrongValueException(className, "请输入完成的类路径");
        }
        if(!TextUtils.isEmpty(classPreName.getText()))
            ppt.put("classPreName",classPreName.getText());
        String url = "/sysObjectApi/listAttributeByClassName";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("className", className.getText());
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        data.setData(JsonUtil.toJson(params));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 &&!TextUtils.isEmpty(mess.getBean())) {
//                return (List<Map<String, Object>>) mess.getBean();
                datas = (List<Map<String, Object>>) mess.getBean();
                this.setClosePage(true);
                detach();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public Map<String, Object> getPpt() {
        ppt.put("datas", datas);

        return ppt;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (event.getTarget().getId().equals("saveBtn")) {
            saveAll();
        }
        if (event.getTarget().getId().equals("clsBtn")) {
            detach();
        }

    }


}
