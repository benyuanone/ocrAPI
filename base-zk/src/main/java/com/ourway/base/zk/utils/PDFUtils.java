package com.ourway.base.zk.utils;

import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageControlVOCompare;
import com.ourway.base.zk.models.PageLayoutVO;

import java.util.*;

/**
 * Created by Administrator on 2017/11/1.
 */
public class PDFUtils {

    public static final String XML_TITLE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    //reportName，这里需要用report替换名称
    public static final String XML_DECLARE = "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"REPORTNAME\" language=\"groovy\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" >";
    public static final String XML_DECLARE_END = "</jasperReport>";
    public static final String COMMOM_PROPERTY = "<property name=\"ireport.zoom\" value=\"1.0\"/><property name=\"ireport.x\" value=\"0\"/><property name=\"ireport.y\" value=\"0\"/>";

    //公共的属性
    public static final String STRING_PROPERTY = "<parameter name=\"PARAMNAME\" class=\"java.lang.String\" />";
    public static final String MAP_PROPERTY = "<parameter name=\"PARAMNAME\" class=\"java.util.Map\" />";
    public static final String OBJ_PROPERTY = "<parameter name=\"PARAMNAME\" class=\"java.lang.Object\" />";
    public static final String SUB_PROPERTY = "<parameter name=\"PARAMNAME\" class=\"net.sf.jasperreports.engine.JasperReport\" />";
    public static final String FIELD_XML = "<field name=\"FIELDNAME\" class=\"java.lang.String\"/>";

    public static final String REPORT_BACKGROUND = "<background><band splitType=\"Stretch\"/></background>";

    public static final int COLUNIT = 49; //单元宽度，A4除以12以后的单元宽度
    public static final int COLHEIGHT = 20;//单元高度

    //子报表申明部分SUBNAME
    public static final String SUBREPORT_DECLARE = "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"SUBNAME\" pageWidth=\"555\" pageHeight=\"802\" whenNoDataType=\"NoDataSection\" columnWidth=\"555\" leftMargin=\"0\" rightMargin=\"0\" topMargin=\"0\" bottomMargin=\"0\" >";
    public static final int SUB_WIDTH = 555;
    public static final int SUB_HEIGHT = 20;


    public static String doAddSubReport(String name, String subObject, String paramterMap, String dataSource) {
        StringBuilder sb = new StringBuilder();
        sb.append("<group name=\"" + name + "\">");
        sb.append("<groupExpression><![CDATA[$P{" + subObject + "}]]></groupExpression>");
        sb.append("<groupHeader><band height=\"1\"/></groupHeader>");
        sb.append("<groupFooter>");
        sb.append("<band height=\"50\">");
        sb.append("<subreport>");
        sb.append("<reportElement stretchType=\"RelativeToBandHeight\" x=\"0\" y=\"0\" width=\"555\" height=\"50\" />");
        sb.append("<parametersMapExpression><![CDATA[$P{" + paramterMap + "}]]></parametersMapExpression>");
        sb.append("<dataSourceExpression><![CDATA[$P{" + dataSource + "}]]></dataSourceExpression>");
        sb.append("<subreportExpression><![CDATA[$P{" + subObject + "}]]></subreportExpression>");
        sb.append("</subreport>");
        sb.append("</band>");
        sb.append("</groupFooter>");
        sb.append("</group>");
        return sb.toString();
    }

    //主报表的标题
    public static String doAddTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append("<title>");
        sb.append("<band height=\"48\" splitType=\"Stretch\">");
        if (!TextUtils.isEmpty(ZKConstants.REPORT_IMG_PATH)) {
            sb.append("<image>");
            sb.append("<reportElement x=\"0\" y=\"0\" width=\"100\" height=\"24\" />");
            sb.append("<imageExpression><![CDATA[\"" + ZKConstants.REPORT_IMG_PATH + "\"]]></imageExpression>");
            sb.append("</image>");
        }
        sb.append("<textField>");
        sb.append("<reportElement x=\"0\" y=\"24\" width=\"555\" height=\"20\" />");
        sb.append("<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">");
        sb.append("<font fontName=\"宋体\" size=\"12\" isBold=\"true\" isUnderline=\"true\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>");
        sb.append("</textElement>");
        sb.append("<textFieldExpression><![CDATA[$P{title}]]></textFieldExpression>");
        sb.append("</textField>");
        sb.append("</band>");
        sb.append("</title>");
        return sb.toString();
    }


