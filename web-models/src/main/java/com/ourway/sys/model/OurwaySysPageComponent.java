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
@Table(name = "OURWAY_SYS_PAGE_COMPONENT")
public class OurwaySysPageComponent extends UUidEntity {

    @Column(name = "PAGE_REF_OWID", nullable = true, length = 64)
    private String pageRefOwid;
    @Column(name = "KJ_ORDER", nullable = true)
    private Integer kjOrder;
    @Column(name = "KJ_NAME", nullable = true, length = 240)
    private String kjName;
    @Column(name = "KJ_LABELID", nullable = true, length = 64)
    private String kjLabelid;
    @Column(name = "KJ_ATTRIBUTE", nullable = true, length = 64)
    private String kjAttribute;
    @Column(name = "KJ_BIND_KEY", nullable = true, length = 64)
    private String kjBindKey;
    @Column(name = "KJ_ATTRIBUTE_DISPLAY", nullable = true, length = 64)
    private String kjAttributeDisplay;
    @Column(name = "KJ_QUERY_ATTRIBUTE", nullable = true, length = 64)
    private String kjQueryAttribute;
    @Column(name = "KJ_TYPE", nullable = true)
    private Byte kjType;
    @Transient
    private String kjTypeLabel;
    @Column(name = "KJ_CLASS", nullable = true, length = 160)
    private String kjClass;
    @Column(name = "KJ_DATA_TYPE", nullable = true)
    private Byte kjDataType;
    @Transient
    private String kjDataTypeLabel;
    @Column(name = "KJ_JAVA_TYPE", nullable = true, length = 160)
    private String kjJavaType;
    @Column(name = "KJ_FORMAT", nullable = true, length = 160)
    private String kjFormat;
    @Column(name = "KJ_REPORT_PARAM", nullable = true)
    private Byte kjReportParam;
    @Transient
    private String kjReportParamLabel;
    @Column(name = "KJ_REPORT_FIELD", nullable = true)
    private Byte kjReportField;
    @Transient
    private String kjReportFieldLabel;
    @Column(name = "KJ_DATASOURCE", nullable = true)
    private Byte kjDatasource;
    @Transient
    private String kjDatasourceLabel;
    @Column(name = "KJ_INTERFACE", nullable = true, length = 160)
    private String kjInterface;
    @Column(name = "KJ_DEFAULT_DATA", nullable = true, length = 3000)
    private String kjDefaultData;
    @Column(name = "KJ_TRANSLATE", nullable = true, length = 160)
    private String kjTranslate;
    @Column(name = "KJ_INIT_DATA", nullable = true, length = 240)
    private String kjInitData;
    @Column(name = "KJ_HIDDEN", nullable = true)
    private Byte kjHidden;
    @Transient
    private String kjHiddenLabel;
    @Column(name = "KJ_FILTER_TYPE", nullable = true)
    private Byte kjFilterType;
    @Transient
    private String kjFilterTypeLabel;
    @Column(name = "KJ_INDEX", nullable = true)
    private Integer kjIndex;
    @Column(name = "KJ_CLASS_IMPL", nullable = true)
    private String kjClassImpl;
    @Column(name = "KJ_KZSX", nullable = true)
    private String kjKzsx;
    @Column(name = "KJ_PRE", nullable = true)
    private String kjPre;
    @Column(name = "KJ_AFTER", nullable = true)
    private String kjAfter;
    @Column(name = "KJ_DEFAULTCSS", nullable = true)
    private String kjDefaultcss;
    @Column(name = "KJ_ICONCLASS", nullable = true)
    private String kjIconclass;
    @Column(name = "KJ_TIPS_LABEL", nullable = true)
    private String kjTipsLabel;

    @Transient
    private OurwaySysLayoutComponent layoutComponent;

    public String getKjBindKey() {
        return kjBindKey;
    }

    public void setKjBindKey(String kjBindKey) {
        this.kjBindKey = kjBindKey;
    }

    public String getKjTipsLabel() {
        return kjTipsLabel;
    }

    public void setKjTipsLabel(String kjTipsLabel) {
        this.kjTipsLabel = kjTipsLabel;
    }

