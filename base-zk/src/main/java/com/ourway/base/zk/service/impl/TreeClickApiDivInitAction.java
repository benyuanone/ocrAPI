package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

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
public class TreeClickApiDivInitAction implements TreeListinerSer {
    //[{"url":"API"}]
    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        String url = "";
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("url"))) {
            AlterDialog.alert("请输入API接口");
            return;
        }
        url = _params.get("url").toString();

        TreeVO treeVO = new TreeVO();
        if (null == treeitem.getValue()) {
            treeVO.setFid(-1);
            treeVO.setPath("-1/");
            treeVO.setPx(1.0);
            treeVO.setCc(1);
            treeVO.setOwid(0);
        } else
            treeVO = treeitem.getValue();

        window.setBaseTree(tree);
        window.setBaseTreeType(3);
        window.setBaseTreeItem(treeitem);
        Map<String, Object> params = JsonUtil.jsonToMap(JsonUtil.toJson(treeVO));
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, url);
        if (TextUtils.isEmpty(responseMessage))
            responseMessage = null;
        if (null == responseMessage || responseMessage.getBackCode() != 0) {
            AlterDialog.alert("TreeClickApiDivInitAction Error");
            return;
        } else {
            window.setPpt((Map<String, Object>) responseMessage.getBean());
            PageUtils.resetGridComponent(window);
            window.bind2Page();

        }

    }

}
