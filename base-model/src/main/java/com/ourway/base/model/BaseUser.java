package com.ourway.base.model;

import java.util.Date;

/**
 * <p>方法 BaseUser : <p>
 * <p>说明:用户基本类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/21 14:49
 * </pre>
 */
public class BaseUser implements java.io.Serializable {
    private String owid;
    private String empId;
    private String empName;
    private String empAliais;
    private String empPic;
    private String empTel;
    private String ipAddr;
    private java.util.Date inTime;
    private java.util.Date updateTime;

    /*0：游客  1 注册用户 */
    private Integer state;

    public String getOwid() {
        return owid;
    }

    public void setOwid(String owid) {
        this.owid = owid;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAliais() {
        return empAliais;
    }

    public void setEmpAliais(String empAliais) {
        this.empAliais = empAliais;
    }

    public String getEmpPic() {
        return empPic;
    }

    public void setEmpPic(String empPic) {
        this.empPic = empPic;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
