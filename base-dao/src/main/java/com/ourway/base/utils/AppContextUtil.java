package com.ourway.base.utils;


import com.ourway.base.log.LogFactory;
import com.ourway.base.log.Logging;
import com.ourway.base.message.MessageInfo;
import com.ourway.base.dataobject.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 应用Context,包含当前用户相关的所有信息
 */
public class AppContextUtil {
    private static Logging log = LogFactory.getInstance();
    public static String USRINF_COOKIE_KEY = "_uck_";
    private static boolean isClientCache = true;
    public static final String USER_INFO_KEY_IN_SESSION = "_USER_INFO_";
    public static final String ERR_INFO_KEY_IN_SESSION = "_ERR_INFO_";

    /**
     * 当前线程中的用户信息
     */
//	private final static ThreadLocal<UserInfo> currentUser = new ThreadLocal<UserInfo>();
    public static boolean isClientCache() {
        return isClientCache;
    }

    public static void setIsClientCache(boolean isClientCache) {
        AppContextUtil.isClientCache = isClientCache;
    }

    /**
     * 设置用户信息
     *
     * @param userInfo 用户信息
     */
    public static void setUserInfo(UserInfo userInfo, HttpServletRequest request) {
        try {
            //吧request放入到高getrequest中去。
//            GetRequestUtil.setReq(request);
            if (request.getSession() != null) {
                request.getSession().setAttribute(USER_INFO_KEY_IN_SESSION, userInfo);
            }
        } catch (Exception e) {
            log.debug("保存userInfo出错");
        }
    }


    /**
     * 返回当前用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        HttpServletRequest request = null;
        try {
//            request = GetRequestUtil.getReq();
            UserInfo uif = new UserInfo();
            if (request.getSession() != null) {
                uif = (UserInfo) request.getSession().getAttribute(USER_INFO_KEY_IN_SESSION);
                if (uif == null) {
                    uif = new UserInfo();
                }
//				uif.setSessionId(request.getSession().getId());
                uif.setAccessedTime(request.getSession().getLastAccessedTime());
            }
            return uif;
        } catch (Exception e) {
            UserInfo uif = new UserInfo();
            if (request != null && request.getSession() != null) {
//				uif.setSessionId(request.getSession().getId());
                uif.setAccessedTime(request.getSession().getLastAccessedTime());
            }
            return uif;
        }
    }


    /**
     * 新增系统提示信息
     *
     * @param msg
     * @param params
     */
    @Deprecated
    public static void addErrorMessage(String msg, Object... params) {
//        addErrorMessage(MessageInfo.MESSAGE_LEVEL_NOTICE, msg, params);
    }

    /**
     * 新增系统提示信息
     * 新增的消息类型(msgtype=defalut)
     * 新增的消息语言默认为当前系统
     *
     * @param level  　系统的信息级别
     *               MessageItemInfo.MESSAGE_LEVEL_FRAMEWORK_SEVERE  0;
     *               MessageItemInfo.MESSAGE_LEVEL_BIZ_SEVERE  1;
     *               MessageItemInfo.MESSAGE_LEVEL_WARNING  2;
     *               MessageItemInfo.MESSAGE_LEVEL_NOTICE  3;
     * @param msg    系统信息
     * @param params msg中含有"{n}"点位符所需要替换的具体参数值
     */
    @Deprecated
    public static void addErrorMessage(int level, String msg, Object... params) {
//        addErrorMessage(null, CommonConstants.MSGTYPE_DEFAULT_VALUE, level, msg, params);
    }

    /**
     * 添加消息到seedo framework中
     *
     * @param language 该msg是哪种语言版本
     * @param keyType  该msg是哪种类型
     * @param level    　系统的信息级别
     *                 MessageItemInfo.MESSAGE_LEVEL_FRAMEWORK_SEVERE  0;
     *                 MessageItemInfo.MESSAGE_LEVEL_BIZ_SEVERE  1;
     *                 MessageItemInfo.MESSAGE_LEVEL_WARNING  2;
     *                 MessageItemInfo.MESSAGE_LEVEL_NOTICE  3;
     * @param msg      消息内容
     * @param params   msg中含有"{n}"点位符所需要替换的具体参数值
     */
    @Deprecated
    public synchronized static void addErrorMessage(String language, String keyType, int level, String msg, Object... params) {
        appendErrorMessage(keyType, level, msg, params);
    }

    /**
     * 添加消息到seedo framework中
     *
     * @param msgType 该msg是哪种类型
     * @param level   　系统的信息级别
     *                MessageItemInfo.MESSAGE_LEVEL_FRAMEWORK_SEVERE  0;
     *                MessageItemInfo.MESSAGE_LEVEL_BIZ_SEVERE  1;
     *                MessageItemInfo.MESSAGE_LEVEL_WARNING  2;
     *                MessageItemInfo.MESSAGE_LEVEL_NOTICE  3;
     * @param msg     消息内容
     * @param params  msg中含有"{n}"点位符所需要替换的具体参数值
     */
    public synchronized static void appendErrorMessage(String msgType, int level, String msg, Object[] params) {
        MessageUtil.appendMessage(msgType, level, msg, params);
    }

    public static void appendErrorMessage(String msg, int msgCode) {
        appendErrorMessage(msgCode, msg, null);
    }

    public static void appendErrorMessage(int level, String msg, Object[] params) {
        appendErrorMessage(null, level, msg, params);
    }

    public static void appendErrorMessage(String msg, Object[] params) {
//        appendErrorMessage(MessageInfo.MESSAGE_LEVEL_NOTICE, msg, params);
    }

    public static void appendErrorMessage(String msg) {
//        appendErrorMessage(MessageInfo.MESSAGE_LEVEL_NOTICE, msg, null);
    }

    /**
     * 取得系统的信息列表
     *
     * @return
     */
    public static List <MessageInfo> getErrorMessageList() {
        return null;
//        return MessageUtil.getMessageList();
    }

    /**
     * 清除系统信息
     */
    public static void cleanErrorMessage() {
        MessageUtil.cleanMessage();
    }

    /**
     * 清除user登录信息
     */
    public static void cleanUser() {
//        HttpServletRequest request = GetRequestUtil.getReq();
//        if (request != null && request.getSession() != null) {
//            request.getSession().invalidate();
//        }
    }


    /**
     * 设置消息存储类型：1为ThreadLocal，2为HttpSession,默认0为ThreadLocal
     *
     * @param msgSaveType
     */
    public static void setMsgSaveType(int msgSaveType) {
        MessageUtil.setMsgSaveType(msgSaveType);
    }
}
