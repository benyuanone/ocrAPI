package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_log_setting_attribute")
public class OurwaySysLogSettingAttribute extends UUidEntity {

    @Column(name = "ATTRIBUTE_REF_OWID", nullable = true, length = 64)
    private String attributeRefOwid;
    @Column(name = "LOG_REF_OWID", nullable = true, length = 64)
    private String logRefOwid;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;

    public String getAttributeRefOwid() {
        return attributeRefOwid;
    }

    public void setAttributeRefOwid(String attributeRefOwid) {
        this.attributeRefOwid = attributeRefOwid;
    }


    public String getLogRefOwid() {
        return logRefOwid;
    }

    public void setLogRefOwid(String logRefOwid) {
        this.logRefOwid = logRefOwid;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
