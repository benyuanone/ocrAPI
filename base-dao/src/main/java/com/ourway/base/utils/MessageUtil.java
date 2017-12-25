package com.ourway.base.utils;

import com.ourway.base.CommonConstants;
import com.ourway.base.message.MessageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>方法 MessageUtil : <p>
 * <p>说明:通用消息处理类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/12 3:03
 * </pre>
 */
public class MessageUtil {

    static ThreadLocal<List<MessageInfo>> messageList;
    private static Log log = LogFactory.getLog(MessageUtil.class);
    private static int msgSaveType;


    /**
    *<p>方法:setMsgSaveType 设置消息存储类型：1为ThreadLocal，2为HttpSession,默认0为ThreadLocal </p>
    *<ul>
     *<li> @param msgSaveType </li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/12 3:04  </li>
    *</ul>
    */
    public static void setMsgSaveType(int msgSaveType) {
        MessageUtil.msgSaveType = msgSaveType;
    }

    /**
     * 新增系统提示信息
     *
     * @param msg
     * @param params
     */

    public static void addMessage(String msg, Object... params) {
        addMessage(MessageInfo.MESSAGE_LEVEL_NOTICE, msg, params);
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
    public static void addMessage(int level, String msg, Object... params) {
        addMessage(null, CommonConstants.MSGTYPE_DEFAULT_VALUE, level, msg, params);
    }

    public static final String SYS_INFO_KEY_IN_SESSION = "_SYS_INFO_";

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
    public static void addMessage(String language, String keyType, int level, String msg, Object... params) {
        appendMessage(keyType, level, msg, params);
    }

    /**
     * 添加消息到seedo framework中
     *
     * @param msgType 该msg是哪种类型
     * @param level   　系统的信息级别或者为消息代码（msgCode）
     *                MessageItemInfo.MESSAGE_LEVEL_FRAMEWORK_SEVERE  0;
     *                MessageItemInfo.MESSAGE_LEVEL_BIZ_SEVERE  1;
     *                MessageItemInfo.MESSAGE_LEVEL_WARNING  2;
     *                MessageItemInfo.MESSAGE_LEVEL_NOTICE  3;
     * @param msg     消息内容
     * @param params  msg中含有"{n}"点位符所需要替换的具体参数值
     */
    public static void appendMessage(String msgType, int level, String msg, Object[] params) {
        MessageInfo mi = new MessageInfo(msgType, level, msg, params);
        List<MessageInfo> l = getMsg(getReq());
        if (l == null) {
            l = new ArrayList<MessageInfo>();
        }
        if (!l.contains(mi)) {
            l.add(0, mi);
            addMsg(getReq(), l);
        }
    }

    public static void appendMessage(int level, String msg, Object[] params) {
        appendMessage(null, level, msg, params);
    }

    public static void appendMessage(String msg, Object[] params) {
        appendMessage(null, MessageInfo.MESSAGE_LEVEL_NOTICE, msg, params);
    }

    /**
     * 添加消息，只需要传入消息代码，即可获取消息内容。
     * 错误消息定义必须按照规则
     *
     * @param level 错误消息代码
     */
    public static void appendMessage(int level) {
        String msg = getMsgByLevel(level);
        appendMessage(level, msg, null);
    }

    public static void appendMessage(String msg) {
        appendMessage(msg, null);
    }

    public static void appendMessage(String msg, int msgCode) {
        appendMessage(msgCode, msg, null);
    }

    private static void addMsg(HttpServletRequest request, List<MessageInfo> mif) {
        if (msgSaveType == 1) {
            if (messageList == null) {
                messageList = new ThreadLocal<List<MessageInfo>>();
            }
            messageList.set(mif);
            return;
        } else if (msgSaveType == 2) {
            if (request != null && request.getSession() != null) {
                request.getSession().setAttribute(SYS_INFO_KEY_IN_SESSION, mif);
            }
            return;
        }
        if (request == null) {
            if (messageList == null) {
                messageList = new ThreadLocal<List<MessageInfo>>();
            }
            messageList.set(mif);
        } else {
            if (request.getSession() != null) {
                request.getSession().setAttribute(SYS_INFO_KEY_IN_SESSION, mif);
            }
        }
    }

    /**
     * 取得系统的信息列表
     *
     * @return
     */
    public static List<MessageInfo> getMessageList() {
        return getMsg(getReq());
    }

    private static HttpServletRequest getReq() {
        HttpServletRequest request = null;
//        try {
//            request = GetRequestUtil.getReq();
//        } catch (Exception e) {
//            log.debug("添加系统信息失败,原因:request==null");
//        }
        return request;
    }

    @SuppressWarnings("unchecked")
    private static List<MessageInfo> getMsg(HttpServletRequest request) {
        List<MessageInfo> l = new ArrayList<MessageInfo>();
        if (messageList != null) {
            if (messageList.get() != null) {
                l.addAll(messageList.get());
            }
        }
        if (request != null && request.getSession() != null) {
            List<MessageInfo> list = null;
            list = (List<MessageInfo>) request.getSession().getAttribute(SYS_INFO_KEY_IN_SESSION);
            if (list != null) {
                l.addAll(list);
            }
        }
        return l.isEmpty() ? null : l;
    }

    /**
     * 清除系统信息
     */
    public static void cleanMessage() {
        if (getReq() != null && getReq().getSession() != null) {
            getReq().getSession().removeAttribute(SYS_INFO_KEY_IN_SESSION);
        }
        if (messageList != null) {
            messageList.remove();
        }
    }

    /**
     * 取得格式化后的消息并如需国际化处理并一起转化后返回。
     *
     * @param msg    格式化消息
     * @param params 格式化消息中需要的实际参数
     * @return 使用方法：getMessage("hello world {0}","seedoplatform");//hello world seedoplatform
     * getMessage("hello");//hello
     * getMessage("hello {0},{1}",new Object[]{"string",new Date()});//hello string,Wed Feb 22 11:56:33 CST 2012
     */
    public static String getMessage(String msg, Object... params) {
        if (params == null || params.length == 0 || msg == null || msg.trim().length() == 0) {
            return msg;
        }
        return MessageFormat.format(msg, params);
    }

    private static final String ERROR_TAG_CODE = "_CODE_";
    private static final String ERROR_TAG_MSG = "_MSG_";

    /**
     * 根据错误消息命名规则，通过消息代码获取消息内容
     *
     * @param level 错误消息代码
     * @return
     */
    private static String getMsgByLevel(int level) {
        String msgVal = "";
        Class<?> clz;
        try {
            clz = Class.forName("hotel.api.contants.error.ErrorMsgContants");
            Field[] fields = clz.getDeclaredFields();
            for (Field f : fields) {
                String fieldTyppe = f.getType().toString();
                if (!fieldTyppe.endsWith("int")) {
                    continue;
                }
                String fieldName = f.getName();
                int val = f.getInt("");
                if (level != val) {
                    continue;
                }
                String msgFieldName = fieldName.replace(ERROR_TAG_CODE, ERROR_TAG_MSG);
                Field msgField;
                msgField = clz.getField(msgFieldName);
                msgVal = (String) msgField.get("");
                return msgVal;
            }
        } catch (ClassNotFoundException e) {
            log.error("getMsgByLevel error", e);
        } catch (IllegalArgumentException e) {
            log.error("getMsgByLevel error", e);
        } catch (IllegalAccessException e) {
            log.error("getMsgByLevel error", e);
        } catch (NoSuchFieldException e) {
            log.error("getMsgByLevel error", e);
        } catch (SecurityException e) {
            log.error("getMsgByLevel error", e);
        }
        return msgVal;
    }

    public static void main(String[] args) {

        MessageInfo mi1 = new MessageInfo("a", 1, "ok", null);
        MessageInfo mi2 = new MessageInfo("a", 1, "ok", null);
        MessageInfo mi3 = new MessageInfo("a", 1, "ok", null);
        MessageInfo mi4 = new MessageInfo("a", 1, "ok", null);
        List<MessageInfo> l1 = new ArrayList<MessageInfo>();
        l1.add(mi4);
        l1.add(mi1);
        if (!l1.contains(mi2)) {
            l1.add(0, mi2);
        }
        System.out.println(l1.size());
    }

}
