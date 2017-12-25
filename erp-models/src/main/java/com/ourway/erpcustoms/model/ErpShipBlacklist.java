package com.ourway.erpcustoms.model;

import com.ourway.base.model.UUidEntity;
import com.ourway.erpbasedata.model.ErpShip;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/*<p>方法 ErpShipBlacklist : <p>
*<p>说明: 船舶黑名单实体类</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-07 16:05
</pre>
*/
@Entity
@Table(name = "erp_ship_blacklist")
public class ErpShipBlacklist extends UUidEntity{
    @Basic
    @Column(name = "ERPSHIP_REF_OWID", nullable = false, length = 32)
    private String erpshipRefOwid;
    @Basic
    @Column(name = "EVENT", nullable = true, length = 1024)
    private String event;
    @Basic
    @Column(name = "DEAL", nullable = true, length = 1024)
    private String deal;
    @Basic
    @Column(name = "REVOKE_REASON", nullable = true)
    private String revokeReason;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 256)
    private String remark;
    @Transient
    //黑名单次数统计
    private Integer blacklistCount = 0;
    @Transient
    //关联船舶资料表
    private ErpShip erpShip;
    //state判断
    public static final int ERPSHIP_BLACKLIST_USED_STATE = 0;
    public static final int ERPSHIP_BLACKLIST_REVOKE_STATE = 1;

    public String getErpshipRefOwid() {
        return erpshipRefOwid;
    }

    public void setErpshipRefOwid(String erpshipRefOwid) {
        this.erpshipRefOwid = erpshipRefOwid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBlacklistCount() {
        return blacklistCount;
    }

    public void setBlacklistCount(Integer blacklistCount) {
        this.blacklistCount = blacklistCount;
    }

    public ErpShip getErpShip() {
        return erpShip;
    }

    public void setErpShip(ErpShip erpShip) {
        this.erpShip = erpShip;
    }
}
