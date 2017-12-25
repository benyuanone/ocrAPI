package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 新增弹出新界面的方法 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class APIAction implements ComponentListinerSer {
    //
//[{"api":"api路径","sucess":"成功提示","fail":"失败提示"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        try {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("api"))) {
                AlterDialog.alert("请定义API接口");
                return;
            }
            if (TextUtils.isEmpty(_params.get("sucess"))) {
                AlterDialog.alert("请定义操作成功后的提示");
                return;
            }
            if (TextUtils.isEmpty(_params.get("fail"))) {
                AlterDialog.alert("请定义操作失败后的提示");
                return;
            }

            ResponseMessage responseMessage = JsonPostUtils.executeAPI(_params.get("api").toString());
            if (TextUtils.isEmpty(responseMessage))
                responseMessage = null;
            if (null == responseMessage || responseMessage.getBackCode() != 0)
                AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()));
            else
                AlterDialog.alert(I18nUtil.getLabelContent(_params.get("sucess").toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
