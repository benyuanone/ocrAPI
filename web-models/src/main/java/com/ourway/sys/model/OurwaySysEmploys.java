package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_employs")
public class OurwaySysEmploys extends UUidEntity {

    @Column(name = "EMP_TYPE", nullable = true)
    private String empType;
    @Column(name = "EMP_ID", nullable = true, length = 16)
    private String empId;
    @Column(name = "EMP_PSW", nullable = true, length = 64)
    private String empPsw;
    @Column(name = "EMP_NAME", nullable = true, length = 16)
    private String empName;
    @Column(name = "EMP_CARD", nullable = true, length = 24)
    private String empCard;
    @Column(name = "EMP_ALIAIS", nullable = true, length = 24)
    private String empAliais;
    @Column(name = "EMP_JOB", nullable = true, length = 160)
    private String empJob;
    @Column(name = "EMP_LABEL", nullable = true, length = 240)
    private String empLabel;
    @Column(name = "EMP_PIC", nullable = true, length = 240)
    private String empPic;
    @Column(name = "EMP_POSITION", nullable = true)
    private Integer empPosition;
    @Column(name = "EMP_POSSITION_PATH", nullable = true, length = 240)
    private String empPossitionPath;
    @Column(name = "EMP_TEL", nullable = true, length = 24)
    private String empTel;
    @Column(name = "EMP_MOBILE1", nullable = true, length = 24)
    private String empMobile1;
    @Column(name = "EMP_MOBILE2", nullable = true, length = 24)
    private String empMobile2;
    @Column(name = "EMP_QQ", nullable = true, length = 64)
    private String empQq;
    @Column(name = "EMP_EMAIL", nullable = true, length = 240)
    private String empEmail;
    @Column(name = "EMP_WEIXIN", nullable = true, length = 240)
    private String empWeixin;
    @Column(name = "EMP_HOME_ADDR", nullable = true, length = 240)
    private String empHomeAddr;
    @Column(name = "EMP_HOME_TEL", nullable = true, length = 16)
    private String empHomeTel;
    @Column(name = "EMP_HOME_POSTCODE", nullable = true, length = 8)
    private String empHomePostcode;
    @Column(name = "EMP_OFFICE_NO", nullable = true, length = 16)
    private String empOfficeNo;
    @Column(name = "RMP_OFFICE", nullable = true, length = 16)
    private String rmpOffice;
    @Column(name = "EMP_DESC", nullable = true, length = 4000)
    private String empDesc;
    @Column(name = "EMP_STATUE", nullable = true)
    private Integer empStatue;
    @Column(name = "EMP_DEFAULT_DEPT", nullable = true)
    private Integer empDefaultDept;
    @Column(name = "EMP_DEFAULT_POSITION", nullable = true, length = 64)
    private String empDefaultPosition;
    @Column(name = "EMP_MOBILE_CODE", nullable = true, length = 20)
    private String empMobileCode;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EMP_MOBILE_TIME", nullable = true)
    private java.util.Date empMobileTime;
    @Column(name = "EMP_INDEX", nullable = true, length = 240)
    private String empIndex;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Transient
    private String sessionId;//当前用户的sessionid，从前端传入
    @Transient
    private List<OurwaySysDfilter> filterList;
    @Transient
    private List<OurwaySysPrivsuser> priUserList;
    @Transient
    private List<OurwaySysQuickmenu> quickMenuList;
    @Transient
    private String deptPositions="";

    public String getDeptPositions() {
        return deptPositions;
    }

    public void setDeptPositions(String deptPositions) {
        this.deptPositions = deptPositions;
    }

    public Integer getEmpDefaultDept() {
        return empDefaultDept;
    }

    public void setEmpDefaultDept(Integer empDefaultDept) {
        this.empDefaultDept = empDefaultDept;
    }

    public String getEmpDefaultPosition() {
        return empDefaultPosition;
    }

