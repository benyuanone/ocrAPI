package com.ourway.base.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**<p>方法 AbstractVerEntity : <p>
*<p>说明:所有表的版本控制，包括版本值和时间</p>
*<pre>
*@author JackZhou
*@date 2017/3/11 16:22
</pre>
*/
@MappedSuperclass
public abstract class AbstractVerEntity extends AbstractAuditLogEntity {
    private static final long serialVersionUID = 1L;
    /*
	 * 重要提示：如各pojo子类用annotation时请与该抽象类对应到filed级别，如pojo子类的
	 * annotation是注释（hibernate tools生成在方法级别上）请与该类使用同在field级别上
	 * 否则会出错
	 */
    @Column(name="VER")
    private Integer  ver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERTIME", updatable = false)
    private Date vertime;

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Date getVertime() {
        return vertime;
    }

    public void setVertime(Date vertime) {
        this.vertime = vertime;
    }
}
