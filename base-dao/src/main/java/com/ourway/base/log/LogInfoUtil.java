package com.ourway.base.log;

import com.ourway.base.dataobject.LogInfo;
import com.ourway.base.model.BaseEntity;
import com.ourway.base.utils.BeanUtil;

import java.util.*;

/**
 * <p>方法 LogInfoUtil : <p>
 * <p>说明:记录业务对表操作(CRUD)的信息
 * removexxx(),savexxx(),updatexxx()这些方法只在框架中自己调用，开发者如无特别需求,无需使用它。</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/7 0:22
 * </pre>
 */
public class LogInfoUtil {

    private static LogInfoUtil logInfoUtil;


    /**
     * <p>方法:getInstance 该工具类调用入口方法 </p>
     * <ul>
     * <li>@return LogInfoUtil  返回单例对象 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:23  </li>
     * </ul>
     */
    public static LogInfoUtil getInstance() {
        if (logInfoUtil == null) {
            logInfoUtil = new LogInfoUtil();
        }
        return logInfoUtil;
    }


    /**
     * <p>方法:getDeleteLogInfo  搜集删除时的日志信息，obj必须有Id属性且是唯一标识(单个obj记录) </p>
     * <ul>
     * <li> @param obj po对象</li>
     * <li>@return java.util.List<LogInfo>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:56  </li>
     * </ul>
     */
    public <T> LogInfo getDeleteLogInfo(T obj, Set<String> filterProperty) {
        List<LogInfo> l = new ArrayList<LogInfo>();
        LogInfo olif = processBaseLogInfo(obj);
        olif.setOpType(LogInfo.LOG_ACTION_TYPE_DEL);
        processBaseOldValLogInfo(obj,filterProperty,olif);
        return olif;
    }

    /**
     * <p>方法:getDeleteLogInfo 搜集删除时的日志信息，obj必须有Id属性且是唯一标识(批量obj记录) </p>
     * <ul>
     * <li> @param objs po对象集合</li>
     * <li>@return java.util.Map<java.lang.String,java.util.List<LogInfo>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 1:01  </li>
     * </ul>
     */
    public <T> List<LogInfo> getDeleteLogInfo(Collection<T> objs, Set<String> filterProperty) {

        List<LogInfo> result = new ArrayList<LogInfo>(objs.size());
        for (Object t : objs) {
            LogInfo l = getDeleteLogInfo(t, filterProperty);
            result.add(l);
        }
        return result;
    }


    /**
     * <p>方法:getSaveLogInfo 取得保存的日志信息 </p>
     * <ul>
     * <li> @param obj  po对象</li>
     * <li> @param filterProperty  需要记录的属性</li>
     * <li>@return java.util.List<LogInfo>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 1:02  </li>
     * </ul>
     */
    public <T> LogInfo getSaveLogInfo(T obj, Set<String> filterProperty) {
        LogInfo logInfo = processBaseLogInfo(obj);
        processBaseNewValLogInfo(obj,filterProperty,logInfo);
        logInfo.setOpType(LogInfo.LOG_ACTION_TYPE_ADD);
        return logInfo;
    }


    /**
     * <p>方法:getUpdateLogInfo 取得修改的日志信息 </p>
     * <ul>
     * <li> @param srcEntity  要修改源对象</li>
     * <li> @param destEntity 被修改的目标对象，即srcEntity所变动过的属性值覆盖此对象对应的属性值</li>
     * <li>@return java.util.List<LogInfo>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 1:06  </li>
     * </ul>
     */
    public <T> LogInfo getUpdateLogInfo(T srcEntity, T destEntity, Set<String> filterProperty) {
        LogInfo logInfo = processBaseLogInfo(destEntity);
        processBaseNewValLogInfo(destEntity,filterProperty,logInfo);
        processBaseOldValLogInfo(srcEntity,filterProperty,logInfo);
        logInfo.setOpType(LogInfo.LOG_ACTION_TYPE_UPDATE);
        return logInfo;
    }

    /**
     * <p>方法:processBaseLogInfo 设置基本的LogInfo属性名称 </p>
     * <ul>
     * <li> @param obj po</li>
     * <li>@return LogInfo  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:57  </li>
     * </ul>
     */
    private <T> LogInfo processBaseLogInfo(T obj) {
        LogInfo logInfo = new LogInfo();
        BeanUtil.setProperty(logInfo, "beanId", BeanUtil.getProperty(obj, BaseEntity.PO_ID));
        String clsName = BeanUtil.getRealClassFromHibernate(obj.getClass()).getName();
        logInfo.setBeanName(clsName);
        logInfo.setOperatorDate(new Date());

        return logInfo;
    }

    private <T> LogInfo processBaseOldValLogInfo(T obj, Set<String> filterProperty, LogInfo logInfo){
        if (null == logInfo.getOldValueMap())
            logInfo.setOldValueMap(new HashMap<String, Object>(filterProperty.size()));
        for (String s : filterProperty) {
            logInfo.getOldValueMap().put(s, BeanUtil.getProperty(obj, s));
        }
        return logInfo;
    }

    private <T> LogInfo processBaseNewValLogInfo(T obj, Set<String> filterProperty, LogInfo logInfo){
        if (null == logInfo.getNewValueMap())
            logInfo.setNewValueMap(new HashMap<String, Object>(filterProperty.size()));
        for (String s : filterProperty) {
            logInfo.getNewValueMap().put(s, BeanUtil.getProperty(obj, s));
        }
        return logInfo;
    }


}
