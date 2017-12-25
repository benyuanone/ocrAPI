package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.List;
import java.util.Map;

/**
 * <p>方法: 表格中的附件处理中心</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class InnerGridAttachFileAction implements ComponentListinerSer {

    private Row getCloseRow(Component component){
        if(component.getParent() instanceof Row){
            return (Row)component.getParent();
        }else
            return getCloseRow(component.getParent());
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            //判断是否有owid，如果没有，则创建一个临时的“tmp”+owid
            String fileExtId = "";
            Row row = getCloseRow(event.getTarget());
            Map<String, Object> ppt = row.getValue();
            if (!TextUtils.isEmpty(ppt.get("owid")))
                fileExtId = ppt.get("owid").toString();
            else if(!TextUtils.isEmpty(ppt.get("fileExtId"))){
                fileExtId = ppt.get("fileExtId").toString();
            }else{
                fileExtId = "tmp" + TextUtils.getUUID();
                ppt.put("fileExtId", fileExtId);//作为处理时候的其它id
            }
            row.setValue(ppt);
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("fileCode"))) {
                AlterDialog.alert("请定义文件类别");
                return;
            }
            _params.put("ppt",fileExtId);
            Component winEdit = Executions.createComponents("/sys/attachfile/attachFile.do", null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if(!TextUtils.isEmpty(_params.get("windowCss")))
                    _win.setStyle(_params.get("windowCss").toString());
                if(!TextUtils.isEmpty(_params.get("title")))
                    _win.setTitle(_params.get("title").toString());
                _win.setClosable(true);
                _win.setMinimizable(true);
                _win.setMinimized(true);
                _win.setParent(window);
                _win.doModal();
                if (_win.isClosePage()) {
                   _win.detach();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
