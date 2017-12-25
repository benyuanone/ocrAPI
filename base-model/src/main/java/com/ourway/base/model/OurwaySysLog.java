package com.ourway.base.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "OURWAY_SYS_LOG")
public class OurwaySysLog extends UUidEntity {
    private static final long serialVersionUID = 1L;
    public static final Integer NEW_FLAG = 1;
    public static final Integer UPDATE_FLAG = 2;
    public static final Integer REMOVE_FLAG = 3;

    @Column(name = "LOG_BUZ_FLAG", length = 64)
    private String logBuzFlag;
    @Column(name = "LOG_BUZ_REF_FLAG", length = 2400)
    private String logBuzRefFlag;
    @Column(name = "LOG_LABEL", length = 64)
    private String logLabel;
    @Column(name = "LOG_CLASS_NAME", length = 160)
    private String logClassName;
    @Column(name = "LOG_TYPE")
    private Integer logType;
    @Column(name = "LOG_OLD_VALUE", length = 2400)
    private String logOldValue;
    @Column(name = "LOG_NEW_VALUE", length = 2400)
    private String logNewValue;
    @Column(name = "LOG_OPERATE_ID", length = 64)
    private String logOperateId;
    @Column(name = "LOG_ORERATE_NAME", length = 64)
    private String logOperateName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_OPERATE_TIME")
    private Date logOperateTime;
    @Column(name = "MEMO", length = 240)
    private String memo;
    @Transient
    private Map<String, List<OurwaySysLog>> subLogs;

    public OurwaySysLog(Class claz, Integer operateFlag) {
        this.logClassName = claz.getName();
        this.logType = operateFlag;
    }

    public Map<String, List<OurwaySysLog>> getSubLogs() {
        return subLogs;
    }

    public void setSubLogs(Map<String, List<OurwaySysLog>> subLogs) {
        this.subLogs = subLogs;
    }

    public OurwaySysLog() {

    }

    public String getLogBuzRefFlag() {
        return logBuzRefFlag;
    }

    public void setLogBuzRefFlag(String logBuzRefFlag) {
        this.logBuzRefFlag = logBuzRefFlag;
    }

    public String getLogLabel() {
        return logLabel;
    }

    public void setLogLabel(String logLabel) {
        this.logLabel = logLabel;
    }

    public String getLogBuzFlag() {
        return logBuzFlag;
    }

    public void setLogBuzFlag(String logBuzFlag) {
        this.logBuzFlag = logBuzFlag;
    }

    public String getLogClassName() {
        return logClassName;
    }

    public void setLogClassName(String logClassName) {
        this.logClassName = logClassName;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogOldValue() {
        return logOldValue;
    }

    public void setLogOldValue(String logOldValue) {
        this.logOldValue = logOldValue;
    }

    public String getLogNewValue() {
        return logNewValue;
    }

    public void setLogNewValue(String logNewValue) {
        this.logNewValue = logNewValue;
    }

    public String getLogOperateId() {
        return logOperateId;
    }

    public void setLogOperateId(String logOperateId) {
        this.logOperateId = logOperateId;
    }

    public String getLogOperateName() {
        return logOperateName;
    }

    public void setLogOperateName(String logOperateName) {
        this.logOperateName = logOperateName;
    }

    public Date getLogOperateTime() {
        return logOperateTime;
    }

    public void setLogOperateTime(Date logOperateTime) {
        this.logOperateTime = logOperateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