    public String getKjIconclass() {
        return kjIconclass;
    }

    public void setKjIconclass(String kjIconclass) {
        this.kjIconclass = kjIconclass;
    }

    public String getKjDefaultcss() {
        return kjDefaultcss;
    }

    public void setKjDefaultcss(String kjDefaultcss) {
        this.kjDefaultcss = kjDefaultcss;
    }

    public String getKjPre() {
        return kjPre;
    }

    public void setKjPre(String kjPre) {
        this.kjPre = kjPre;
    }

    public String getKjAfter() {
        return kjAfter;
    }

    public void setKjAfter(String kjAfter) {
        this.kjAfter = kjAfter;
    }

    public String getKjKzsx() {
        return kjKzsx;
    }

    public void setKjKzsx(String kjKzsx) {
        this.kjKzsx = kjKzsx;
    }

    public String getKjQueryAttribute() {
        return kjQueryAttribute;
    }

    public void setKjQueryAttribute(String kjQueryAttribute) {
        this.kjQueryAttribute = kjQueryAttribute;
    }

    public void setKjTypeLabel(String kjTypeLabel) {
        this.kjTypeLabel = kjTypeLabel;
    }

    public void setKjDataTypeLabel(String kjDataTypeLabel) {
        this.kjDataTypeLabel = kjDataTypeLabel;
    }

    public void setKjReportParamLabel(String kjReportParamLabel) {
        this.kjReportParamLabel = kjReportParamLabel;
    }

    public void setKjReportFieldLabel(String kjReportFieldLabel) {
        this.kjReportFieldLabel = kjReportFieldLabel;
    }

    public void setKjDatasourceLabel(String kjDatasourceLabel) {
        this.kjDatasourceLabel = kjDatasourceLabel;
    }

    public void setKjHiddenLabel(String kjHiddenLabel) {
        this.kjHiddenLabel = kjHiddenLabel;
    }

    public void setKjFilterTypeLabel(String kjFilterTypeLabel) {
        this.kjFilterTypeLabel = kjFilterTypeLabel;
    }

    public String getKjTypeLabel() {
        switch (this.kjType) {
            case 0:
                return "Textbox";
            case 1:
                return "Listbox";
            case 2:
                return "Button";
            case 3:
                return "Bandbox";
            case 4:
                return "Datebox";
            case 5:
                return "Intbox";
            case 6:
                return "Decimalbox";
            case 7:
                return "Longbox";
            case 8:
                return "CheckBox";
            case 9:
                return "Radio";
            case 10:
                return "TimeBox";
            case 11:
                return "doublespinner";
            case 12:
                return "spinner";
        }
        return kjTypeLabel;
    }

