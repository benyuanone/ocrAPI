package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Kevin on 2017-09-11.
 */
@Entity
@Table(name = "erp_pipe")
public class ErpPipe extends UUidEntity {
    //树相关
    @Transient
    private Integer treeId1 = 1;

    public Integer getTreeId1() {
        return treeId1;
    }

    public void setTreeId1(Integer treeId1) {
        this.treeId1 = treeId1;
    }
    @Transient
    private  Integer treeId2 = -1;

    public Integer getTreeId2() {
        return treeId2;
    }

    public void setTreeId2(Integer treeId2) {
        this.treeId2 = treeId2;
    }

    @Transient
    private double literPerM;

    public double getLiterPerM() {
        return literPerM;
    }

    public void setLiterPerM(double literPerM) {
        this.literPerM = literPerM;
    }

    @Transient
    private double capacity;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "PIPE_ID", nullable = false, length = 16)
    private String pipeId;
    public String getPipeId() {
        return pipeId;
    }

    public void setPipeId(String pipeId) {
        this.pipeId = pipeId;
    }

    @Basic
    @Column(name = "PIPE_NAME", nullable = false, length = 256)
    private String pipeName;
    public String getPipeName() {
        return pipeName;
    }

    public void setPipeName(String pipeName) {
        this.pipeName = pipeName;
    }

    @Basic
    @Column(name = "DIAMETER", nullable = true, precision = 6)
    private BigDecimal diameter;
    public BigDecimal getDiameter() {
        return diameter;
    }

    public void setDiameter(BigDecimal diameter) {
        this.diameter = diameter;
    }

    @Basic
    @Column(name = "STANDARD1", nullable = true, precision = 6)
    private BigDecimal standard1;
    public BigDecimal getStandard1() {
        return standard1;
    }

    public void setStandard1(BigDecimal standard1) {
        this.standard1 = standard1;
    }

    @Basic
    @Column(name = "STANDARD2", nullable = true, precision = 6)
    private BigDecimal standard2;
    public BigDecimal getStandard2() {
        return standard2;
    }

    public void setStandard2(BigDecimal standard2) {
        this.standard2 = standard2;
    }

    @Basic
    @Column(name = "MATERIAL", nullable = true)
    private Integer material;
    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    @Basic
    @Column(name = "PIPE_FROM", nullable = true, length = 256)
    private String pipeFrom;
    public String getPipeFrom() {
        return pipeFrom;
    }

    public void setPipeFrom(String pipeFrom) {
        this.pipeFrom = pipeFrom;
    }

    @Basic
    @Column(name = "PIPE_TO", nullable = true, length = 256)

    private String pipeTo;
    public String getPipeTo() {
        return pipeTo;
    }

    public void setPipeTo(String pipeTo) {
        this.pipeTo = pipeTo;
    }

    @Basic
    @Column(name = "LENGTH", nullable = true, precision = 6)
    private BigDecimal length;
    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    @Basic
    @Column(name = "TYPE", nullable = false)
    private Integer type;
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "PROPERTY", nullable = false)
    private Integer property;
    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "NEXT_CHECK_DATE", nullable = true)
    private Date nextCheckDate;
    public Date getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Transient
    private Integer updateFlag;

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }
}
