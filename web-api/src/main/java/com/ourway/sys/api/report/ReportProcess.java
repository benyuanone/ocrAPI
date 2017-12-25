package com.ourway.sys.api.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/3/28.
 */
public class ReportProcess {
    private static Log logger = LogFactory
            .getLog(ReportProcess.class);
    /** 設置字段寬度 */
    private final static int textWidth = 80;
    /** 設置字段高度 */
    private final static int textHeight = 20;
    /** coulumnHeader區域字體大小 */
    private final static int columnHeaderfontSize = 14;
    /** detail 區域字體大小 */
    private final static int fontSize = 12;
    /** 設置間距 */
    private final static int X = 80;
    /** coulumnHeader區域高度 */
    private final static int columnHeaderHeight = 20;
    /** detail 區域高度 */
    private final static int detailHeight = 20;
    /**  */
    private static String aliasColumn = "column";

    public static void main(String args[]){
        try {
            String[] columns = {"字段1", "字段2", "字段3", "字段4"};
            File f = new File("d://test.pdf");
            List<Object[]> list = new ArrayList<Object[]>();
            Object[] obj = null;
            for(int j=0;j<50;j++){
                obj = new Object[columns.length];
                for(int i=0;i<columns.length;i++){
                    obj[i] = columns[i]+j+","+i;
                }
                list.add(obj);
            }
            OutputStream out = new FileOutputStream(f);
            preview(columns, list, out);
            out.close();
            logger.info("成功～");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * PDF打印
     *
     * @param headers
     *            colimnHeaders
     * @param list
     *            數據來源
     * @param out
     *            輸出流
     * @throws Exception
     */
    public static void preview(String headers[], List<Object[]> list,
                               OutputStream out) throws Exception {
//        long start = System.currentTimeMillis();
//        String[] alias = preaseAliasColumnHeaders(headers);
//        JasperReport jp = getJasperReport(headers, alias);
//        Map<String, Object> parameters = new HashMap<String,Object>();
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jp, parameters,
//                new JRBeanCollectionDataSource(getBaseList(alias, phrase(list))));
//        logger.info("Filling time : " + (System.currentTimeMillis() - start));
//        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
//        logger.info("Printing time : " + (System.currentTimeMillis() - start));
    }

    /**
     * 打印機打印報表
     *
     * @param headers
     *            colimnHeaders
     * @param list
     *            數據來源
     * @param parameters
     *            Map 參數
     * @param printerName
     *            打印機名稱
     *  @param printerName
     *            打印機份數
     * @param tempFileName
     *            创建报表打印临时文件的路径
     * @throws Exception
     */
    public static void print(String headers[], List<Object[]> list,
                             Map<String, Object> parameters, String printerName, int copies,
                             String tempFileName) throws Exception {
//        long start = System.currentTimeMillis();
//        /** 获取打印报表数据 */
//        String[] alias = preaseAliasColumnHeaders(headers);
//        JasperReport jp = getJasperReport(headers, alias);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jp, parameters,
//                new JRBeanCollectionDataSource(getBaseList(alias, phrase(list))));
//        /** 保存向打印机发送的临时报表数据 */
//        JRSaver.saveObject(jasperPrint, tempFileName);
//        logger.info("Filling time : " + (System.currentTimeMillis() - start));
//        /** 设置打印参数 */
//        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//        /** 设置A4纸张 */
//        printRequestAttributeSet.add(MediaSizeName.ISO_A4);
//        /** 設置打印份數 */
//        printRequestAttributeSet.add(new Copies(copies));
//        /** 设置打印机 */
//        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
//        /** 添加打印机名称 */
//        // "Epson Stylus 800 ESC/P// 2"
//        printServiceAttributeSet.add(new PrinterName(printerName, null));
//        /** 打印机对象 */
//        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
//        /** 向打印机发送的临时报表数据 */
//        exporter
//                .setParameter(JRExporterParameter.INPUT_FILE_NAME, tempFileName);
//        exporter.setParameter(
//                JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
//                printRequestAttributeSet);
//        exporter.setParameter(
//                JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
//                printServiceAttributeSet);
//        /** 是否弹出打印页数的提示对话框 */
//        exporter.setParameter(
//                JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
//                Boolean.FALSE);
//        /** 是否弹出打印机设置属性的提示对话框 */
//        exporter.setParameter(
//                JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
//                Boolean.FALSE);
//        /** 执行打印机打印报表 */
//        exporter.exportReport();
//        logger.info("Printing time : " + (System.currentTimeMillis() - start));
    }

    public static List<String[]> phrase(List<Object[]> list){
        List<String[]> temps = new ArrayList<String[]>();
        String []s= null;
        for(Object[] obj : list){
            s = new String[obj.length];
            for(int i = 0;i<obj.length;i++){
                s[i] = obj[i].toString();
            }
            temps.add(s);
        }
        return temps;
    }

    /**
     * 產生columnHeaders的別名(Headers[]有可能為中文，所以有必要用別名來替換)
     *
     * @param headers
     * @return
     */
    private static String[] preaseAliasColumnHeaders(String headers[]) {
        int size = headers.length;
        String[] alias = new String[size];
        for (int i = 0; i < size; i++) {
            alias[i] = aliasColumn + i;
        }
        return alias;
    }


//    private static JasperReport getJasperReport(String[] headers,
//                                                String alias[]) throws JRException {
//        JasperDesign design = new JasperDesign();
//
//        // name="statistics"
//        design.setName("statistics");
//        // columnCount="1"
//        // printOrder="Vertical"
////        design.setPrintOrder(JRReport.);
//        // orientation="Portrait"
////        design.setOrientation(JRReport.ORIENTATION_PORTRAIT);
//        // pageWidth="595"
//        design.setPageWidth(595);
//        // pageHeight="842"
//        design.setPageHeight(842);
//        // columnWidth="535"
//        design.setColumnWidth(535);
//        // columnSpacing="0"
//        design.setColumnSpacing(0);
//        // leftMargin="30"
//        design.setLeftMargin(30);
//        // rightMargin="30"
//        design.setRightMargin(30);
//        // topMargin="20"
//        design.setTopMargin(20);
//        // bottomMargin="20"
//        design.setBottomMargin(20);
//        // whenNoDataType="NoPages"
////        design.setWhenNoDataType(JRReport.WHEN_NO_DATA_TYPE_BLANK_PAGE);
//        // isTitleNewPage="false"
//        design.setTitleNewPage(false);
//        // isSummaryNewPage="false"
//        design.setSummaryNewPage(false);
//
//        // JRDesignBand title = new JRDesignBand();
//        // title.setHeight(50);
//        // JRDesignStaticText titleText = new JRDesignStaticText();
//        // titleText.setText("test report");
//        // titleText.setX(230);
//        // titleText.setFontSize(20);
//        // titleText.setHeight(50);
//        // titleText.setWidth(100);
//        // title.addElement(titleText);
//        // design.setTitle(title);
//        JRDesignBand columnHeader = new JRDesignBand();
//        columnHeader.setHeight(columnHeaderHeight);
//
//        JRDesignBand detail = new JRDesignBand();
//        detail.setHeight(detailHeight);
//
//        for (int i = 0; i < headers.length; i++) {
//            // add column headers
//            JRDesignStaticText staticText = new JRDesignStaticText();
//            staticText.setText(headers[i]);
//            staticText.setFontSize(columnHeaderfontSize);
//            staticText.setHeight(textHeight);
//            staticText.setWidth(textWidth);
//            staticText.setX(X * i);
//            staticText.setPdfFontName("MHei-Medium");
//            staticText.setPdfEmbedded(true);
//            staticText.setPdfEncoding("UniCNS-UCS2-H");
////            staticText.setTextAlignment(JRBasePrintText.HORIZONTAL_ALIGN_CENTER);
////            staticText.setLeftBorder(JRBaseLine.PEN_1_POINT);
////            staticText.setTopBorder(JRBaseLine.PEN_1_POINT);
////            staticText.setRightBorder(JRBaseLine.PEN_1_POINT);
////            staticText.setBottomBorder(JRBaseLine.PEN_1_POINT);
//            columnHeader.addElement(staticText);
//
//            // define fields
//            JRDesignField field = new JRDesignField();
//            field.setName(alias[i]);
//            field.setValueClass(String.class);
//            design.addField(field);
//
//            // add text fields for displaying fields
//            JRDesignTextField textField = new JRDesignTextField();
//            JRDesignExpression expression = new JRDesignExpression();
//            expression.setText("$F{" + alias[i] + "}");
//            expression.setValueClass(String.class);
//            textField.setExpression(expression);
//            textField.setFontSize(fontSize);
//            textField.setHeight(textHeight);
//            textField.setWidth(textWidth);
//            textField.setX(X * i);
//            textField.setPdfFontName("MHei-Medium");
//            textField.setPdfEmbedded(true);
//            textField.setPdfEncoding("UniCNS-UCS2-H");
////            textField.setTextAlignment(JRBasePrintText.HORIZONTAL_ALIGN_CENTER);
////            textField.setLeftBorder(JRBaseLine.PEN_1_POINT);
////            textField.setTopBorder(JRBaseLine.PEN_1_POINT);
////            textField.setRightBorder(JRBaseLine.PEN_1_POINT);
////            textField.setBottomBorder(JRBaseLine.PEN_1_POINT);
//            detail.addElement(textField);
//        }
//        design.setColumnHeader(columnHeader);
////        design.setDetail(detail);
//
//        return JasperCompileManager.compileReport(design);
//    }
//
//    /**
//     * 利用反射機制，裝拼數據
//     *
//     * @param headers
//     * @param list
//     * @return
//     * @throws Exception
//     */
//    private static List<Object> getBaseList(String[] headers,
//                                            List<String[]> list) throws Exception {
//        List<Object> result = new ArrayList<Object>();
//        int length = headers.length;
//        DynaProperty[] dynaProps = new DynaProperty[length];
//        for (int i = 0; i < length; i++) {
//            dynaProps[i] = new DynaProperty(headers[i], String.class);
//        }
//        BasicDynaClass dynaClass = new BasicDynaClass("first",
//                BasicDynaBean.class, dynaProps);
//        for (Object[] obj : list) {
//            DynaBean employee = dynaClass.newInstance();
//            for (int i = 0; i < length; i++) {
//                employee.set(headers[i], obj[i]);
//            }
//            result.add(employee);
//        }
//        return result;
//    }


}
