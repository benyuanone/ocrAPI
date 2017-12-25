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
public class OpenTabByPageCASer implements ComponentListinerSer {
    //[{"attr":"pageCa属性","gridId":"dataList","title":"tab标签名"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入查询配置参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);

        if (TextUtils.isEmpty(_params.get("attr"))) {
            AlterDialog.alert(I18nUtil.getLabelContent("未配置属性"));
            return;
        }
        if (TextUtils.isEmpty(_params.get("gridId"))) {
            AlterDialog.alert(I18nUtil.getLabelContent("未设置表格"));
            return;
        }
        if (TextUtils.isEmpty(_params.get("title"))) {
            AlterDialog.alert(I18nUtil.getLabelContent("未设置标签"));
            return;
        }
        BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
        List<Map<String, Object>> datas = grid.getSelectRowsData();
        if (null != datas && datas.size() > 1) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.MUL_SELECT));
            return;
        }
        if (null == datas || datas.size() <= 0) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.MUL_SELECT));
            return;
        }
        Map<String, Object> data = datas.get(0);
        String pageCa = data.get(_params.get("attr").toString()).toString();
        String title = _params.get("title").toString();

        Component comp = Path.getComponent("/mainWin");
        MainAction root = null;
        if (comp instanceof MainAction) {
            root = (MainAction) comp;
        }
        try {
            root.openFunByPageCa(I18nUtil.getLabelContent(title) + data.get("indexno"), pageCa);
        }catch(Exception e){
            AlterDialog.alert(I18nUtil.getLabelContent("public.sys.error.openError"));
        }
    }
}

