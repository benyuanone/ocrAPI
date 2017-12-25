package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.treeutils.NodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 MenusDataUtil : <p>
 * <p>说明:系统菜单处理类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/6 11:57
 * </pre>
 */
public class TreeDataUtil {

    public static Boolean moveTree(String url, List<TreeVO> treeVOS) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        data.setData(JsonUtil.toJson(treeVOS.toArray()));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<TreeVO> loadTree(String url) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                if (null != mess.getBean()) {
                    List<MenuVO> menuvos = new ArrayList<MenuVO>();
                    List<Map<String, Object>> datas = (List<Map<String, Object>>) mess.getBean();
                    for (Map<String, Object> map : datas) {
                        menuvos.add(BeanUtil.map2Obj(map, MenuVO.class));
                    }
                    return convertTree(menuvos);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<TreeVO> loadDynamicTree(String url, TreeVO treeVO) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        data.setData(JsonUtil.toJson(treeVO));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0) {
                if (null != mess.getBean()) {
                    List<TreeVO> menuvos = new ArrayList<TreeVO>();
                    List<Map<String, Object>> datas = (List<Map<String, Object>>) mess.getBean();
                    for (Map<String, Object> map : datas) {
                        menuvos.add(com.ourway.base.utils.JsonUtil.map2Bean(map, TreeVO.class));
                    }
                    return menuvos;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    调用修改界面
    public static TreeVO updateTreeNode(TreeVO vo, String url) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        data.setData(JsonUtil.toJson(vo));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                if (null != mess.getBean()) {
                    Map<String, Object> _result = (Map<String, Object>) mess.getBean();
                    vo = com.ourway.base.utils.JsonUtil.map2Bean(_result, TreeVO.class);
                    return vo;
                }
            } else {
                AlterDialog.alert(mess.getErrorMess());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<TreeVO> convertTree(List<MenuVO> menuvos) {
        List<TreeVO> _vos = new ArrayList<TreeVO>();
        for (MenuVO vo : menuvos) {
            TreeVO tvo = NodeUtils.convertFromMenuVo(vo);
            _vos.add(tvo);
        }
        return TreeUtils.getTrees(_vos);
    }

    public static Boolean removeTreeNode(TreeVO vo, String url) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        data.setData(JsonUtil.toJson(vo));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                return true;
            } else {
                if (null != mess)
                    AlterDialog.alert(mess.getErrorMess());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();
        TreeDataUtil.loadTree("/sysTreeDicApi/listAll/15");
    }
}
