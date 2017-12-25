package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

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
public class FormComponentDetailAction extends BaseWindow {
    protected Log info = LogFactory.getLog(FormComponentDetailAction.class);

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        Map map = event.getArg();
        if (null == map) {
            AlterDialog.alert("无按钮属性");
            detach();
        }
        ppt = (Map<String, Object>) map;
        initEventList();
        bind2Page();
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton delBtn = (BaseButton) getFellowIfAny("delBtn");
        saveBtn.addEventListener(Events.ON_CLICK, this);
        delBtn.addEventListener(Events.ON_CLICK, this);
        BaseCombobox pageInit = (BaseCombobox) getFellowIfAny("componentSelGrid_evnetFormula");
        pageInit.addEventListener(Events.ON_SELECT, this);
    }

    /*加载按钮可以执行的事件*/
    private void initEventList() {
        List<Map<String, Object>> datas = DicUtil.listDic(0);
        BaseCombobox pageInit = (BaseCombobox) getFellowIfAny("componentSelGrid_evnetFormula");
        pageInit.getChildren().clear();
        if (null != datas && datas.size() > 0)
            for (Map<String, Object> data : datas) {
                BaseComboitem ci = new BaseComboitem(data.get("dicVal3").toString());
                ci.setExternProperty(data);
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString());
                ci.setParent(pageInit);
            }
    }

    public void save() {
        bindAll2Ppt(true);
        setDelete(false);
        setClosePage(true);
        detach();
    }

    @Override
    public void onEvent(Event event) throws Exception {
        if (event.getTarget() instanceof BaseCombobox) {
            BaseCombobox combobox = (BaseCombobox) event.getTarget();
            BaseComboitem comboitem = (BaseComboitem) combobox.getSelectedItem();
            if (null != comboitem) {

                if (!TextUtils.isEmpty(comboitem.getExternProperty().get("dicVal5"))) {
                    BaseTextbox tb = (BaseTextbox) getFellowIfAny("componentSelGrid_eventDataContent");
                    tb.setText("[{" + comboitem.getExternProperty().get("dicVal5").toString() + "}]");
                }
            }
        }
        if (event.getTarget().getId().equals("saveBtn")) {
            //保存
            save();
        }
        if (event.getTarget().getId().equals("delBtn")) {
            //删除
            if (AlterDialog.corfirm("确定需要删除该控件")) {
                ppt.put("operateFlag", 2);
                setDelete(true);
                setClosePage(true);
                detach();
            }
        }
    }

}