    public static String doAddTextField(int x, int y, int width, String parameter, String position,String preStr) {
        StringBuilder sb = new StringBuilder();
        sb.append("<textField isBlankWhenNull=\"true\">");
        sb.append("<reportElement x=\"" + x + "\" y=\"" + y + "\" width=\"" + width + "\" height=\"20\" />");
        if (position.equals("right"))
            sb.append("<textElement textAlignment=\"Right\" verticalAlignment=\"Middle\">");
        else {
            sb.append("<textElement  verticalAlignment=\"Middle\">");
//            sb.append("<font isUnderline=\"true\"/>");
        }
        if (position.equals("right")) {
            //sb.append("<paragraph rightIndent=\"5\"/>");
            sb.append("<font fontName=\"宋体\" size=\"12\" isBold=\"true\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>");
        }
        else {
            sb.append("<font fontName=\"宋体\" isUnderline=\"true\" size=\"12\" isBold=\"true\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>");
          //  sb.append("<paragraph leftIndent=\"5\"/>");
        }
        sb.append("</textElement>");
        sb.append("<textFieldExpression><![CDATA[$P{" + parameter + "}+\""+preStr+"\"]]></textFieldExpression>");
        sb.append("</textField>");
        return sb.toString();
    }


    //主表，添加pageHeader
    public static String doAddMainTalble(Map<String, List<PageControlVO>> mainTables, List<String> paramsList) {
        List<String> setList = TemplateUtils.doGenOrderForSet(mainTables);
        int totalHeight = (setList.size() + 1) * COLHEIGHT;
        StringBuilder sb = new StringBuilder();
        sb.append("<pageHeader>");
        sb.append("<band height=\"" + totalHeight + "\" splitType=\"Stretch\">");
        int rowIndex = 1;
        for (String s : setList) {
            List<PageControlVO> subComps = mainTables.get(s);
            sb.append(doAddRow(subComps, rowIndex, paramsList));
            rowIndex++;
        }
        sb.append("</band>");
        sb.append("</pageHeader>");
        return sb.toString();
    }

