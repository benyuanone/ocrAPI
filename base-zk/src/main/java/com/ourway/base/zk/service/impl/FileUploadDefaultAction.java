package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.bandbox.ListBandbox;
import com.ourway.base.zk.component.BaseFileUpload;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 单文件上传后的回填数据 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class FileUploadDefaultAction implements ComponentListinerSer {

    //str的格式： a-b,c-d,e-f
    private void setValueToMainGrid(BaseWindow window, String str, Map<String, Object> dataMap) {
        String[] strs = str.split("\\,");
        String[] eles = null;
        Object obj = null;
        for (String s : strs) {
            eles = s.split("\\-");
            obj = ComponentUtils.doGetObjByLabel(eles[0], dataMap);
            if (!TextUtils.isEmpty(eles[1])) {
                Component component = window.getFellowIfAny("mainTableGrid_" + eles[1]);
                DataBinder.binderToPage(eles[1], obj, component);
            }
        }
    }

    private void setVlaueToRow(BaseFileUpload listBandbox, String str, Map<String, Object> dataMap) {
        Row row = getCurrentRow(listBandbox);
        List<Component> list = row.getChildren();
        String[] strs = str.split("\\,");
        String[] eles = null;
        Object obj = null;
        for (String s : strs) {
            eles = s.split("\\-");
            obj = ComponentUtils.doGetObjByLabel(eles[0], dataMap);
            if (!TextUtils.isEmpty(eles[1])) {
                for (Component component : list) {
                    if (!TextUtils.isEmpty(component.getAttribute("property")) && component.getAttribute("property").toString().equalsIgnoreCase(eles[1])) {
                        DataBinder.binderToPage(eles[1], obj, component);
                        break;
                    }
                }
            }
        }

    }

    private Row getCurrentRow(Component component) {
        if (component.getParent() instanceof Row)
            return (Row) component.getParent();
        else
            return getCurrentRow(component.getParent());
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        Map<String, Object> selVal = null;
        BaseFileUpload listBandbox = null;
        try {
            if (null != event && event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                listBandbox = (BaseFileUpload) keyEvent.getTarget();
                selVal= listBandbox.getFilePpt();
            }
            if (null == selVal)
                return;
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            //把数据复制到主表对应的属性中区
            if (!TextUtils.isEmpty(_params.get("form"))) {
                setValueToMainGrid(window, _params.get("form").toString(), selVal);
            }
            //把数据复制到表格当前行的属性中去
            if (!TextUtils.isEmpty(_params.get("row"))) {
                setVlaueToRow(listBandbox, _params.get("row").toString(), selVal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
