package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class DoHandleListDataSer implements ComponentListinerSer {
    // [{"apiUrl":"/","fail":"public.sys.failMess","success":"public.sys.success","confirm":"public .sys.confirm","closed":"0","gridId":"dataList"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入配置参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);

        if (TextUtils.isEmpty(_params.get("apiUrl"))) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.NO_API));
            return;
        }
        if (TextUtils.isEmpty(_params.get("success"))) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.NO_SUCCESS));
            return;
        }
        if (TextUtils.isEmpty(_params.get("fail"))) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.NO_FAIL));
            return;
        }
        if (TextUtils.isEmpty(_params.get("gridId"))) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.NO_FAIL));
            return;
        }
        BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
        if (null == grid) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.NO_GRID));
            return;
        }

        List<Map<String, Object>> allData = window.getGridUtils().getAllDatas(grid, false);


        if (!TextUtils.isEmpty(_params.get("confirm"))) {
            if (!AlterDialog.corfirm(I18nUtil.getLabelContent(_params.get("confirm").toString())))
                return;
        }


        ResponseMessage responseMessage = JsonPostUtils.executeObjAPI(allData, _params.get("apiUrl").toString());
        if (TextUtils.isEmpty(responseMessage))
            responseMessage = null;
        if (null == responseMessage || responseMessage.getBackCode() != 0) {
            if (null != responseMessage)
                AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()) + ":" + responseMessage.getErrorMess());
            else
                AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()));
            return;
        } else if (responseMessage.getBackCode() == 0) {
            AlterDialog.alert(I18nUtil.getLabelContent(_params.get("success").toString()));
            tabClose(window, _params);
            windowClose(window, _params);
            //如果不关闭页面，则判断是否要刷新，必须返回List<Map<String,Object>>对象
            refreshRow(window, grid, responseMessage);

        }
    }

    private void refreshRow(BaseWindow window, BaseGrid grid, ResponseMessage responseMessage) {
        try {
            if (responseMessage.getBean() instanceof List) {
                List<Map<String, Object>> afterDatas = (List<Map<String, Object>>) responseMessage.getBean();
                for (Map<String, Object> afterData : afterDatas) {
                    if (!TextUtils.isEmpty(afterData.get("rowId"))) {
                        window.getGridUtils().refreshRow(afterData, grid, afterData.get("rowId").toString());
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void tabClose(BaseWindow window, Map<String, Object> _params) {
        if (!TextUtils.isEmpty(_params.get("closed")) && _params.get("closed").toString().equalsIgnoreCase("1")) {
            try {
                Component comp = Path.getComponent("/mainWin");
                MainAction root = null;
                if (comp instanceof MainAction) {
                    root = (MainAction) comp;
                }
                root.closeTab(window.getParent().getId().replaceAll("_panel", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void windowClose(BaseWindow window, Map<String, Object> _params) {
        if (!TextUtils.isEmpty(_params.get("closed")) && _params.get("closed").toString().equalsIgnoreCase("2")) {
            try {
                window.detach();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