    //新增一行
    public static String doAddRow(List<PageControlVO> subComps, int rowIndex, List<String> paramsList) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int x = 0;
        int y = (rowIndex - 1) * COLHEIGHT;
        int width = 0;
        for (PageControlVO subComp : subComps) {
            //表示有label
            if (null != subComp.getLayoutComponent().getCompLable() && subComp.getLayoutComponent().getCompLable() > 0) {
                if(index==0) {
                    x = x + (subComp.getLayoutComponent().getCompLable()-2) * COLUNIT;
                    width = (subComp.getLayoutComponent().getCompLable()) * COLUNIT;
                }else{
                    x = x + (subComp.getLayoutComponent().getCompLable()) * COLUNIT;
                    width = (subComp.getLayoutComponent().getCompLable()+1) * COLUNIT;
                }
                sb.append(doAddTextField(x, y, width, subComp.getKjLabelid(), "right",":"));
                paramsList.add(subComp.getKjLabelid());
            }
            //方式当前控件
            if (null == subComp.getLayoutComponent().getCompCols())
                continue;
            if(index==0)
                x = x + (subComp.getLayoutComponent().getCompCols()) * COLUNIT+5;
            else
                x = x + (subComp.getLayoutComponent().getCompCols()) * COLUNIT+5;

            width = subComp.getLayoutComponent().getCompCols() * COLUNIT;
            if (!TextUtils.isEmpty(subComp.getKjAttributeDisplay())) {
                sb.append(doAddTextField(x, y, width, subComp.getKjAttributeDisplay(), "left",""));
                paramsList.add(subComp.getKjAttributeDisplay());
            } else {
                sb.append(doAddTextField(x, y, width, subComp.getKjAttribute(), "left",""));
                paramsList.add(subComp.getKjAttribute());
            }
            index++;
        }
        return sb.toString();
    }


    //子报表，增加报表表头
    public static String doSubColumnHeaderTextfiled(int x, int y, int width, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("<textField>");
        sb.append("<reportElement mode=\"Opaque\" x=\"" + x + "\" y=\"" + y + "\" width=\"" + width + "\" height=\"20\" backcolor=\"#CCCCCC\" />");
        sb.append("<box>");
        sb.append("<pen lineWidth=\"0.25\"/><topPen lineWidth=\"0.25\"/><leftPen lineWidth=\"0.25\"/><bottomPen lineWidth=\"0.25\"/><rightPen lineWidth=\"0.25\"/>");
        sb.append("</box>");
        sb.append("<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">");
        sb.append("<font fontName=\"宋体\" isBold=\"true\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>");
        sb.append("</textElement>");
        sb.append("<textFieldExpression><![CDATA[$P{" + name + "}]]></textFieldExpression>");
        sb.append("</textField>");
        return sb.toString();
    }

    //zi baobiao biaoti
    public static String doSubColumnTitle(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("<textField>");
        sb.append("<reportElement x=\"0\" y=\"0\" width=\"555\" height=\"20\" />");
        sb.append("<textElement verticalAlignment=\"Middle\">");
        sb.append("<font fontName=\"宋体\" isBold=\"true\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>");
        sb.append("</textElement>");
        sb.append("<textFieldExpression><![CDATA[$P{" + name + "}]]></textFieldExpression>");
        sb.append("</textField>");
        return sb.toString();
    }

    //新增子表表的field域
    public static String doSubField(int x, int y, int width, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("<textField>");
        sb.append("<reportElement x=\"" + x + "\" y=\"" + y + "\" width=\"" + width + "\" height=\"20\" />");
        sb.append("<box>");
        sb.append("<pen lineWidth=\"0.25\"/><topPen lineWidth=\"0.25\"/><leftPen lineWidth=\"0.25\"/><bottomPen lineWidth=\"0.25\"/><rightPen lineWidth=\"0.25\"/>");
        sb.append("</box>");
        sb.append("<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">");
        sb.append("<font fontName=\"宋体\" pdfFontName=\"STSong-Light\" pdfEncoding=\"UniGB-UCS2-H\" isPdfEmbedded=\"true\"/>");
        sb.append("</textElement>");
        sb.append("<textFieldExpression><![CDATA[$F{" + name + "}]]></textFieldExpression>");
        sb.append("</textField>");
        return sb.toString();
    }


    //xinzeng row
    public static String doCreateSubTableHeaderXML(List<PageControlVO> subComps, List<String> paramsList, int rowIndex) {
        int x = 0;
        int y = rowIndex * SUB_HEIGHT;
        int width = SUB_WIDTH / subComps.size();
        int _index = 0;
        Collections.sort(subComps, new PageControlVOCompare());
        StringBuilder sb = new StringBuilder();
        for (PageControlVO subComp : subComps) {
            x = x + _index * width;
            sb.append(doSubColumnHeaderTextfiled(x, y, width, subComp.getKjLabelid()));
            paramsList.add(subComp.getKjLabelid());
            _index++;
        }
        return sb.toString();
    }

    public static String doCreateSubTableFieldXML(List<PageControlVO> subComps, List<String> paramsList) {
        int x = 0;
        int y = 0;
        int width = SUB_WIDTH / subComps.size();
        int _index = 0;
        Collections.sort(subComps, new PageControlVOCompare());
        StringBuilder sb = new StringBuilder();
        for (PageControlVO subComp : subComps) {
            x = x + _index * width;
            sb.append(doSubField(x, y, width, subComp.getKjAttribute()));
            paramsList.add(subComp.getKjAttribute());
            _index++;
        }
        return sb.toString();
    }

    //生成从表
    public static String doCreateSubTableXML(String name, PageLayoutVO data) {
        List<String> params = new ArrayList<String>();
        List<String> fieldParams = new ArrayList<String>();
        String propertyXML = "";
        String filedXml = "";
        StringBuilder sb = new StringBuilder();
        sb.append(XML_TITLE);
        sb.append(SUBREPORT_DECLARE.replaceAll("SUBNAME", name));
        sb.append(COMMOM_PROPERTY);
        int headSize = data.getLayOutComponents().size();

        for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
            List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
            String header = doCreateSubTableHeaderXML(subComps, params, index);
            propertyXML = propertyXML + header;
        }

        //添加最后一行的filed

        for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
            if (index == data.getLayOutComponents().size()) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                String header = doCreateSubTableFieldXML(subComps, fieldParams);
                filedXml = filedXml + header;
            }
        }
        //添加property
        sb.append(STRING_PROPERTY.replaceAll("PARAMNAME", "subtitle"));
        for (String param : params) {
            sb.append(STRING_PROPERTY.replaceAll("PARAMNAME", param));
        }
        for (String fieldParam : fieldParams) {
            sb.append(FIELD_XML.replaceAll("FIELDNAME", fieldParam));
        }

        sb.append(REPORT_BACKGROUND);
        //columnHeader
        int _height = headSize * 20 + 20;
        sb.append("<columnHeader>");
        sb.append("<band height=\"" + _height + "\" splitType=\"Stretch\">");
        sb.append(doSubColumnTitle("subtitle"));
        sb.append(propertyXML);
        sb.append("</band>");
        sb.append("</columnHeader>");
        sb.append("<detail>");
        sb.append("<band height=\"20\" splitType=\"Stretch\">");
        sb.append(filedXml);
        sb.append("</band>");
        sb.append("</detail>");
        sb.append("<columnFooter>");
        sb.append("<band height=\"20\" splitType=\"Stretch\"/>");
        sb.append("</columnFooter>");
        sb.append("</jasperReport>");
        return sb.toString();
    }


    //生成主表
    public static String doCreateMainTableXML(String name, List<String> subReports, Map<String, List<PageControlVO>> mainTables) {
        List<String> params = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        sb.append(XML_TITLE);
        sb.append(XML_DECLARE.replaceAll("REPORTNAME", name));
        sb.append(COMMOM_PROPERTY);
        String mainTable = doAddMainTalble(mainTables, params);
        //新增主表标题
        sb.append(STRING_PROPERTY.replaceAll("PARAMNAME", "title"));
        //增加参数
        Map<String, Object> map = new HashMap<String, Object>();
        for (String key : params) {
            if (null == map.get(key)) {
                sb.append(STRING_PROPERTY.replaceAll("PARAMNAME", key));
                map.put(key, 1);
            }
        }
        //增减subreport参数
        for (String param : subReports) {
            sb.append(SUB_PROPERTY.replaceAll("PARAMNAME", param + "Report"));
            sb.append(MAP_PROPERTY.replaceAll("PARAMNAME", param + "Map"));
            sb.append(OBJ_PROPERTY.replaceAll("PARAMNAME", param + "Obj"));
        }
        //添加子报表
        for (String param : subReports) {
            sb.append(doAddSubReport(param, param + "Report", param + "Map", param + "Obj"));
        }
        //添加background
        sb.append(REPORT_BACKGROUND);
        //添加title
        sb.append(doAddTitle());
        //添加pageHeader
        sb.append(mainTable);

        sb.append(XML_DECLARE_END);

        return sb.toString();

    }

}