    public void setEmpDefaultPosition(String empDefaultPosition) {
        this.empDefaultPosition = empDefaultPosition;
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPsw() {
        return empPsw;
    }

    public void setEmpPsw(String empPsw) {
        this.empPsw = empPsw;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public String getEmpAliais() {
        return empAliais;
    }

    public void setEmpAliais(String empAliais) {
        this.empAliais = empAliais;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getEmpLabel() {
        return empLabel;
    }

    public void setEmpLabel(String empLabel) {
        this.empLabel = empLabel;
    }

    public String getEmpPic() {
        return empPic;
    }

    public void setEmpPic(String empPic) {
        this.empPic = empPic;
    }

    public Integer getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(Integer empPosition) {
        this.empPosition = empPosition;
    }

    public String getEmpPossitionPath() {
        return empPossitionPath;
    }

    public void setEmpPossitionPath(String empPossitionPath) {
        this.empPossitionPath = empPossitionPath;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public String getEmpMobile1() {
        return empMobile1;
    }

    public void setEmpMobile1(String empMobile1) {
        this.empMobile1 = empMobile1;
    }

    public String getEmpMobile2() {
        return empMobile2;
    }

    public void setEmpMobile2(String empMobile2) {
        this.empMobile2 = empMobile2;
    }

    public String getEmpQq() {
        return empQq;
    }

    public void setEmpQq(String empQq) {
        this.empQq = empQq;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpWeixin() {
        return empWeixin;
    }

    public void setEmpWeixin(String empWeixin) {
        this.empWeixin = empWeixin;
    }

    public String getEmpHomeAddr() {
        return empHomeAddr;
    }

    public void setEmpHomeAddr(String empHomeAddr) {
        this.empHomeAddr = empHomeAddr;
    }

    public String getEmpHomeTel() {
        return empHomeTel;
    }

    public void setEmpHomeTel(String empHomeTel) {
        this.empHomeTel = empHomeTel;
    }

    public String getEmpHomePostcode() {
        return empHomePostcode;
    }

    public void setEmpHomePostcode(String empHomePostcode) {
        this.empHomePostcode = empHomePostcode;
    }

    public String getEmpOfficeNo() {
        return empOfficeNo;
    }

    public void setEmpOfficeNo(String empOfficeNo) {
        this.empOfficeNo = empOfficeNo;
    }

    public String getRmpOffice() {
        return rmpOffice;
    }

    public void setRmpOffice(String rmpOffice) {
        this.rmpOffice = rmpOffice;
    }

    public String getEmpDesc() {
        return empDesc;
    }

    public void setEmpDesc(String empDesc) {
        this.empDesc = empDesc;
    }

    public Integer getEmpStatue() {
        return empStatue;
    }

    public void setEmpStatue(Integer empStatue) {
        this.empStatue = empStatue;
    }

    public String getEmpMobileCode() {
        return empMobileCode;
    }

    public void setEmpMobileCode(String empMobileCode) {
        this.empMobileCode = empMobileCode;
    }

    public Date getEmpMobileTime() {
        return empMobileTime;
    }

    public void setEmpMobileTime(Date empMobileTime) {
        this.empMobileTime = empMobileTime;
    }

    public String getEmpIndex() {
        return empIndex;
    }

    public void setEmpIndex(String empIndex) {
        this.empIndex = empIndex;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public List<OurwaySysDfilter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<OurwaySysDfilter> filterList) {
        this.filterList = filterList;
    }

    public List<OurwaySysPrivsuser> getPriUserList() {
        return priUserList;
    }

    public void setPriUserList(List<OurwaySysPrivsuser> priUserList) {
        this.priUserList = priUserList;
    }

    public List<OurwaySysQuickmenu> getQuickMenuList() {
        return quickMenuList;
    }

    public void setQuickMenuList(List<OurwaySysQuickmenu> quickMenuList) {
        this.quickMenuList = quickMenuList;
    }


}
