package com.ourway.base.zk.utils;

import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.EmployVO;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.util.List;
import java.util.Map;

/**
 * <p>ZKSessionUtils.java : <p>
 * <p>说明：zk的一些session操作放在这里</p>
 * <pre>
 * @author JackZhou
 * @date 2017/2/27 23:16
 * </pre>
 */
public class ZKSessionUtils {
    //    默认界面高度
    public static final int SCREEN_DEFAULT_HEIGHT = 1024;
    public static final int DEFAULT_PAGESIZE = 1024;
    public static int screenHeight = 1024;
    public static int screenWidth = 1024;
    public static final String LAST_LINK = "lastLink";
    public static final String ACCEPT_HEADER = "userAgent";
    public static final String CLIENT_COOKIE = "clientCookie";
    public static final String PRIVS_SESSION = "userPrivsSession";
    /*临时拷贝对象*/
    public static final String COPY_OBJ = "clientCopyObject";

    /**
     * <p>方法:setTempObject 用于拷贝的时候，保存个人的临时对象 </p>
     * <ul>
     * <li> @param ppt 临时的页面对象，key-value对应</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 12:55  </li>
     * </ul>
     */
    public static void setTempObject(BaseWindow window) {
        Map<String, Object> ppt = window.bind2Object();
        Session s = Sessions.getCurrent();
        s.setAttribute(COPY_OBJ, ppt);
    }

    /**
     * <p>方法:getTempObject 获取临时保存的复制内容 </p>
     * <ul>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 12:59  </li>
     * </ul>
     */
    public static Map<String, Object> getTempObject() {
        Session s = Sessions.getCurrent();
        if (null == s.getAttribute(COPY_OBJ))
            return null;
        else
            return (Map<String, Object>) s.getAttribute(COPY_OBJ);
    }


    public static void setCookie(String ck) {
        Session s = Sessions.getCurrent();
        s.setAttribute(CLIENT_COOKIE, ck);
    }

    public static String getCookie() {
        Session s = Sessions.getCurrent();
        if (null == s)
            return "";
        System.out.println("cookie:" + s.getAttribute(CLIENT_COOKIE));
        if (TextUtils.isEmpty(s.getAttribute(CLIENT_COOKIE)))
            return "";
        else
            return s.getAttribute(CLIENT_COOKIE).toString();
//        return null == s.getAttribute(CLIENT_COOKIE) ? "" : s.getAttribute(CLIENT_COOKIE).toString();
    }


    public static void setUserAgent(String userAgent) {
        Session s = Sessions.getCurrent();
        s.setAttribute(ACCEPT_HEADER, userAgent);
    }

    public static String getUserAgent() {
        Session s = Sessions.getCurrent();
        if (null == s || null == s.getAttribute(ACCEPT_HEADER))
            return "Mozilla/5.0 /Windows; U; Windows NT 4.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.0";
        else
            return s.getAttribute(ACCEPT_HEADER).toString();
    }

    public static String getSessionId() {
        Session s = Sessions.getCurrent();
        if (null != s && null != s.getAttribute(ZKConstants.ZKUSER_SESSION)) {
            EmployVO user = (EmployVO) s.getAttribute(ZKConstants.ZKUSER_SESSION);
            return user.getSessionId();
        } else
            return "";
    }

    public static void setZkUser(EmployVO user) {
        Session s = Sessions.getCurrent();
        s.setAttribute(ZKConstants.ZKUSER_SESSION, user);
    }

    public static EmployVO getZkUser() {
        Session s = Sessions.getCurrent();
        if (null != s && null != s.getAttribute(ZKConstants.ZKUSER_SESSION)) {
            EmployVO user = (EmployVO) s.getAttribute(ZKConstants.ZKUSER_SESSION);
            return user;
        } else
            return null;
    }

    public static void setScreenHeight(int height) {
        Session s = Sessions.getCurrent();
        s.setAttribute(ZKConstants.ZKHEIGHT_SESSION, height);
    }

    public static void setScreenWidth(int width) {
        Session s = Sessions.getCurrent();
        s.setAttribute(ZKConstants.ZKWIDTH_SESSION, width);
    }

    public static int getScreenWidth() {
        Session s = Sessions.getCurrent();
        if (null != s.getAttribute(ZKConstants.ZKWIDTH_SESSION))
            return (Integer) s.getAttribute(ZKConstants.ZKWIDTH_SESSION);
        else
            return 1024;
    }

    public static int getScreenHeight() {
        Session s = Sessions.getCurrent();
        if (null != s.getAttribute(ZKConstants.ZKHEIGHT_SESSION))
            return (Integer) s.getAttribute(ZKConstants.ZKHEIGHT_SESSION);
        else
            return 768;
    }

    public static void setLastLink(Object linkUrl) {
        Session s = Sessions.getCurrent();
        s.setAttribute(ZKSessionUtils.LAST_LINK, linkUrl);
    }

    public static Object getLaskLink() {
        Session s = Sessions.getCurrent();
        if (null == s.getAttribute(ZKSessionUtils.LAST_LINK))
            return null;
        return s.getAttribute(ZKSessionUtils.LAST_LINK);
    }


    public static void setPrivsSession(Map<String, List<String>> privsMap) {
        Session s = Sessions.getCurrent();
        EmployVO employVO = getZkUser();
        s.setAttribute(employVO.getEmpId() + ZKSessionUtils.PRIVS_SESSION, privsMap);
    }

    public static List<String> getPrivsSession(String pageCa) {
        Session s = Sessions.getCurrent();
        EmployVO employVO = getZkUser();
        if (null == s.getAttribute(employVO.getEmpId() + ZKSessionUtils.PRIVS_SESSION))
            return null;
        Map<String, List<String>> pageCaList = (Map<String, List<String>>) s.getAttribute(employVO.getEmpId() + ZKSessionUtils.PRIVS_SESSION);
        if (null == pageCaList.get(pageCa))
            return null;
        return pageCaList.get(pageCa);
    }

    public static void clearUser() {
        Session s = Sessions.getCurrent();
        s.removeAttribute(ZKConstants.ZKUSER_SESSION);
        s.removeAttribute(ZKSessionUtils.CLIENT_COOKIE);
        s.removeAttribute(ZKSessionUtils.PRIVS_SESSION);
    }
}
