package com.ourway.base.dataobject;

import java.util.Date;
import java.util.Map;
import java.util.Set;


/**
 * <p>方法 LogInfo : <p>
 * <p>说明:业务日志记录信息,该类通过把类转为JSON字符串方法把该类的新增、删除或修改操作信息转换成,
 * 可读懂的业务操作日志信息。得到信息后可用BusinessLogService保存到数据库便于业务上的业务日志追踪。</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/7 0:33
 * </pre>
 */
public class LogInfo {

    public static final String LOG_ACTION_TYPE_ADD = "add";
    public static final String LOG_ACTION_TYPE_DEL = "delete";
    public static final String LOG_ACTION_TYPE_UPDATE = "update";

    /*操作类型*/
    private String opType;
    /*新的内容*/
    private Map<String, Object> newValueMap;
    /*旧的内容*/
    private Map<String, Object> oldValueMap;
    /*需要记录的属性*/
    private Set<String> filterProperty;
    /*操作时间*/
    private Date operatorDate;
    /*当前对象的主键*/
    private Object beanId;
    /*当前对象的类名*/
    private String beanName;

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Map<String, Object> getNewValueMap() {
        return newValueMap;
    }

    public void setNewValueMap(Map<String, Object> newValueMap) {
        this.newValueMap = newValueMap;
    }

    public Map<String, Object> getOldValueMap() {
        return oldValueMap;
    }

    public void setOldValueMap(Map<String, Object> oldValueMap) {
        this.oldValueMap = oldValueMap;
    }

    public Set<String> getFilterProperty() {
        return filterProperty;
    }

    public void setFilterProperty(Set<String> filterProperty) {
        this.filterProperty = filterProperty;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public Object getBeanId() {
        return beanId;
    }

    public void setBeanId(Object beanId) {
        this.beanId = beanId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
