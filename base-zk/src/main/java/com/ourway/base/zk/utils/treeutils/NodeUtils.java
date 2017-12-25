package com.ourway.base.zk.utils.treeutils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.models.MenuVO;
import com.ourway.base.zk.models.TreeVO;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public class NodeUtils {
    //返回最大的排序，在基础上加上与此小数点向对应的小数
    //获取加入子节点的层次
    public static Integer getCcFromSubTreeitem(Treeitem dropItem) {
        if (dropItem.getValue() instanceof TreeVO) {
            TreeVO vo = (TreeVO) dropItem.getValue();
            return vo.getCc() + 1;
        }
        if (dropItem.getValue() instanceof MenuVO) {
            MenuVO vo = (MenuVO) dropItem.getValue();
            return vo.getCc() + 1;
        }
        return 0;
    }

    //获取跟目标节点相同的级别
    public static Integer getCcFromSameTreeitem(Treeitem dropItem) {
        if (dropItem.getValue() instanceof TreeVO) {
            TreeVO vo = (TreeVO) dropItem.getValue();
            return vo.getCc();
        }
        if (dropItem.getValue() instanceof MenuVO) {
            MenuVO vo = (MenuVO) dropItem.getValue();
            return vo.getCc();
        }
        return 0;
    }

    //获取两个节点之间的序号，求平均序号
    public static double getIndexBetween2Item(Treeitem dropItem,boolean preFlag){
        Treeitem preItem = null;//指定
        Treechildren tc = (Treechildren)dropItem.getParent();
        List<Treeitem> allItems = tc.getChildren();
        int _index = 0;
        //獲取當前的位置
        for (;_index<allItems.size();_index++){
            if(dropItem.getId().equalsIgnoreCase(allItems.get(_index).getId())){
                break;
            }
        }
        if(preFlag&&(_index-1)>=0)
            preItem = allItems.get(_index-1);
        if(!preFlag&&(_index+1)<allItems.size())
            preItem = allItems.get(_index+1);
        TreeVO _vo2 = dropItem.getValue();
        if(null!=preItem){
            TreeVO _vo1 = preItem.getValue();
            return (_vo1.getPx()+_vo2.getPx())/2;
        }else{
            if(preFlag)
                return _vo2.getPx()/2;
            else
                return (_vo2.getPx()*2+0.2)/2;
        }
    }


    //获取当前节点的后一个节点
    public static Treeitem getAfterItem(Treeitem dropItem){
        Treeitem afterItem = null;
        Treechildren tc = (Treechildren)dropItem.getParent();
        List<Treeitem> allItems = tc.getChildren();
        int _index = 0;
        //獲取當前的位置
        for (;_index<allItems.size();_index++){
            if(dropItem.getId().equalsIgnoreCase(allItems.get(_index).getId())){
                break;
            }
        }
        if((_index+1)<allItems.size())
            afterItem = allItems.get(_index+1);
        return afterItem;

    }
    //获取本层次中最大的序号
    public static double getIndexInItem(Treeitem dropItem) {
        double _treeIndex = 0;
        Treechildren treechildren = dropItem.getTreechildren();
        if (null == treechildren) {
            treechildren = new Treechildren();
            treechildren.setParent(dropItem);
            return 1;
        }
        List<Treeitem> treeitemList = treechildren.getChildren();
        if (null != treeitemList && treeitemList.size() > 0) {
            for (Treeitem treeitem : treeitemList) {
                if (treeitem.getValue() instanceof TreeVO) {
                    TreeVO vo = (TreeVO) treeitem.getValue();
                    if (null != vo.getPx() && _treeIndex < vo.getPx()) {
                        _treeIndex = vo.getPx();
                    }
                }
                if (treeitem.getValue() instanceof MenuVO) {
                    MenuVO vo = (MenuVO) treeitem.getValue();
                    if (null != vo.getPx() && _treeIndex < vo.getPx()) {
                        _treeIndex = vo.getPx();
                    }
                }
            }

        }
        return _treeIndex + 1;
    }


    public static double getIndexInSame(Treeitem dropItem,int type){
        TreeVO vo = (TreeVO) dropItem.getValue();
        Treechildren tc = (Treechildren) dropItem.getParent();
          return 0;
    }



    //获取指定节点的父节点路径
    public static String getFpath(String path) {
        int _index = path.lastIndexOf("/");
        if(_index==-1)
            return "-1";
        return path.substring(0, _index);
    }

    public static TreeVO convertFromMenuVo(MenuVO menuVO){
        TreeVO treeVO = new TreeVO();
        BeanUtil.copyBean(menuVO,treeVO,"owid", "fid", "path", "name", "link", "icon", "px", "cc");
        treeVO.setBean(menuVO);
        return treeVO;
    }

    public static void main(String[] args) {
        String s = "-1/35/1010";

        System.out.println(s.split("/35/")[1]);
    }
}
