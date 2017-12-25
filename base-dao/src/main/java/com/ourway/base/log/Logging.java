package com.ourway.base.log;

import com.ourway.base.config.ConfigInfotor;
import com.ourway.base.utils.StringUtil;
import com.ourway.base.config.ConfigFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>方法 Logging : <p>
 * <p>说明:装common.logging类,当开发人员使用该类时就无须再对该平台中的log4j.properties文件做修改（建议），
 * 但如果有需求的话，对于log4j.properties中的LogLevel禁止修改，如果要修改它请对该平台提供的API ConfigInfotor.class
 * 调用putProperty(Logging.LOG_LEVEL_KEY_NAME,Logging.LOG_LEVEL_xxx)方法或该平台提供的管理界面功能进行动态修改。</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/7 0:09
 * </pre>
 */
public class Logging implements java.io.Serializable {

    private Log log;
    private ConfigInfotor ci;
    private Map<String, Integer> logLevelMap = new HashMap<String, Integer>();

    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_ERROR = "ERROR";
    public static final String LOG_LEVEL_FATAL = "FATAL";
    public static final String LOG_LEVEL_KEY_NAME = "log.logLevel";

    private static final int LOG_LEVEL_VALUE_DEBUG = 0;
    private static final int LOG_LEVEL_VALUE_INFO = 1;
    private static final int LOG_LEVEL_VALUE_ERROR = 2;
    private static final int LOG_LEVEL_VALUE_FATAL = 3;

    /**
     * <p>方法:Logging 构造器 </p>
     * <ul>
     * <li> @param clz 日志类</li>
     * <li>@return   </li>
     * <li>@author JackZhou </li>
     * <li>@date    </li>
     * </ul>
     */
    public Logging(Class<?> clz) {
//		PropertyConfigurator.configure("");//
        log = LogFactory.getLog(clz);
        logLevelMap.put(LOG_LEVEL_DEBUG, LOG_LEVEL_VALUE_DEBUG);
        logLevelMap.put(LOG_LEVEL_INFO, LOG_LEVEL_VALUE_INFO);
        logLevelMap.put(LOG_LEVEL_ERROR, LOG_LEVEL_VALUE_ERROR);
        logLevelMap.put(LOG_LEVEL_FATAL, LOG_LEVEL_VALUE_FATAL);
    }


    /**
     * <p>方法:debug debug信息输出 </p>
     * <ul>
     * <li> @param info 异常对象</li>
     * <li> @param e 处理类</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:11  </li>
     * </ul>
     */
    public void debug(Object info, Throwable e) {
        initCi();
        String logLevel = ci.getProperty(LOG_LEVEL_KEY_NAME);
        Integer level = logLevelMap.get(StringUtil.trimST(logLevel).toUpperCase());
        if (level != null
                && level <= LOG_LEVEL_VALUE_DEBUG) {
            log.debug(info, e);
        }
    }

    /**
     * <p>方法:initCi 获取系统当前配置，具体配置在系统的config.propeties </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:14  </li>
     * </ul>
     */
    private void initCi() {
        if (ci != null) {
            return;
        }
        ci = ConfigFactory.getDefaultProvider();
    }


    /**
     * <p>方法:debug debug信息输出 </p>
     * <ul>
     * <li> @param info 信息对象</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:16  </li>
     * </ul>
     */
    public void debug(Object info) {
        debug(info, null);
    }


    /**
     * <p>方法:info  </p>
     * <ul>
     * <li> @param info </li>
     * <li> @param e </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:17  </li>
     * </ul>
     */
    public void info(Object info, Throwable e) {
        initCi();
        String logLevel = ci.getProperty(LOG_LEVEL_KEY_NAME);
        Integer level = logLevelMap.get(StringUtil.trimST(logLevel).toUpperCase());
        if (level != null
                && level <= LOG_LEVEL_VALUE_INFO) {
            log.info(info, e);
        }
    }


    /**
     * <p>方法:info  </p>
     * <ul>
     * <li> @param info </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:17  </li>
     * </ul>
     */
    public void info(Object info) {
        info(info, null);
    }

    /**
     * <p>方法:error  </p>
     * <ul>
     * <li> @param info </li>
     * <li> @param e </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:17  </li>
     * </ul>
     */
    public void error(Object info, Throwable e) {
        initCi();
        String logLevel = ci.getProperty(LOG_LEVEL_KEY_NAME);
        Integer level = logLevelMap.get(StringUtil.trimST(logLevel).toUpperCase());
        if (level != null
                && level <= LOG_LEVEL_VALUE_ERROR) {
            log.error(info, e);
        }
    }

    /**
     * <p>方法:error  </p>
     * <ul>
     * <li> @param info </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:17  </li>
     * </ul>
     */
    public void error(Object info) {
        error(info, null);
    }

    /**
     * <p>方法:fatal  </p>
     * <ul>
     * <li> @param info </li>
     * <li> @param e </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:17  </li>
     * </ul>
     */
    public void fatal(Object info, Throwable e) {
        initCi();
        String logLevel = ci.getProperty(LOG_LEVEL_KEY_NAME);
        Integer level = logLevelMap.get(StringUtil.trimST(logLevel).toUpperCase());
        if (level != null
                && level <= LOG_LEVEL_VALUE_FATAL) {
            log.fatal(info, e);
        }
    }

    /**
     * <p>方法:fatal  </p>
     * <ul>
     * <li> @param info </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:18  </li>
     * </ul>
     */
    public void fatal(Object info) {
        fatal(info, null);
    }

}
