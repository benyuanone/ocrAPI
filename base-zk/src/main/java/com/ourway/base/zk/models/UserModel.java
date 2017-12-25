package com.ourway.base.zk.models;


import java.util.Date;

/**
 * <p>方法 UserModel : <p>
 * <p>说明:zk前段的用户信息</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/26 0:46
 * </pre>
 */
public class UserModel implements java.io.Serializable {
    private String sessionId;
    private String empNo;
    private String empName;
    private java.util.Date lastTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
