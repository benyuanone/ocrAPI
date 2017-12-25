package com.ourway.base.zk.models;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.utils.RandomUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;

/**
 * <p>方法 PublicData : <p>
 * <p>说明:Json公共传输类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 22:28
 * </pre>
 */
public class PublicData implements java.io.Serializable {
    /*用户id*/
    private String openId;
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
    /*当前sessionid*/
    private String sessionId;
    /*接口版本号*/
    private String version;
    /*调用时间*/
    private String timestamp;
    /*扩展信息*/
    private String info;
    /*传递的json数据*/
    private String data;
    private Integer pageNo;
    private Integer pageSize;
    /*传递数据的设备类型*/
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

    /**
     * <p>方法:instantce 获取publicdata数据 </p>
     * <ul>
     * <li>@return com.ourway.base.zk.models.PublicData  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/26 1:02  </li>
     * </ul>
     */
    public static PublicData instantce() {
        PublicData pd = new PublicData();
        pd.setAppKey(BaseConstants.APP_KEY);
//        6位随机码
        pd.setRandNum(RandomUtil.getRandomCharacter(6));
        pd.setPrivateKey(TextUtils.MD5(BaseConstants.APP_SECTRECT + pd.getRandNum()));
        pd.setTimestamp(System.currentTimeMillis() + "");
        pd.setSessionId(ZKSessionUtils.getSessionId());
        pd.setDeviceType("zk");
        if(null!=ZKSessionUtils.getZkUser()){
            EmployVO employVO = ZKSessionUtils.getZkUser();
            pd.setOpenId(employVO.getEmpId());
            pd.setCurrLanguage(employVO.getLanguage());
        }
        return pd;
    }
}
