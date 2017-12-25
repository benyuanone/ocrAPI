package com.ourway.base.zk.utils.treeutils.impl;

import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.treeutils.NodeUtils;
import com.ourway.base.zk.utils.treeutils.TreeMoveInt;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public class MenuTreeMoveIntImpl implements TreeMoveInt {
    @Override
    public int moveToSubTree(Treeitem dragItem, Treeitem dropItem,PageControlVO vo) {
        boolean flag = null == dragItem || null == dropItem || null == dragItem.getValue() || null == dropItem.getValue();
        if (flag)
            return -1;
        TreeVO dragVo = dragItem.getValue();
        TreeVO dropVo = dropItem.getValue();
        //获取drop下面的子节点和获取最大的序号
        int _cc = NodeUtils.getCcFromSubTreeitem(dropItem);
        double _px = NodeUtils.getIndexInItem(dropItem);
        dragVo.setCc(_cc);
        dragVo.setPx(_px);
        dragVo.setFid(dropVo.getOwid());
        dragVo.setPath(NodeUtils.getFpath(dropVo.getPath()) + "/" + dropVo.getOwid() + "/" + dragVo.getOwid());
        dragItem.setValue(dragVo);
        dropItem.getTreechildren().appendChild(dragItem);
        //调用接口，保存修改后的菜单
        List<Object> menus = new ArrayList<Object>(2);
        menus.add(dragVo);
        ResponseMessage mess = JsonPostUtils.executeObjAPI(menus, "/sysMenusApi/updateMoveMenu");
        return 0;
    }


    @Override
    public int moveToPreTree(Treeitem dragItem, Treeitem dropItem,PageControlVO vo) {
        boolean flag = null == dragItem || null == dropItem || null == dragItem.getValue() || null == dropItem.getValue();
        if (flag)
            return -1;
        TreeVO dragVo = dragItem.getValue();
        TreeVO dropVo = dropItem.getValue();
        int _cc = NodeUtils.getCcFromSameTreeitem(dropItem);
        double _px = NodeUtils.getIndexBetween2Item(dropItem, true);
        Treechildren tc = (Treechildren) dropItem.getParent();
        if (dragVo.getCc() == _cc) {
            //表示在同一层，先去掉它
            tc.removeChild(dragItem);
        }
        dragVo.setCc(_cc);
        dragVo.setPx(_px);
        dragVo.setFid(dropVo.getFid());
        dragVo.setPath(NodeUtils.getFpath(dropVo.getPath()) + "/" + dragVo.getOwid());
        dragItem.setValue(dragVo);
        tc.insertBefore(dragItem, dropItem);//在当前节点前添加
        //调用接口，保存修改后的菜单
        List<Object> menus = new ArrayList<Object>(2);
        menus.add(dragVo);
        ResponseMessage mess = JsonPostUtils.executeObjAPI(menus, "/sysMenusApi/updateMoveMenu");
        return 0;
    }

    @Override
    public int moveToAfterTree(Treeitem dragItem, Treeitem dropItem,PageControlVO vo) {
        boolean flag = null == dragItem || null == dropItem || null == dragItem.getValue() || null == dropItem.getValue();
        if (flag)
            return -1;
        TreeVO dragVo = dragItem.getValue();
        TreeVO dropVo = dropItem.getValue();
        int _cc = NodeUtils.getCcFromSameTreeitem(dropItem);
        double _px = NodeUtils.getIndexBetween2Item(dropItem, false);
        Treechildren tc = (Treechildren) dropItem.getParent();
        if (dragVo.getCc() == _cc) {
            //表示在同一层，先去掉它
            tc.removeChild(dragItem);
        }
        dragVo.setCc(_cc);
        dragVo.setPx(_px);
        dragVo.setFid(dropVo.getFid());
        dragVo.setPath(NodeUtils.getFpath(dropVo.getPath()) + "/" + dragVo.getOwid());
        dragItem.setValue(dragVo);
        //获取后一个节点，然后进行插入。
        Treeitem afterItem = NodeUtils.getAfterItem(dropItem);
        if (null != afterItem)
            tc.insertBefore(dragItem, afterItem);//在当前节点前添加
        else
            tc.appendChild(dragItem);
        //调用接口，保存修改后的菜单
        List<Object> menus = new ArrayList<Object>(2);
        menus.add(dragVo);
        ResponseMessage mess = JsonPostUtils.executeObjAPI(menus, "/sysMenusApi/updateMoveMenu");
        return 0;
    }
}
