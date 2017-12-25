package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_dic_value")
public class OurwaySysDicValue extends UUidEntity {

    @Column(name = "DIC_REF_OWID", nullable = true)
    private Integer dicRefOwid;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Column(name = "DIC_VAL1", nullable = true, length = 160)
    private String dicVal1;
    @Column(name = "DIC_VAL2", nullable = true, length = 160)
    private String dicVal2;
    @Column(name = "DIC_VAL3", nullable = true, length = 160)
    private String dicVal3;
    @Column(name = "DIC_VAL4", nullable = true, length = 160)
    private String dicVal4;
    @Column(name = "DIC_VAL5", nullable = true, length = 160)
    private String dicVal5;
    @Column(name = "DIC_VAL6", nullable = true, length = 160)
    private String dicVal6;
    @Column(name = "DIC_VAL7", nullable = true, length = 160)
    private String dicVal7;
    @Column(name = "DIC_VAL8", nullable = true, length = 160)
    private String dicVal8;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;

    @Transient
    private Integer updateFlag;

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Integer getDicRefOwid() {
        return dicRefOwid;
    }

    public void setDicRefOwid(Integer dicRefOwid) {
        this.dicRefOwid = dicRefOwid;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getDicVal1() {
        return dicVal1;
    }

    public void setDicVal1(String dicVal1) {
        this.dicVal1 = dicVal1;
    }


    public String getDicVal2() {
        return dicVal2;
    }

    public void setDicVal2(String dicVal2) {
        this.dicVal2 = dicVal2;
    }


    public String getDicVal3() {
        return dicVal3;
    }

    public void setDicVal3(String dicVal3) {
        this.dicVal3 = dicVal3;
    }


    public String getDicVal4() {
        return dicVal4;
    }

    public void setDicVal4(String dicVal4) {
        this.dicVal4 = dicVal4;
    }


    public String getDicVal5() {
        return dicVal5;
    }

    public void setDicVal5(String dicVal5) {
        this.dicVal5 = dicVal5;
    }


    public String getDicVal6() {
        return dicVal6;
    }

    public void setDicVal6(String dicVal6) {
        this.dicVal6 = dicVal6;
    }


    public String getDicVal7() {
        return dicVal7;
    }

    public void setDicVal7(String dicVal7) {
        this.dicVal7 = dicVal7;
    }


    public String getDicVal8() {
        return dicVal8;
    }

    public void setDicVal8(String dicVal8) {
        this.dicVal8 = dicVal8;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
