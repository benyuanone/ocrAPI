package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_message")
public class OurwaySysMessage extends UUidEntity {

    @Column(name = "MESS_PER_ID", nullable = true, length = 64)
    private String messPerId;
    @Column(name = "MESS_PER_NAME", nullable = true, length = 20)
    private String messPerName;
    @Column(name = "MESS_TYPE", nullable = true)
    private Integer messType;
    @Column(name = "MESS_TYPE_PATH", nullable = true, length = 240)
    private String messTypePath;
    @Column(name = "MESS_TYPE_NAME", nullable = true, length = 240)
    private String messTypeName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MESS_TIME", nullable = true)
    private Date messTime;
    @Column(name = "MESS_ALERT", nullable = true)
    private Byte messAlert;
    @Column(name = "MESS_TITLE", nullable = true, length = 800)
    private String messTitle;
    @Column(name = "MESS_CONTENT", nullable = true, length = 1600)
    private String messContent;
    @Column(name = "MESS_STATUS", nullable = true)
    private Byte messStatus;
    @Column(name = "MESS_COUNT", nullable = true)
    private Integer messCount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MESS_OPENTIME", nullable = true)
    private Date messOpentime;
    @Column(name = "MESS_SEND_ID", nullable = true, length = 64)
    private String messSendId;
    @Column(name = "MESS_SEND_NAME", nullable = true, length = 20)
    private String messSendName;
    @Column(name = "MESS_ATTACHS", nullable = true, length = 240)
    private String messAttachs;
    @Column(name = "MESS_DELIVER", nullable = true)
    private Byte messDeliver;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MESS_DELIVER_TIME", nullable = true)
    private Date messDeliverTime;
    @Column(name = "MESS_DELIVER_DEVICE", nullable = true, length = 64)
    private String messDeliverDevice;
    @Column(name = "MESS_BIZ_ID", nullable = true, length = 64)
    private String messBizId;
    @Column(name = "MESS_BIZ_URL", nullable = true, length = 240)
    private String messBizUrl;
    @Column(name = "MEMO", nullable = true, length = 800)
    private String memo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MESS_NEXT_TIME", nullable = true)
    private Date messNextTime;

    public String getMessPerId() {
        return messPerId;
    }

    public void setMessPerId(String messPerId) {
        this.messPerId = messPerId;
    }

    public String getMessPerName() {
        return messPerName;
    }

    public void setMessPerName(String messPerName) {
        this.messPerName = messPerName;
    }

    public Integer getMessType() {
        return messType;
    }

    public void setMessType(Integer messType) {
        this.messType = messType;
    }

    public String getMessTypePath() {
        return messTypePath;
    }

    public void setMessTypePath(String messTypePath) {
        this.messTypePath = messTypePath;
    }

    public String getMessTypeName() {
        return messTypeName;
    }

    public void setMessTypeName(String messTypeName) {
        this.messTypeName = messTypeName;
    }

    public Date getMessTime() {
        return messTime;
    }

    public void setMessTime(Date messTime) {
        this.messTime = messTime;
    }

    public Byte getMessAlert() {
        return messAlert;
    }

    public void setMessAlert(Byte messAlert) {
        this.messAlert = messAlert;
    }

    public String getMessTitle() {
        return messTitle;
    }

    public void setMessTitle(String messTitle) {
        this.messTitle = messTitle;
    }

    public String getMessContent() {
        return messContent;
    }

    public void setMessContent(String messContent) {
        this.messContent = messContent;
    }

    public Byte getMessStatus() {
        return messStatus;
    }

    public void setMessStatus(Byte messStatus) {
        this.messStatus = messStatus;
    }

    public Integer getMessCount() {
        return messCount;
    }

    public void setMessCount(Integer messCount) {
        this.messCount = messCount;
    }

    public Date getMessOpentime() {
        return messOpentime;
    }

    public void setMessOpentime(Date messOpentime) {
        this.messOpentime = messOpentime;
    }

    public String getMessSendId() {
        return messSendId;
    }

    public void setMessSendId(String messSendId) {
        this.messSendId = messSendId;
    }

    public String getMessSendName() {
        return messSendName;
    }

    public void setMessSendName(String messSendName) {
        this.messSendName = messSendName;
    }

    public String getMessAttachs() {
        return messAttachs;
    }

    public void setMessAttachs(String messAttachs) {
        this.messAttachs = messAttachs;
    }

    public Byte getMessDeliver() {
        return messDeliver;
    }

    public void setMessDeliver(Byte messDeliver) {
        this.messDeliver = messDeliver;
    }

    public Date getMessDeliverTime() {
        return messDeliverTime;
    }

    public void setMessDeliverTime(Date messDeliverTime) {
        this.messDeliverTime = messDeliverTime;
    }

    public String getMessDeliverDevice() {
        return messDeliverDevice;
    }

    public void setMessDeliverDevice(String messDeliverDevice) {
        this.messDeliverDevice = messDeliverDevice;
    }

    public String getMessBizId() {
        return messBizId;
    }

    public void setMessBizId(String messBizId) {
        this.messBizId = messBizId;
    }

    public String getMessBizUrl() {
        return messBizUrl;
    }

    public void setMessBizUrl(String messBizUrl) {
        this.messBizUrl = messBizUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getMessNextTime() {
        return messNextTime;
    }

    public void setMessNextTime(Date messNextTime) {
        this.messNextTime = messNextTime;
    }
}
