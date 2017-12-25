package com.ourway.base.websocket;

/**
 * Created by Administrator on 2017/10/19.
 */
public class BaseSocketMess {

    /*发送消息给目标的人，0 表示给服务器 1:给网页 */
    private String targetId;
    /*消息类型 0：登陆消息 1：界面返回 2.后台发送给前台 */
    private Integer msgType;
    private String msgTitle;//消息标题，支持多语言
    /*推送的消息*/
    private String msgBody;//消息内容体，数据部分，不支持多语言
    /*数据类型 0:字符串 1：二维码 2 html*/
    private Integer dataType;
    private String msgUrl;
    private String msgBizId;
    private String msgIcon;
    private String empId;
    private String fromEmpId;

    public String getMsgBizId() {
        return msgBizId;
    }

    public void setMsgBizId(String msgBizId) {
        this.msgBizId = msgBizId;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getFromEmpId() {
        return fromEmpId;
    }

    public void setFromEmpId(String fromEmpId) {
        this.fromEmpId = fromEmpId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public String getMsgIcon() {
        return msgIcon;
    }

    public void setMsgIcon(String msgIcon) {
        this.msgIcon = msgIcon;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public static BaseSocketMess txtInstance(String empId, String msgBody, String fromEmpId,String msgTitle,String msgBizId,String msgUrl) {
        BaseSocketMess mess = new BaseSocketMess();
        mess.setEmpId(empId);
        mess.setMsgBody(msgBody);
        mess.setFromEmpId(fromEmpId);
        mess.setDataType(0);
        mess.setMsgType(2);
        mess.setTargetId("1");
        mess.setMsgTitle(msgTitle);
        mess.setMsgBizId(msgBizId);
        mess.setMsgUrl(msgUrl);
        return mess;
    }
}
