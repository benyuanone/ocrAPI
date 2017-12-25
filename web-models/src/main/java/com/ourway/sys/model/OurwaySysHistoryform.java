package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "OURWAY_SYS_HISTORYFORM")
public class OurwaySysHistoryform extends UUidEntity {

    @Column(name = "RELATIONSHIP_27_OWID", nullable = true, length = 240)
    private String formOwid;
    @Column(name = "CONTENT", nullable = true, length = 64)
    private String content;
    @Column(name = "CZJG", nullable = true, length = 64)
    private Byte czjg;
    @Column(name = "CZR", nullable = true, length = 64)
    private String czr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CZSJ", nullable = true, length = 64)
    private Date czsj;

    public String getFormOwid() {
        return formOwid;
    }

    public void setFormOwid(String formOwid) {
        this.formOwid = formOwid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getCzjg() {
        return czjg;
    }

    public void setCzjg(Byte czjg) {
        this.czjg = czjg;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }
}
