package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.event.Event;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 CreateSqlAction.java : <p>
 * <p>说明：</p>
 * <pre>根据owid生成相应的数据库记录
 * @author cc
 * @date 2017/7/6 15:53
 * </pre>
 */
public class CreateSqlAction implements ComponentListinerSer {
    public static String PAGE_URL = "sysPageApi/writeSql";

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义gridId");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            List<Map<String,Object>> objs = baseGrid.getSelectRowsData();
            if (null == objs || objs.size() <= 0) {
                AlterDialog.alert("请选择需要操作的记录");
                return;
            }
            if (objs.size() > 1) {
                AlterDialog.alert("一次只能选择一条进行操作");
                return;
            }

            Map<String, Object> rowData = (Map<String, Object>) objs.get(0);
            if (TextUtils.isEmpty(rowData.get("owid"))) {
                AlterDialog.alert("不包含数据的主键，不能操作");
                return;
            }

            PublicData data = PublicData.instantce();
            data.setMethod(PAGE_URL);
            Map<String, Object> ppt = new HashMap<String, Object>(1);
            ppt.put("owid", rowData.get("owid").toString());
            data.setData(JsonUtil.toJson(ppt));
            String result = "";
            String fileName = "";
            try {
                result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
                ResponseMessage mess = JsonUtil.getResponseMsg(result);
                if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                     fileName = mess.getBean().toString();
                }
                FileInputStream inputStream;
                File dosfile = new File("c:/files/" + fileName);
                if (dosfile.exists()) {
                    inputStream = new FileInputStream(dosfile);
                    Filedownload.save(inputStream, "text/plain", DateUtil.getTimeStamp() + ".txt");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}