package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

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
public class TreeAlterAction implements TreeListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
//        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
//        Map<String, Object> _params = paramsList.get(0);
//        _params.put("selItem", treeitem.getValue());
        AlterDialog.alert(pgvo.getLayoutComponent().getKjConstraintKey());

    }

}
