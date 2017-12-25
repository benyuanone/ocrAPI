package com.ourway.base.zk.models;

import com.ourway.base.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 PageControl : <p>
 * <p>说明:页面相应控件</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 23:54
 * </pre>
 */
public class PageControlVO {
    private String owid;
    private String pageRefOwid;
    private Integer kjOrder;
    private String kjName;
    private String kjLabelid;
    private String kjAttribute;
    private String kjAttributeDisplay;
    private String kjQueryAttribute;
    private Integer kjType;
    private String kjClass;
    private Integer kjDataType;
    private String kjJavaType;
    private String kjFormat;
    private Integer kjReportParam;
    private Integer kjReportField;
    private Integer kjDatasource;
    private String kjInterface;
    private Object kjDefaultData;
    private String kjTranslate;
    private Integer kjHidden;
    private Integer kjIndex;
    private Integer kjFilterType;
    private String kjClassImpl;
    private Object kjInitData;
    private Object kjKzsx;//控件扩展属性
    private String kjPre;
    private String kjAfter;
    private String kjDefaultcss;
    private String kjIconclass;
    private String kjTipsLabel;
    private String kjBindKey;

    private Integer treeType;//用来判断树节点的新增修改类型

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

    public Integer getTreeType() {
        return treeType;
    }

    public void setTreeType(Integer treeType) {
        this.treeType = treeType;
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

    public Object getKjKzsx() {
        if (kjKzsx instanceof ArrayList) {
            return JsonUtil.toJson(((List) kjKzsx).toArray());
        }
        if (kjKzsx instanceof Map) {
            return JsonUtil.toJson(kjKzsx);
        }
        return kjKzsx;

    }

    public void setKjKzsx(Object kjKzsx) {
        this.kjKzsx = kjKzsx;
    }

    public String getKjQueryAttribute() {
        return kjQueryAttribute;
    }

    public void setKjQueryAttribute(String kjQueryAttribute) {
        this.kjQueryAttribute = kjQueryAttribute;
    }

    public void setKjInitData(Object kjInitData) {
        this.kjInitData = kjInitData;
    }

    private PageLayoutControlVO layoutComponent;

    public String getKjClassImpl() {
        return kjClassImpl;
    }

    public void setKjClassImpl(String kjClassImpl) {
        this.kjClassImpl = kjClassImpl;
    }

    public Integer getKjFilterType() {
        return kjFilterType;
    }

    public String getKjAttributeDisplay() {
        return kjAttributeDisplay;
    }

    public void setKjAttributeDisplay(String kjAttributeDisplay) {
        this.kjAttributeDisplay = kjAttributeDisplay;
    }

    public void setKjFilterType(Integer kjFilterType) {
        this.kjFilterType = kjFilterType;
    }

    public Object getKjInitData() {
        if (kjInitData instanceof ArrayList) {
            return JsonUtil.toJson(((List) kjInitData).toArray());
        }
        if (kjInitData instanceof Map) {
            return JsonUtil.toJson(kjInitData);
        }
        return kjInitData;
    }

    public Object getKjDefaultData() {
        if (kjDefaultData instanceof ArrayList) {
            return JsonUtil.toJson(((List) kjDefaultData).toArray());
        }
        if (kjDefaultData instanceof Map) {
            return JsonUtil.toJson(kjDefaultData);
        }
        return kjDefaultData;
    }

    public void setKjDefaultData(Object kjDefaultData) {
        this.kjDefaultData = kjDefaultData;
    }

    public void setKjInitData(String kjInitData) {
        this.kjInitData = kjInitData;
    }

    public PageLayoutControlVO getLayoutComponent() {
        return layoutComponent;
    }

    public void setLayoutComponent(PageLayoutControlVO layoutComponent) {
        this.layoutComponent = layoutComponent;
    }

    public String getOwid() {
        return owid;
    }

    public void setOwid(String owid) {
        this.owid = owid;
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

    public Integer getKjType() {
        return kjType;
    }

    public void setKjType(Integer kjType) {
        this.kjType = kjType;
    }

    public String getKjClass() {
        return kjClass;
    }

    public void setKjClass(String kjClass) {
        this.kjClass = kjClass;
    }

    public Integer getKjDataType() {
        return kjDataType;
    }

    public void setKjDataType(Integer kjDataType) {
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

    public Integer getKjReportParam() {
        return kjReportParam;
    }

    public void setKjReportParam(Integer kjReportParam) {
        this.kjReportParam = kjReportParam;
    }

    public Integer getKjReportField() {
        return kjReportField;
    }

    public void setKjReportField(Integer kjReportField) {
        this.kjReportField = kjReportField;
    }

    public Integer getKjDatasource() {
        return kjDatasource;
    }

    public void setKjDatasource(Integer kjDatasource) {
        this.kjDatasource = kjDatasource;
    }

    public String getKjInterface() {
        return kjInterface;
    }

    public void setKjInterface(String kjInterface) {
        this.kjInterface = kjInterface;
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

    public Integer getKjHidden() {
        return kjHidden;
    }

    public void setKjHidden(Integer kjHidden) {
        this.kjHidden = kjHidden;
    }

    public Integer getKjIndex() {
        return kjIndex;
    }

    public void setKjIndex(Integer kjIndex) {
        this.kjIndex = kjIndex;
    }

    public static PageControlVO instance(int compOrder,String kjName,String kjAttribute,String kjLabelid,Integer kjType,Integer kjDataType){
        PageControlVO vo = new PageControlVO();
        vo.setKjName(kjName);
        PageLayoutControlVO layoutControlVO = new PageLayoutControlVO();
        layoutControlVO.setCompOrder(compOrder);
        vo.setLayoutComponent(layoutControlVO);
        vo.setKjAttribute(kjAttribute);
        vo.setKjLabelid(kjLabelid);
        vo.setKjType(kjType);
        vo.setKjDataType(kjDataType);
        return vo;
    }


}
