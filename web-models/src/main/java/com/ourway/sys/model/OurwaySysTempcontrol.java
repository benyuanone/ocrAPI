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
@Table(name = "ourway_sys_tempcontrol")
public class OurwaySysTempcontrol extends UUidEntity {

    @Column(name = "TEMP_REF_OWID", nullable = true, length = 64)
    private String tempRefOwid;
    @Column(name = "CONTROL_ID", nullable = true, length = 64)
    private String controlId;
    @Column(name = "CONTROL_DESC", nullable = true, length = 480)
    private String controlDesc;
    @Column(name = "CONTROL_CSS", nullable = true, length = 4000)
    private String controlCss;
    @Column(name = "CONTROL_CHILD", nullable = true)
    private Byte controlChild;
    @Column(name = "CONTROL_TYPE", nullable = true)
    private Byte controlType;

    @Transient
    private String controlTypeLabel;
    @Column(name = "CONTROL_NAME", nullable = true)
    private String controlName;
    @Column(name = "INDEX_NO", nullable = true)
    private Integer indexNo;
    @Transient
    private Integer updateFlag;//表示子表操作的标志，0 新增，1 修改 2 删除 4 不动

    public String getControlTypeLabel() {
        if (null != this.controlType) {
            switch (this.controlType) {
                case 0:
                    return "布局视图";
                case 1:
                    return "数据表格";
                case 2:
                    return "细表表头";
                case 3:
                    return "细表列表";
                case 4:
                    return "鼠标右键操作";
                case 5:
                    return "单行按钮区";
                case 6:
                    return "树形定义";
                case 7:
                    return "bandbox基本定义";
                case 8:
                    return "bandbox中DataList定义";
                case 9:
                    return "bandbox中Tree定义";
                case 10:
                    return "多tab多panel定义";
            }
        }
        return controlTypeLabel;
    }

    public void setControlTypeLabel(String controlTypeLabel) {
        this.controlTypeLabel = controlTypeLabel;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getTempRefOwid() {
        return tempRefOwid;
    }

    public void setTempRefOwid(String tempRefOwid) {
        this.tempRefOwid = tempRefOwid;
    }


    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }


    public String getControlDesc() {
        return controlDesc;
    }

    public void setControlDesc(String controlDesc) {
        this.controlDesc = controlDesc;
    }


    public String getControlCss() {
        return controlCss;
    }

    public void setControlCss(String controlCss) {
        this.controlCss = controlCss;
    }


    public Byte getControlChild() {
        return controlChild;
    }

    public void setControlChild(Byte controlChild) {
        this.controlChild = controlChild;
    }


    public Byte getControlType() {
        return controlType;
    }

    public void setControlType(Byte controlType) {
        this.controlType = controlType;
    }


}
