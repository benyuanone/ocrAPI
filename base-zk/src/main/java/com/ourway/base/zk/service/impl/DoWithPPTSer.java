package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
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
public class DoWithPPTSer implements ComponentListinerSer {
    // [{"apiUrl":"接口路径","fail":"失败提示标签","success":"成功提示标签","confirm":"确认语句标签","closed":"","reload":"0 不刷新主表 1刷新主表"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入查询配置参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);

        window.bindAll2Ppt(true);
        Map<String, Object> ppt = window.getPpt();
        if (TextUtils.isEmpty(ppt.get("owid"))) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.NO_OWID));
            return;
        }

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
        if (!TextUtils.isEmpty(_params.get("confirm"))) {
            if (!AlterDialog.corfirm(I18nUtil.getLabelContent(_params.get("confirm").toString())))
                return;
        }
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(ppt, _params.get("apiUrl").toString());
        if (TextUtils.isEmpty(responseMessage))
            responseMessage = null;
        if (null == responseMessage || responseMessage.getBackCode() != 0) {
            if (null != responseMessage)
                AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()) + ":" + responseMessage.getErrorMess());
            else
                AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()));
            return;
        } else if (responseMessage.getBackCode() == 0) {
            //刷新界面
            if (!TextUtils.isEmpty(_params.get("reload")) && _params.get("reload").toString().equalsIgnoreCase("1")) {
                try {
                    BaseWindow parentW = window.getTopWindow();
                    if (null != parentW)
                        parentW.reloadPpt(true);
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(_params.get("reload")) && _params.get("reload").toString().equalsIgnoreCase("2")) {
                try {
                    window.getTopWindow().reloadPpt(false);
                } catch (Exception e) {
                }
            }
            AlterDialog.alert(I18nUtil.getLabelContent(_params.get("success").toString()));
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

            if (!TextUtils.isEmpty(_params.get("closed")) && _params.get("closed").toString().equalsIgnoreCase("2")) {
                try {
                    window.detach();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

