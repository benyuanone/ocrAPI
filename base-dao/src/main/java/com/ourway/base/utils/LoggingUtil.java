package com.ourway.base.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Seedo platform
 * 版权所有请勿擅自修改，如有需要请联系作者email:wuzhisong2004@163.com
 * 版本：v1.0.0
 * 日期：2012-2-21
 *
 * @author wuzhisong
 *         功能说明：包装common.logging类，主要在要对应每个方法里加上isXXXEnabled()开关,并带有格式化参数。
 *         使用说明：强烈建议有如下情形者调用相对应的方法。
 *         例子：log.debug("找不到文件名称为{0}",fileName);//当消息含有与变量结合显示的应当用jdk format方式
 *         不建议用log.debug("找不到文件名称为"+fileName);这样开方式使用，因为该工具类支持自定义国际化
 *         实现并做性能优化。
 */
public class LoggingUtil {

    private static Log log = null;
    private static LoggingUtil logUtil;

    /**
     * 构造函数
     *
     * @param logClz 发生在日志所在的类
     */
    private LoggingUtil(Class<?> logClz) {
        log = LogFactory.getLog(logClz);
    }

    /**
     * 取得LoggingUtil对象（单例形式）
     *
     * @param logClz 发生在日志所在的类
     * @return
     */
    public static LoggingUtil getInstance(Class<?> logClz) {
        if (logUtil == null) {
            logUtil = new LoggingUtil(logClz);
        }
        return logUtil;
    }

    public static LoggingUtil getInstance() {
        Throwable t = new Throwable();
        StackTraceElement[] ste = t.getStackTrace();
        try {
            Class<?> c = null;
            if (ste.length < 2) {
                /*
				 * 说明自己调用自己
				 */
                c = Class.forName(ste[0].getClassName());
            } else {
				/*
				 * 上一级调用者
				 */
                c = Class.forName(ste[1].getClassName());
            }
            return getInstance(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String fmt(String message, Object... params) {
        String retu = message;
		if(params!=null)		{
			retu=MessageUtil.getMessage(message,params);
		}
        return retu;
    }

    /**
     * debug记录
     *
     * @param info
     * @param e
     */
    public void debug(Throwable e, String info, Object... params) {
        if (log.isDebugEnabled()) {
            log.debug(fmt(info, params), e);
        }
    }

    /**
     * debug记录
     *
     * @param info
     */
    public void debug(String info, Object... params) {
        debug(null, info, params);
    }

    /**
     * info记录
     *
     * @param info
     * @param e
     */
    public void info(Throwable e, String info, Object... params) {
        if (log.isInfoEnabled()) {
            log.info(fmt(info, params), e);
        }
    }

    /**
     * info记录
     *
     * @param info
     */
    public void info(String info, Object... params) {
        info(null, info, params);
    }

    /**
     * error记录
     *
     * @param info
     * @param e
     */
    public void error(Throwable e, String info, Object... params) {
        if (log.isErrorEnabled()) {
            log.error(fmt(info, params), e);
        }
    }

    /**
     * error记录
     *
     * @param info
     */
    public void error(String info, Object... params) {
        error(null, info, params);
    }

    /**
     * fatal记录
     *
     * @param info
     * @param e
     */
    public void fatal(Throwable e, String info, Object... params) {
        if (log.isFatalEnabled()) {
            log.fatal(fmt(info, params), e);
        }
    }

    /**
     * fatal记录
     *
     * @param info
     */
    public void fatal(String info, Object... params) {
        fatal(null, info, params);
    }

}
