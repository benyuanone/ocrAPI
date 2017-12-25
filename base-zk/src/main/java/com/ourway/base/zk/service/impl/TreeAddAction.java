package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.template.DetailTreeNodeAction;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import com.ourway.base.zk.utils.treeutils.NodeUtils;
import org.zkoss.zk.ui.Component;
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
public class TreeAddAction implements TreeListinerSer {

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
        _params.put("type", pgvo.getTreeType());
        switch (pgvo.getTreeType()) {
            case 1://新增子节点
                if (null == treeitem || null == treeitem.getValue()) {
                    //表示根节点
                    _params.put("fid", -1);
                    _params.put("path", "-1/");
                    _params.put("px", NodeUtils.getIndexInItem(treeitem));//本节点序号中最大的
                    _params.put("cc", 1);//层次肯定为1
                } else {
                    TreeVO treeVO = treeitem.getValue();
                    _params.put("fid", treeVO.getOwid());
                    _params.put("path", treeVO.getPath() + "/");
                    _params.put("px", NodeUtils.getIndexInItem(treeitem));
                    _params.put("cc", treeVO.getCc() + 1);
                }
                break;
            case 2://新增同级节点
                if (null == treeitem || null == treeitem.getValue()) {
                    //表示根节点
                    _params.put("fid", -1);
                    _params.put("path", "-1/");
                    _params.put("px", NodeUtils.getIndexInItem(treeitem));//本节点序号中最大的
                    _params.put("cc", 1);//层次肯定为1
                } else {
                    TreeVO treeVO = treeitem.getValue();
                    _params.put("fid", treeVO.getFid());
                    _params.put("path", NodeUtils.getFpath(treeVO.getPath()) + "/");
                    _params.put("px", NodeUtils.getIndexInItem(treeitem.getParentItem()));
                    _params.put("cc", treeVO.getCc());
                }
                break;
            case 3:
                break;
        }

        try {
            DetailTreeNodeAction winEdit = (DetailTreeNodeAction) Executions.createComponents("/template/detailNode.do", null, _params);
            winEdit.setClosable(true);
            winEdit.doModal();
            if (winEdit.isClosePage()) {
                TreeVO vo = winEdit.getBean();
                switch (pgvo.getTreeType()) {
                    case 1:
//                        TreeUtils.addTreeItem(treeitem, window, vo, true, true, true, true, tree.getRightPopup());
                       TreeUtils.doCreateSubTreeItem(vo,treeitem,tree,window);
                        break;
                    case 2:
//                        TreeUtils.addTreeItem(treeitem.getParentItem(), window, vo, true, true, true, true, tree.getRightPopup());
                        TreeUtils.doCreateSameLevelTreeItem(vo,treeitem,tree,window);
                        break;
                    case 3:
                        treeitem.setValue(vo);
                        Treecell cell = (Treecell) treeitem.getFirstChild().getFirstChild();
                        cell.setLabel(vo.getName());
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        TreeUtils.showTree(window, treeitem, tree,datas);
    }

}
