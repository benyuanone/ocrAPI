package com.ourway.base.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 所有表的扩展字段
 * Created by jack on 2017/1/28.
 */
/**<p>方法 AbstractExpEntity : <p>
*<p>说明:所有表的扩展字段</p>
*<pre>
*@author JackZhou
*@date 2017/3/11 16:22
</pre>
*/
@MappedSuperclass
public abstract class AbstractExpEntity extends AbstractVerEntity {
    private static final long serialVersionUID = 1L;
    @Column(name="EXP1",length = 200)
    private String exp1;
    @Column(name="EXP2",length = 200)
    private String exp2;
    @Column(name="EXP3",length = 200)
    private String exp3;
    @Column(name="EXP4",length = 200)
    private String exp4;
    @Column(name="EXP5",length = 200)
    private String exp5;
    @Column(name="EXP6",length = 200)
    private String exp6;
    @Column(name="EXP7",length = 200)
    private String exp7;
    @Column(name="EXP8",length = 200)
    private String exp8;
    @Column(name="EXP9",length = 200)
    private String exp9;
    @Column(name="EXP10",length = 200)
    private String exp10;

    public String getExp1() {
        return exp1;
    }

    public void setExp1(String exp1) {
        this.exp1 = exp1;
    }

    public String getExp2() {
        return exp2;
    }

    public void setExp2(String exp2) {
        this.exp2 = exp2;
    }

    public String getExp3() {
        return exp3;
    }

    public void setExp3(String exp3) {
        this.exp3 = exp3;
    }

    public String getExp4() {
        return exp4;
    }

    public void setExp4(String exp4) {
        this.exp4 = exp4;
    }

    public String getExp5() {
        return exp5;
    }

    public void setExp5(String exp5) {
        this.exp5 = exp5;
    }

    public String getExp6() {
        return exp6;
    }

    public void setExp6(String exp6) {
        this.exp6 = exp6;
    }

    public String getExp7() {
        return exp7;
    }

    public void setExp7(String exp7) {
        this.exp7 = exp7;
    }

    public String getExp8() {
        return exp8;
    }

    public void setExp8(String exp8) {
        this.exp8 = exp8;
    }

    public String getExp9() {
        return exp9;
    }

    public void setExp9(String exp9) {
        this.exp9 = exp9;
    }

    public String getExp10() {
        return exp10;
    }

    public void setExp10(String exp10) {
        this.exp10 = exp10;
    }
}
