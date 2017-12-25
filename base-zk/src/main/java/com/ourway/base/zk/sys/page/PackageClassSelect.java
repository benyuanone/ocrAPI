package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.TextUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import java.util.ArrayList;
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
public class PackageClassSelect extends BaseWindow {
    protected Log info = LogFactory.getLog(PackageClassSelect.class);
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
        return name;
    }
    public String getPreClassName() {
        BaseTextbox preClassName = (BaseTextbox) getFellowIfAny("preClassName");
        String name = preClassName.getText();
        return name;
    }

    private void saveAll() {
        BaseTextbox className = (BaseTextbox) getFellowIfAny("className");

        if(TextUtils.isEmpty(className.getText())){
            throw new WrongValueException(className,"请输入需要加入到业务类中的包名");
        }
        this.setClosePage(true);
        detach();
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
