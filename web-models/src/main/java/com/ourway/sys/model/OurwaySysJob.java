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
@Table(name = "ourway_sys_job")
public class OurwaySysJob extends UUidEntity {
    @Column(name = "JOB_NAME", nullable = true, length = 240)
    private String jobName;
    @Column(name = "JOB_DESC", nullable = true, length = 240)
    private String jobDesc;
    @Column(name = "JOB_CLASS", nullable = true, length = 240)
    private String jobClass;
    @Column(name = "JOB_TYPE", nullable = true)
    private Byte jobType;
    @Column(name = "JOB_CRON", nullable = true, length = 240)
    private String jobCron;

    @Transient
    private Integer updateFlag;

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public Byte getJobType() {
        return jobType;
    }

    public void setJobType(Byte jobType) {
        this.jobType = jobType;
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }
}
