package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;

import javax.xml.soap.Text;
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
public class ResetGridAction implements ComponentListinerSer {
//[{"gridId":"","tree":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入reset配置参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        if (null == paramsList && paramsList.size() <= 0) {
            AlterDialog.alert("请输入需要重置控件的Grid");
            return;
        }

        for (Map<String, Object> map : paramsList) {
            if(!TextUtils.isEmpty(map.get("tree"))){
                BaseTree tree = (BaseTree) window.getFellowIfAny(map.get("tree").toString());
                tree.setSelectedItem(null);
            }
            if (!TextUtils.isEmpty(map.get("gridId"))) {
                if (null != window.getFellowIfAny(map.get("gridId").toString())) {
                    Div grid = (Div) window.getFellowIfAny(map.get("gridId").toString());
                    PageUtils.resetGridComponent(grid);
                }
            }
        }
    }
}
