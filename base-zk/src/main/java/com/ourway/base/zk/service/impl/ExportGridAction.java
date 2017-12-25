package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ExcelUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 导出表格数据</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class ExportGridAction implements ComponentListinerSer {
//[{"title":"","label":"","gridId":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            //"gridId"指定表格
            //获取所有主表中当个控件的值
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("title"))) {
                AlterDialog.alert("请输入导出标题");
                return;
            }
            if (TextUtils.isEmpty(_params.get("label"))) {
                AlterDialog.alert("请输入导出文件名");
                return;
            }
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (null == grid) {
                AlterDialog.alert("对象不存在");
                return;
            }
            List<Map<String,Object>> result= grid.exportFilter();
            ExcelUtils.exportXls(grid.getControlVOS(),result,I18nUtil.getLabelContent(_params.get("title").toString()),I18nUtil.getLabelContent(_params.get("label").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
