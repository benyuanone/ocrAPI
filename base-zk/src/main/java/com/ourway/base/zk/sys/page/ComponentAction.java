package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listitem;

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
public class ComponentAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentAction.class);


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        initComponentInitList();
        initKjFormat();
        if (null != event.getArg().get("ppt")) {
            ppt = (Map<String, Object>) event.getArg().get("ppt");
            bind2Page();
        }
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton closeBtn = (BaseButton) getFellowIfAny("closeBtn");
        BaseButton copyBtn = (BaseButton) getFellowIfAny("copyBtn");
        BaseButton pasteBtn = (BaseButton) getFellowIfAny("pasteBtn");
        saveBtn.addEventListener(Events.ON_CLICK, this);
        closeBtn.addEventListener(Events.ON_CLICK, this);
        copyBtn.addEventListener(Events.ON_CLICK, this);
        pasteBtn.addEventListener(Events.ON_CLICK, this);

        BaseListbox kjType = (BaseListbox) getFellowIfAny("atributeGrid_kjType");
        initKjType( kjType);
        BaseListbox kjDatasource = (BaseListbox) getFellowIfAny("atributeGrid_kjDatasource");
        kjType.addEventListener(Events.ON_SELECT, this);
        kjDatasource.addEventListener(Events.ON_SELECT, this);

        initAction();

    }



    private void initKjType( BaseListbox kjType){
        Listitem item = new Listitem("---请选择---","");
        item.setParent(kjType);
        if(null==ppt||TextUtils.isEmpty(ppt.get("kjType")))
           item.setSelected(true);
        List<Map<String,Object>> types = DicUtil.listDic(1,"b.dicVal1");
        for(Map<String,Object> map :types){
            item = new Listitem(TextUtils.isEmpty(map.get("dicVal2"))?"":map.get("dicVal2").toString(),TextUtils.isEmpty(map.get("dicVal1"))?"":map.get("dicVal1").toString());
            item.setParent(kjType);
            if(null!=ppt&&!TextUtils.isEmpty(ppt.get("kjType"))&&ppt.get("kjType").toString().equals(map.get("dicVal1").toString()))
                item.setSelected(true);
        }
    }
    private void initKjFormat(){
        BaseCombobox combobox = (BaseCombobox)getFellowIfAny("atributeGrid_kjFormat");
        combobox.getChildren().clear();
        List<Map<String,Object>> datas = DicUtil.listDic(16,"b.dicVal1");
        if(null!=datas)
            for(Map<String,Object> data:datas){
                Comboitem ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2"))?"":data.get("dicVal2").toString());
                ci.setParent(combobox);
            }

    }

    private void initComponentInitList(){

        List<Map<String,Object>> datas = DicUtil.listDic(6);
        BaseCombobox kjClassImpl = (BaseCombobox)getFellowIfAny("atributeGrid_kjClassImpl");
        kjClassImpl.getChildren().clear();
        if(null!=datas)
            for(Map<String,Object> data:datas){
                Comboitem ci = new Comboitem(data.get("dicVal2").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal3"))?"":data.get("dicVal3").toString());
                ci.setParent(kjClassImpl);
            }

    }

    private void initAction() {
        if (null != ppt && null != ppt.get("kjType")) {
            if (ppt.get("kjType").toString().equals("4") || ppt.get("kjType").toString().equals("10")) {
                getFellowIfAny("kjTranslateRow").setVisible(true);
            }
            if (ppt.get("kjType").toString().equals("3")) {
                getFellowIfAny("kjClassRow").setVisible(true);


            }
        }
        if (null != ppt && null != ppt.get("kjDatasource")) {
            if (ppt.get("kjDatasource").toString().equals("1")) {
                getFellowIfAny("kjDefaultDataRow").setVisible(true);
            }
            if (ppt.get("kjDatasource").toString().equals("2")) {
                getFellowIfAny("kjInterfaceRow").setVisible(true);
                getFellowIfAny("kjInitDataRow").setVisible(true);
            }
            if (ppt.get("kjDatasource").toString().equals("3")) {
                getFellowIfAny("kjClassImplRow").setVisible(true);
                getFellowIfAny("kjInitDataRow").setVisible(true);
            }
        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("saveBtn")) {
                //保存并返回给上一页
                isClosePage = true;
                ppt = bind2Object();
                if(TextUtils.isEmpty(ppt.get("kjDataType"))){
                    throw new WrongValueException(getFellowIfAny("atributeGrid_kjDataType"),"必选");
                }
                detach();
            }
            if (comp.getId().equals("closeBtn")) {
                //关闭
                detach();
            }
        }
        if (comp.getId().equals("atributeGrid_kjType")) {
            BaseListbox baseListbox = (BaseListbox) comp;
            if (null != baseListbox.getSelectedItem()) {
                if (baseListbox.getSelectedItem().getValue().toString().equals("4") || baseListbox.getSelectedItem().getValue().toString().equals("10")) {
                    //显示格式字段
                    getFellowIfAny("kjTranslateRow").setVisible(true);
                } else
                    getFellowIfAny("kjTranslateRow").setVisible(false);
                if (baseListbox.getSelectedItem().getValue().toString().equals("3")) {
                    //显示格式字段
                    getFellowIfAny("kjClassRow").setVisible(true);
                } else
                    getFellowIfAny("kjClassRow").setVisible(false);

            }
        }
        if (comp.getId().equals("atributeGrid_kjDatasource")) {
            BaseListbox baseListbox = (BaseListbox) comp;
            if (null != baseListbox.getSelectedItem()) {
                if (baseListbox.getSelectedItem().getValue().toString().equals("1")) {
                    //显示格式字段
                    getFellowIfAny("kjDefaultDataRow").setVisible(true);
                } else
                    getFellowIfAny("kjDefaultDataRow").setVisible(false);
                if (baseListbox.getSelectedItem().getValue().toString().equals("2")) {
                    //显示格式字段
                    getFellowIfAny("kjInterfaceRow").setVisible(true);
                    getFellowIfAny("kjInitDataRow").setVisible(true);
                } else {
                    getFellowIfAny("kjInterfaceRow").setVisible(false);
                    getFellowIfAny("kjInitDataRow").setVisible(false);
                }
                if (baseListbox.getSelectedItem().getValue().toString().equals("3")) {
                    //显示格式字段
                    getFellowIfAny("kjClassImplRow").setVisible(true);
                    getFellowIfAny("kjInitDataRow").setVisible(true);
                } else {
                    getFellowIfAny("kjClassImplRow").setVisible(false);
                    getFellowIfAny("kjInitDataRow").setVisible(false);
                }

            }
        }
        if (comp.getId().equals("copyBtn")) {
            copyWin();
        }
        if (comp.getId().equals("pasteBtn")) {
            pasteWin();
            initAction();
        }


    }


}
