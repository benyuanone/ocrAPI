package com.ourway.base.zk.utils.treeutils;

import com.ourway.base.zk.models.PageControlVO;
import javafx.scene.control.TreeItem;
import org.zkoss.zul.Treeitem;

/**
 * Created by Administrator on 2017/11/17.
 */
public interface TreeMoveInt {
    /*移动树节点，作为子节点*/
    public int moveToSubTree(Treeitem dragItem, Treeitem dropItem, PageControlVO vo);

    public int moveToPreTree(Treeitem dragItem, Treeitem dropItem, PageControlVO vo);

    public int moveToAfterTree(Treeitem dragItem, Treeitem dropItem, PageControlVO vo);
}
