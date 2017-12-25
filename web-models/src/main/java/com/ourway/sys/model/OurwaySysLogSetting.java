package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_log_setting")
public class OurwaySysLogSetting extends UUidEntity {

    @Column(name = "OBJECT_REF_OWID", nullable = false, length = 64)
    private String objectRefOwid;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Transient
    private String className;
    @Transient
    private List<OurwaySysLogSettingAttribute> dataList;

    public List<OurwaySysLogSettingAttribute> getDataList() {
        return dataList;
    }

    public void setDataList(List<OurwaySysLogSettingAttribute> dataList) {
        this.dataList = dataList;
    }

    public String getObjectRefOwid() {
        return objectRefOwid;
    }

    public void setObjectRefOwid(String objectRefOwid) {
        this.objectRefOwid = objectRefOwid;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
