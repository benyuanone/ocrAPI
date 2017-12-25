package com.ourway.base.zk.sys.page;

import com.ourway.base.zk.component.*;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class HeadComponentSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(HeadComponentSelAction.class);

    private Map<String, Object> layOutComponent;
    private Map<String, Map<String, Object>> pageComponents;

    private Map<String,Object> existMap = new HashMap<String,Object>();


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        if (null != event.getArg().get("ppt")) {
            ppt = (Map<String, Object>) event.getArg().get("ppt");
            bind2Page();
        }
        if (null != event.getArg().get("ctrls")) {
            pageComponents = (Map<String, Map<String, Object>>) event.getArg().get("ctrls");
        }
        if (null != event.getArg().get("layoutComponent")) {
            layOutComponent = (Map<String, Object>) event.getArg().get("layoutComponent");
            if(null!=layOutComponent.get("dataList")){
                List<Map<String,Object>> datas = (List<Map<String,Object>>)layOutComponent.get("dataList");
                for(Map<String,Object> data:datas){
                   existMap.put(data.get("pageRefOwid").toString(),data);
                }
            }
        }
        initPageInit();
        init();
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton removeBtn = (BaseButton) getFellowIfAny("removeBtn");
        BaseButton closeBtn = (BaseButton) getFellowIfAny("closeBtn");
        saveBtn.addEventListener(Events.ON_CLICK, this);
        closeBtn.addEventListener(Events.ON_CLICK, this);
        removeBtn.addEventListener(Events.ON_CLICK, this);
    }

    private void initPageInit(){
        List<Map<String,Object>> datas = DicUtil.listDic(0);
        BaseCombobox pageInit = (BaseCombobox)getFellowIfAny("componentSelGrid_evnetFormula");
        pageInit.getChildren().clear();
        if(null!=datas)
            for(Map<String,Object> data:datas){
                Comboitem ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(TextUtils.isEmpty(data.get("dicVal2"))?"":data.get("dicVal2").toString());
                ci.setParent(pageInit);
            }
    }


    //初始化
    private void init() {
        if (null == pageComponents)
            return;
        Set<String> keys = pageComponents.keySet();
        BaseListbox layoutRefOwid = (BaseListbox) getFellowIfAny("componentSelGrid_pageRefOwid");
        layoutRefOwid.getChildren().clear();
        Listitem item = new Listitem("---请选择---", "");
        if (null == ppt.get("pageRefOwid"))
            item.setSelected(true);
        item.setParent(layoutRefOwid);
        for (String key : keys) {
            item = new Listitem(pageComponents.get(key).get("kjName").toString() + ":" + key, key);
            if (null != ppt.get("pageRefOwid") && ppt.get("pageRefOwid").toString().equals(item.getValue().toString()))
                item.setSelected(true);
            else {
                if (null != existMap && null != existMap.get(item.getValue().toString()))
                    continue;
            }
            item.setParent(layoutRefOwid);
        }

    }

    private void saveAll() {
        ppt = bindAll2Ppt(true);
        isClosePage = true;
        detach();
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("saveBtn")) {
                saveAll();

            }
            if(comp.getId().equals("removeBtn")){
                //用于删除
                ppt = null;
                isClosePage = true;
                detach();
            }
            if (comp.getId().equals("closeBtn")) {
                //关闭
                detach();
            }
        }
    }


}
