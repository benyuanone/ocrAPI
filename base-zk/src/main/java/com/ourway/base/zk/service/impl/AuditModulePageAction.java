package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.ZkUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.*;

/**
 * <p>方法: 新增弹出新界面的方法 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class AuditModulePageAction implements ComponentListinerSer {
    //[{"pageCa":"打开的路径","className":"类","title":"","windowCss":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        try {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }
            if (TextUtils.isEmpty(_params.get("className"))) {
                AlterDialog.alert("请输入工作流名");
                return;
            }
            Map<String, Object> ppt = window.getPpt();
            ppt.put("flowClassName", _params.get("className"));
            if (TextUtils.isEmpty(ppt.get("owid"))) {
                AlterDialog.alert("不包含owid,不能跳转");
                return;
            }
            //业务流程主键
            ppt.put("flowBusinessOwid", ppt.get("owid"));


            //根据pageca拿到页面的相关信息
            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在模板中定义指定ca的页面");
                return;
            }
            if (vo.getPageCustomer() == 0)
                url = vo.getPageTemplatePath();
            else
                url = vo.getPageCa();
            ppt.put("pageCa", _params.get("pageCa"));

            Component winEdit = Executions.createComponents(url, null, ppt);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss")))
                    _win.setStyle(_params.get("windowCss").toString());
                if (!TextUtils.isEmpty(_params.get("title")))
                    _win.setTitle(I18nUtil.getLabelContent(_params.get("title").toString()));
                _win.setParentPpt(_params);
                _win.setTopWindow(window);
                _win.doModal();
                if (_win.isClosePage()) {


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean doCheckPrivs(String owid, String className) {
        String url = "/sysFlowControlCenter/checkAuditPrivs";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("owid", owid);
        params.put("flowClassName", className);
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, url);
        if (TextUtils.isEmpty(responseMessage))
            return false;
        if (null == responseMessage || responseMessage.getBackCode() != 0) {
            return false;
        } else
            return new Boolean(responseMessage.getBean().toString());
    }

}
