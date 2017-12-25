package com.ourway.test;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

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
public class FlowTestAction extends BaseWindow {
    protected Log info = LogFactory.getLog(FlowTestAction.class);

    BaseTextbox content;
    BaseTextbox content1;
    BaseTextbox content2;

    BaseButton button_addBtn;
    BaseButton reject1_addBtn;
    BaseButton agree1_addBtn;
    BaseButton reject2_addBtn;
    BaseButton agree2_addBtn;
    String owid;


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        Map map = event.getArg();

        content = (BaseTextbox) getFellowIfAny("content");
        content1 = (BaseTextbox) getFellowIfAny("content1");
        content2 = (BaseTextbox) getFellowIfAny("content2");
        button_addBtn = (BaseButton) getFellowIfAny("button_addBtn");
        reject1_addBtn = (BaseButton) getFellowIfAny("reject1_addBtn");
        agree1_addBtn = (BaseButton) getFellowIfAny("agree1_addBtn");
        reject2_addBtn = (BaseButton) getFellowIfAny("reject2_addBtn");
        agree2_addBtn = (BaseButton) getFellowIfAny("agree2_addBtn");
        button_addBtn.addEventListener(Events.ON_CLICK, this);
        reject1_addBtn.addEventListener(Events.ON_CLICK, this);
        agree1_addBtn.addEventListener(Events.ON_CLICK, this);
        reject2_addBtn.addEventListener(Events.ON_CLICK, this);
        agree2_addBtn.addEventListener(Events.ON_CLICK, this);
        if(!TextUtils.isEmpty(map.get("owid"))) {
            owid = map.get("owid").toString();
            init(owid);
        }else{
            getFellowIfAny("step1").setVisible(false);
            getFellowIfAny("step2").setVisible(false);
        }
    }

    void init(String owid){
       Map<String,Object> params = new HashMap<String,Object>(1);
       params.put("owid",owid);
       ResponseMessage message =  JsonPostUtils.executeAPI(params,"/sysFlowTest/detail");
       if(null!=message&&message.getBackCode()==0){
           ppt = (Map<String,Object>)message.getBean();
           content.setText(ppt.get("content")+"");
           if(null!=ppt.get("status")){
               switch(Integer.parseInt(ppt.get("status").toString())){
                   case 0:
                       getFellowIfAny("step1").setVisible(false);
                       getFellowIfAny("step2").setVisible(false);
                       break;
                   case 1:
                       getFellowIfAny("step1").setVisible(true);
                       getFellowIfAny("step2").setVisible(false);
                       content.setDisabled(true);
                       button_addBtn.setDisabled(true);
                       break;
                   case  2:
                       agree1_addBtn.setDisabled(true);
                       reject1_addBtn.setDisabled(true);
                       content1.setDisabled(true);
                       getFellowIfAny("step2").setVisible(true);
                       break;
               }
           }
       }

    }

    @Override
    public void onEvent(Event event) throws Exception {
        if(event.getTarget().getId().equals("button_addBtn")){
             Map<String,Object> params = new HashMap<String,Object>(2);
             params.put("owid",owid);
             params.put("content",content.getText());
            ResponseMessage message =  JsonPostUtils.executeAPI(params,"/sysFlowTest/save1");
            if(null!=message&&message.getBackCode()==0){
                AlterDialog.alert("提交成功");
                detach();
            }
        }
        if(event.getTarget().getId().equals("agree1_addBtn")){
             Map<String,Object> params = new HashMap<String,Object>(2);
             params.put("owid",owid);
             params.put("content",content1.getText());
            ResponseMessage message =  JsonPostUtils.executeAPI(params,"/sysFlowTest/save2");
            if(null!=message&&message.getBackCode()==0){
                AlterDialog.alert("提交成功");
                detach();
            }
        }
    }
}