    public String getKjDataTypeLabel() {
        try {
            if (null != this.kjDataType)
                switch (this.kjDataType) {
                    case 0:
                        return "String";
                    case 1:
                        return "Integer";
                    case 2:
                        return "Byte";
                    case 3:
                        return "Double";
                    case 4:
                        return "Decimal";
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kjDataTypeLabel;
    }

    public String getKjReportParamLabel() {
        if (null != this.kjReportParam && this.kjReportParam == 1)
            return "报表参数";
        return kjReportParamLabel;
    }

    public String getKjReportFieldLabel() {
        if (null != this.kjReportField && this.kjReportField == 1)
            return "报表字段";
        return kjReportFieldLabel;
    }

    public String getKjDatasourceLabel() {
        if (null != this.kjDataType)
            switch (this.kjDataType) {
                case 0:
                    return "用户输入";
                case 1:
                    return "默认值";
                case 2:
                    return "API接口";
                case 3:
                    return "自定义类";

            }
        return kjDatasourceLabel;
    }

    public String getKjHiddenLabel() {
        if (null != this.kjHidden && this.kjHidden == 1)
            return "无效";
        return kjHiddenLabel;
    }

    public String getKjFilterTypeLabel() {
        if (null != kjFilterType)
            switch (this.kjFilterType) {
                case 0:
                    return "等值条件";
                case 1:
                    return "like 条件";
                case 2:
                    return "between条件";
                case 3:
                    return "in 多个值";
                case 4:
                    return "大于等于和小于等于";

            }
        return kjFilterTypeLabel;
    }

    public String getKjClassImpl() {
        return kjClassImpl;
    }

    public void setKjClassImpl(String kjClassImpl) {
        this.kjClassImpl = kjClassImpl;
    }

    public String getKjAttributeDisplay() {
        return kjAttributeDisplay;
    }

    public void setKjAttributeDisplay(String kjAttributeDisplay) {
        this.kjAttributeDisplay = kjAttributeDisplay;
    }

    public Byte getKjFilterType() {
        return kjFilterType;
    }

    public void setKjFilterType(Byte kjFilterType) {
        this.kjFilterType = kjFilterType;
    }

    public String getKjInitData() {
        return kjInitData;
    }

    public void setKjInitData(String kjInitData) {
        this.kjInitData = kjInitData;
    }

    public OurwaySysLayoutComponent getLayoutComponent() {
        return layoutComponent;
    }

    public void setLayoutComponent(OurwaySysLayoutComponent layoutComponent) {
        this.layoutComponent = layoutComponent;
    }

    public Integer getKjIndex() {
        return kjIndex;
    }

    public void setKjIndex(Integer kjIndex) {
        this.kjIndex = kjIndex;
    }

    public String getPageRefOwid() {
        return pageRefOwid;
    }

    public void setPageRefOwid(String pageRefOwid) {
        this.pageRefOwid = pageRefOwid;
    }


    public Integer getKjOrder() {
        return kjOrder;
    }

    public void setKjOrder(Integer kjOrder) {
        this.kjOrder = kjOrder;
    }


    public String getKjName() {
        return kjName;
    }

    public void setKjName(String kjName) {
        this.kjName = kjName;
    }


    public String getKjLabelid() {
        return kjLabelid;
    }

    public void setKjLabelid(String kjLabelid) {
        this.kjLabelid = kjLabelid;
    }


    public String getKjAttribute() {
        return kjAttribute;
    }

    public void setKjAttribute(String kjAttribute) {
        this.kjAttribute = kjAttribute;
    }


    public Byte getKjType() {
        return kjType;
    }

    public void setKjType(Byte kjType) {
        this.kjType = kjType;
    }


    public String getKjClass() {
        return kjClass;
    }

    public void setKjClass(String kjClass) {
        this.kjClass = kjClass;
    }


    public Byte getKjDataType() {
        return kjDataType;
    }

    public void setKjDataType(Byte kjDataType) {
        this.kjDataType = kjDataType;
    }


    public String getKjJavaType() {
        return kjJavaType;
    }

    public void setKjJavaType(String kjJavaType) {
        this.kjJavaType = kjJavaType;
    }


    public String getKjFormat() {
        return kjFormat;
    }

    public void setKjFormat(String kjFormat) {
        this.kjFormat = kjFormat;
    }

    public Byte getKjReportParam() {
        return kjReportParam;
    }

    public void setKjReportParam(Byte kjReportParam) {
        this.kjReportParam = kjReportParam;
    }


    public Byte getKjReportField() {
        return kjReportField;
    }

    public void setKjReportField(Byte kjReportField) {
        this.kjReportField = kjReportField;
    }


    public Byte getKjDatasource() {
        return kjDatasource;
    }

    public void setKjDatasource(Byte kjDatasource) {
        this.kjDatasource = kjDatasource;
    }


    public String getKjInterface() {
        return kjInterface;
    }

    public void setKjInterface(String kjInterface) {
        this.kjInterface = kjInterface;
    }


    public String getKjDefaultData() {
        return kjDefaultData;
    }

    public void setKjDefaultData(String kjDefaultData) {
        this.kjDefaultData = kjDefaultData;
    }


    public String getKjTranslate() {
        return kjTranslate;
    }

    public void setKjTranslate(String kjTranslate) {
        this.kjTranslate = kjTranslate;
    }


    public Byte getKjHidden() {
        return kjHidden;
    }

    public void setKjHidden(Byte kjHidden) {
        this.kjHidden = kjHidden;
    }


}
