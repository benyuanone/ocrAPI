package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
public class MenusDataUtil {
    /*页面对应的控件获取api方法*/
    public static String URL = "sysMenusApi/listUserMenus";

    /**
     * <p>方法:getControls 根据pageca获取当前page下面的控件 </p>
     * <ul>
     * <li> @param pageCA pageca</li>
     * <li>@return java.util.List<com.ourway.base.zk.models.PageControl>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 23:57  </li>
     * </ul>
     */
    public static List<TreeVO> getMenus() {
        PublicData data = PublicData.instantce();
        data.setMethod(URL);
//        Map<String, String> ppt = new HashMap<String, String>();
//        data.setData(JsonUtil.toJson(ppt));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0) {
                if (null != mess.getBean()) {
                    List<MenuVO> menuvos = new ArrayList<MenuVO>();
                    List<Map<String, Object>> datas = (List<Map<String, Object>>) mess.getBean();
                    for (Map<String, Object> map : datas) {
                        menuvos.add(BeanUtil.map2Obj(map, MenuVO.class));
                    }
                    handlePageFuncs(menuvos);
                    return convertTree(menuvos);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //处理当前登录用户的功能，除了菜单和界面以外的功能
    private static void handlePageFuncs(List<MenuVO> menuVOList) {
        Map<String, List<String>> funcMap = new HashMap<String, List<String>>();
        String _pageCa = "";
        String _pageCaTmp[];
        for (MenuVO menuVO : menuVOList) {
            _pageCa = menuVO.getPageCa();
            switch (menuVO.getType()) {
                case 3: //按钮
                    _pageCaTmp = _pageCa.split("\\=");
                    if (_pageCaTmp.length != 2 || TextUtils.isEmpty(_pageCaTmp[0]) || TextUtils.isEmpty(_pageCaTmp[1]))
                        continue;
                    List<String> components = new ArrayList<String>();
                    if (null != funcMap.get(_pageCaTmp[0] + "_btns"))
                        components = funcMap.get(_pageCaTmp[0] + "_btns");
                    components.add(_pageCaTmp[1]);
                    funcMap.put(_pageCaTmp[0] + "_btns", components);
                    break;
                case 1://控件
                    _pageCaTmp = _pageCa.split("\\=");
                    if (_pageCaTmp.length != 2 || TextUtils.isEmpty(_pageCaTmp[0]) || TextUtils.isEmpty(_pageCaTmp[1]))
                        continue;
                    components = new ArrayList<String>();
                    if (null != funcMap.get(_pageCaTmp[0] + "_comps"))
                        components = funcMap.get(_pageCaTmp[0] + "_comps");
                    components.add(_pageCaTmp[1]);
                    funcMap.put(_pageCaTmp[0] + "_comps", components);
                    break;
            }
        }
        ZKSessionUtils.setPrivsSession(funcMap);
    }

    private static List<TreeVO> convertTree(List<MenuVO> menuvos) {
        List<TreeVO> _vos = new ArrayList<TreeVO>();
        for (MenuVO vo : menuvos) {
            if (null != vo.getType() && (vo.getType() == 1 || vo.getType() == 3))
                continue;
            TreeVO tvo = new TreeVO();
            BeanUtil.copyBean(vo, tvo, "fid", "path","name", "link", "icon");
            tvo.setOwid(vo.getOwid());
            tvo.setBean(vo);
            _vos.add(tvo);
        }
        return TreeUtils.getTrees(_vos);
    }


    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();
        MenusDataUtil.getMenus();
    }
}
