package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.template.DetailTreeNodeAction;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
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
public class TreeRemoveAction implements TreeListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("url"))) {
            AlterDialog.alert("请定义树加载接口");
            return;
        }
        _params.put("selItem", treeitem.getValue());
        if(null==treeitem.getValue()){
            AlterDialog.alert(ERCode.TREE_NO_OPERATE);
            return;
        }
        TreeVO treeVO = (TreeVO)treeitem.getValue();
        if(AlterDialog.corfirm(I18nUtil.getLabelContent(ERCode.TREE_REMOVE_CONFIRM))) {
            Boolean result = TreeDataUtil.removeTreeNode(treeVO, _params.get("url").toString());
            if (result) {
                treeitem.getParent().removeChild(treeitem);
            }
        }
    }

}
