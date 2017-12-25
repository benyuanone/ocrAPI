package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.InnerGridInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
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
public class InnerRefreshAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            Map<String, Object> ppt = window.bindAll2Ppt(false);
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (null == grid) {
                AlterDialog.alert("刷新对象不存在");
                return;
            }
            if (null != grid.getLayoutVO().getControlLoad() && grid.getLayoutVO().getControlLoad() == 1 && !TextUtils.isEmpty(grid.getLayoutVO().getControlInt())) {
                InnerGridInitSer ser = new InnerGridApiInitSer();
                ser.initPage(window, grid.getLayoutVO(), window.getPpt(), grid);
            }
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.GRID_REFRESH_SUCCESS));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
