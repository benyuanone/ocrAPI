package com.ourway.base.model;

/**
 * <p>方法 PublicDataVO : <p>
 * <p>说明:通用数据传入对象</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/12 14:24
 * </pre>
 */
public class PublicDataVO implements java.io.Serializable {
    /*用户id*/
    private String openId; //可以是员工id

    private String currLanguage;//当前的语言
    /*6位随机数*/
    private String randNum;
    /*私钥和随机数MD5，32长度*/
    private String privateKey;

    /*当前应用的appkey*/
    private String appKey;
    /*业务路径*/
    private String method;
    /*当前前台的wxid*/
    private String wxid;
    /*当前客户端sessionid*/
    private String sessionId;
    /*接口版本号*/
    private String version;
    /*调用时间*/
    private String timestamp;
    /*扩展信息*/
    private String info;
    /*传递的json数据*/
    private String data;

    /*当前的页面号，从0开始*/
    private Integer pageNo = 0;
    /*每页显示的条数*/
    private Integer pageSize = 20;
    /*设备类型*/
    private String deviceType;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getCurrLanguage() {
        return currLanguage;
    }

    public void setCurrLanguage(String currLanguage) {
        this.currLanguage = currLanguage;
    }

    @Override
    public String toString() {
        return "PublicData{" +
                "openId='" + openId + '\'' +
                "currLanguage='" + currLanguage + '\'' +
                ", randNum='" + randNum + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", method='" + method + '\'' +
                ", wxid='" + wxid + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", version='" + version + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", info='" + info + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getRandNum() {
        return randNum;
    }

    public void setRandNum(String randNum) {
        this.randNum = randNum;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
