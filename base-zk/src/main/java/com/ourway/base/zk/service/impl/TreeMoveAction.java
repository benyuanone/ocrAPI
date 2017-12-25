package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.MenuVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
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
public class TreeMoveAction implements TreeListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("url"))) {
            AlterDialog.alert("请定义树加载接口");
            return;
        }
        //source - drag ,dest - drop
        Treeitem sourceItem = tree.getDragItem();
        Treeitem destItem = tree.getDropItem();
        if (sourceItem.getParent() == destItem.getParent()) {
//            AlterDialog.alert("tongyiji");
            changeSameLevelOrder(sourceItem, destItem, _params.get("url").toString());
        } else {
//            AlterDialog.alert("butongyiji");
            changeDiffLevelOrder(sourceItem, destItem, _params.get("url").toString());
        }


    }

    private void changeSameLevelOrder(Treeitem sourItem, Treeitem destItem, String url) {
        if (null == sourItem.getValue() || null == destItem.getValue()) {
            return;
        }
        TreeVO sourVo = (TreeVO) sourItem.getValue();
        TreeVO destVo = (TreeVO) destItem.getValue();
        //更换顺序
//        int _sourceIndex = null == sourVo.getPx() ? 0 : sourVo.getPx();
//        int _destIndex = null == destVo.getPx() ? 0 : destVo.getPx();
//        sourVo.setPx(_destIndex);
//        destVo.setPx(_sourceIndex);
        //调用
        List<TreeVO> menus = new ArrayList<TreeVO>(2);
        menus.add(sourVo);
        menus.add(destVo);

        Boolean flag = TreeDataUtil.moveTree(url, menus);
        if (flag) {
            ((Treecell) sourItem.getTreerow().getFirstChild()).setLabel(destVo.getName());
            sourItem.setValue(destVo);
            ((Treecell) destItem.getTreerow().getFirstChild()).setLabel(sourVo.getName());
            destItem.setValue(sourVo);
            //更换两个节点之间的数据
            Treechildren _sourChildren;
            Treechildren _destChildren;
            _sourChildren = sourItem.getTreechildren();
            _destChildren = destItem.getTreechildren();
            if (null != _sourChildren)
                _sourChildren.setParent(destItem);
            if (null != _destChildren)
                _destChildren.setParent(sourItem);
        }
    }

    private void changeDiffLevelOrder(Treeitem sourItem, Treeitem destItem, String url) {
        if (null == sourItem.getValue()) {
            return;
        }
        TreeVO sourMenu = (TreeVO) sourItem.getValue();
        //移动到根节点之下
        if (null == destItem.getValue()) {
            sourMenu.setFid(-1);
//            sourMenu.setPx(destItem.getTreechildren().getChildren().size() + 1);
            List<TreeVO> menus = new ArrayList<TreeVO>(1);
            menus.add(sourMenu);
            Boolean flag = TreeDataUtil.moveTree(url, menus);
            if (flag) {
                sourItem.setParent(destItem.getTreechildren());
            }
        } else {
            //表示目标节点不为空
            TreeVO destMenu = (TreeVO) destItem.getValue();
            sourMenu.setFid(destMenu.getOwid());
//            if (null != destItem.getTreechildren()) {
//                sourMenu.setPx(destItem.getTreechildren().getChildren().size() + 1);
//            } else
//                sourMenu.setPx(1);
            List<TreeVO> menus = new ArrayList<TreeVO>(1);
            menus.add(sourMenu);
            Boolean flag = TreeDataUtil.moveTree(url, menus);
            if (flag) {
                if (null != destItem.getTreechildren()) {
                    sourItem.setParent(destItem.getTreechildren());
                } else {
                    Treechildren tc = new Treechildren();
                    tc.setParent(destItem);
                    sourItem.setParent(tc);
                }
            }

        }
    }

}
