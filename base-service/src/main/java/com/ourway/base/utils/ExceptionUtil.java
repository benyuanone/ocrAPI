package com.ourway.base.utils;


import com.ourway.base.CommonConstants;
import com.ourway.base.config.ConfigFactory;
import com.ourway.base.message.MsgI18NManager;

import java.util.Date;

/**
 * 功能说明：异常处理工具类，开发人员处理异常时强烈建议都使用此工具类，不要使用throw new PlatformExcept()方式。
 */

/**
 * <p>方法 ExceptionUtil : <p>
 * <p>说明:异常处理工具类，开发人员处理异常时强烈建议都使用此工具类，不要使用throw new Exception()方式</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/13 18:05
 * </pre>
 */
public class ExceptionUtil {

    /*目的在管理界面上实时控制日志开关*/
    private static ThreadLocal<Boolean> swithcLogLocal = new ThreadLocal<Boolean>();
    private static LoggingUtil log;
    private static Class<?> logClass;

    /**
     * 设置调用此类的主类名称，目的是让日志记录的文件体现出错的类名是哪一个。
     * 使用方法：
     */
    private static void setProcessLogProgram() {
        try {
            Throwable t = new Throwable();
            StackTraceElement[] ste = t.getStackTrace();
            logClass = Class.forName(ste[4].getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getProcessProgram(){
        String result = "";
        try {
            Throwable t = new Throwable();
            StackTraceElement[] ste = t.getStackTrace();
            result = ste[3].getClassName()+":"+ste[3].getMethodName();
//            System.out.println(":"+ste[3].getClassName()+":"+ste[4].getMethodName()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初始化日志实例
     * 使用方法：
     */
    private static void initLogginUtil() {
        if (log == null) {
            setProcessLogProgram();
        }
        log = LoggingUtil.getInstance(logClass);
        String open = ConfigFactory.getDefaultProvider().getProperty(CommonConstants.EXCEPTION_UTIL_SWITCH_LOG_OPEN);
        Boolean isOpen = new Boolean(open);
        swithcLogLocal.set(isOpen);
        if (swithcLogLocal.get() == null) {
            swithcLogLocal.set(true);
        }
    }

    /**
     * 抛出异常
     *
     * @param e       Throwable对象
     * @param message 错误信息
     * @param args    错误信息中的格式参数，遵照java.text.format标准
     */
    /**
     * <p>方法:throwException 抛出异常 </p>
     * <ul>
     * <li> @param e Throwable对象</li>
     * <li> @param message 错误信息</li>
     * <li> @param args 错误信息中的格式参数，遵照java.text.format标准</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/13 18:07  </li>
     * </ul>
     */
    public static void throwException(Throwable e, String message, Object... args) {
        initLogginUtil();
        message = MsgI18NManager.getProvider().getMsg(message, SessionUtils.getCurrLanguage());
        message ="\n出错类及方法："+ getProcessProgram()+"\n出错信息："+message;
        if (e != null && message != null && args != null) {
            if (swithcLogLocal.get()) {
                log.error(e, message, args);
            }
        } else if (e != null && message != null && args == null) {
            if (swithcLogLocal.get()) {
                log.error(e, message);
            }
        } else if (e == null && message != null && args == null) {
            if (swithcLogLocal.get()) {
                log.error(message);
            }
        } else if (e == null && message != null && args != null) {
            if (swithcLogLocal.get()) {
                log.error(message, args);
            }
        } else {
            if (swithcLogLocal.get()) {
                log.error(e, message, args);
            }
        }
    }

    /**
     * throw exception
     *
     * @param e
     * @param message
     */
    public static void throwException(Throwable e, String message) {
        throwException(e, message, new Object[]{});
    }

    /**
     * throw exception
     *
     * @param message
     * @param args
     */
    public static void throwException(String message, Object... args) {
        throwException(null, message, args);
    }

    /**
     * 打开或关闭Log日志记录，默认打开。
     *
     * @param isOpen true为打开，false为关闭
     *               使用方法：
     */
    public static void switchLog(boolean isOpen) {
        swithcLogLocal.set(isOpen);
    }

    public static void main(String[] args) {
        try {
            throwException("jjjjj={0}", new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        switchLog(false);
        throwException("rrrrr={0}", new Date());
        String b = ConfigFactory.getDefaultProvider().getProperty(CommonConstants.EXCEPTION_UTIL_SWITCH_LOG_OPEN);

        System.out.println("====" + b);
    }

}
