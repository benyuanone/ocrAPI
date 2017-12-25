package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 PageDataUtil : <p>
 * <p>说明:获取页面配置信息数据类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 22:03
 * </pre>
 */
public class LoginUtil {
    /*页面对应的控件获取api方法*/
    public static String LOGIN_URL = "sysUserApi/login";
    public static String LOGIN_OUT_URL = "sysUserApi/logout";

    /**
     * <p>方法:getControls 根据pageca获取当前page下面的控件 </p>
     * <ul>
     * <li> @param pageCA pageca</li>
     * <li>@return java.util.List<com.ourway.base.zk.models.PageControl>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 23:57  </li>
     * </ul>
     */
    public static EmployVO login(Map<String, Object> ppt) {
        PublicData data = PublicData.instantce();
        data.setMethod(LOGIN_URL);
        data.setData(JsonUtil.toJson(ppt));
        data.setSessionId(TextUtils.getUUID());
        String result = "";
        try {
            result = HttpUtils.loginPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0) {
                EmployVO employVO = BeanUtil.map2Obj((Map<String, Object>) mess.getBean(), EmployVO.class);
                return employVO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
    *<p>方法:logOut 用户登出 </p>
    *<ul>
     *<li> @param ppt </li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/8 15:25  </li>
    *</ul>
    */
    public static void logOut() {
        PublicData data = PublicData.instantce();
        data.setMethod(LOGIN_OUT_URL);
        data.setSessionId(TextUtils.getUUID());
        String result = "";
        try {
            result = HttpUtils.logOutPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();
//        LoginUtil.getControls("/login.do", pageControls);
    }
}
