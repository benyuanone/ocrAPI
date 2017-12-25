package com.ourway.base.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


/**
 * <p>方法 AbstractAuditLogEntity : <p>
 * <p>说明:所有的表都需要继承这个类，这个类保存了所有类的用户操作日志。</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/11 16:21
 * </pre>
 */
@MappedSuperclass
public abstract class AbstractAuditLogEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /*
     * 重要提示：如各pojo子类用annotation时请与该抽象类对应到filed级别，如pojo子类的
	 * annotation是注释（hibernate tools生成在方法级别上）请与该类使用同在field级别上
	 * 否则会出错
	 */
    @Column(name = "CREATOR", length = 36, updatable = false)
    private String creator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME", updatable = false)
    private Date createtime;

    @Column(name = "CREATOR_NAME", length = 100)
    private String creatorName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATE")
    private Date lasupdate;

    @Column(name = "UPDATOR", length = 36)
    private String updator;

    @Column(name = "UPDATOR_NAME", length = 100)
    private String updatorName;

    @Column(name = "DELFLG")
    private Integer delflg;// 0 正常 -1删除 -100 临时存

    @Column(name = "STATE")
    private Integer state;//-1 :作废 0：正常可修改  100：工作流完成 1-99是流程

    @Column(name = "DEPT_ID")
    private Integer deptId;

    @Column(name = "DEPT_PATH", length = 100)
    private String deptPath;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getLasupdate() {
        return lasupdate;
    }

    public void setLasupdate(Date lasupdate) {
        this.lasupdate = lasupdate;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }
}
