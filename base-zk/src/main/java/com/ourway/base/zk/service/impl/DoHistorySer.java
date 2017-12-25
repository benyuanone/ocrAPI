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
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * 弹出框显示历史轨迹记录
 * Created by jack on 2017/5/28.
 */
public class DoHistorySer implements ComponentListinerSer {
   // [{"className":"类名","windowName":"界面名称"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入查询配置参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);

        if (TextUtils.isEmpty(_params.get("className"))) {
            AlterDialog.alert(I18nUtil.getLabelContent("未定义日志类名"));
            return;
        }
        Map<String,Object> ppt = window.getPpt();
        if(TextUtils.isEmpty(ppt.get("owid"))){
            AlterDialog.alert(I18nUtil.getLabelContent("无业务主键"));
            return;
        }
        _params.put("owid",ppt.get("owid").toString());
        Component winEdit = Executions.createComponents("/sys/history/historyDetail.do", null, _params);
        if (winEdit instanceof BaseWindow) {
            BaseWindow _win = (BaseWindow) winEdit;
            if (!TextUtils.isEmpty(_params.get("windowName")))
               _win.setTitle(I18nUtil.getLabelContent(_params.get("windowName").toString()));
            _win.setTopWindow(window);//弹出前设置上级窗口
            _win.setParentPpt(_params);
            _win.doHighlighted();
            _win.setClosable(true);
            if (_win.isClosePage()) {


            }

        }


    }
}

