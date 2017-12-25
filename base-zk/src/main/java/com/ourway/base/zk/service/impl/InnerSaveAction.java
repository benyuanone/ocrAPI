package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

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
public class InnerSaveAction implements ComponentListinerSer {
//[{"gridId":"数据来源","url":"保存接口","tree":"treeid，表示必选","freshTree":"0,不刷新左侧树，1刷新"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            //获取所有主表中当个控件的值
            Map<String, Object> ppt = window.bindAll2Ppt(true);
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("url"))) {
                AlterDialog.alert("请定义需要提交保存的API接口");
                return;
            }
            if (!TextUtils.isEmpty(_params.get("gridId"))) {
                String[] _ids = _params.get("gridId").toString().split("\\,");
                for (String s : _ids) {
                    BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(s);
                    if (null != baseGrid)
                        ppt.put(s, GridUtils.instance().getAllDatas(baseGrid,true));
                }
            }

            //调用接口进行保存
            ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
            if (null == message) {
                AlterDialog.alert("操作失败");
                return;
            }
            if (message.getBackCode() == 0) {
                AlterDialog.alert("操作成功");
                String[] _ids = _params.get("gridId").toString().split("\\,");
                for (String s : _ids) {
                    BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(s);
                    baseGrid.refreshInlineEditRow();
                    if (!TextUtils.isEmpty(_params.get("freshTree"))&&_params.get("freshTree").toString().equalsIgnoreCase("1")) {
                        window.loadTreeData();
                    }
                }
            } else {
                AlterDialog.alert(message.getErrorMess());
                return;
            }
        }
        catch (Exception e) {
           throw e;
        }

    }
}
