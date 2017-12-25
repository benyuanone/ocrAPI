package com.ourway.test;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseChosenbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.AlterDialog;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Menuitem;

import java.io.*;
import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ReportTestAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ReportTestAction.class);

    Menuitem defaultItem;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        defaultItem = (Menuitem) getFellowIfAny("defaultItem");
        defaultItem.addEventListener(Events.ON_CLICK, this);
        BaseChosenbox chosenbox = (BaseChosenbox) getFellowIfAny("chosenbox");
        chosenbox.setStyle("width:200px;height:22px;");
        chosenbox.setEmptyMessage("---請選擇---");

//        chosenbox.setCreatable(true);
//        chosenbox.setCreateMessage("test-{0}");
        ListModelList<String> modelList = new ListModelList<String>();
        modelList.add("test-1");
        modelList.add("test-2");
        modelList.add("test-3");
        chosenbox.setModel(modelList);

        chosenbox.setSelectedObjects(modelList);

        BaseButton submit = (BaseButton) getFellowIfAny("submit");
        submit.addEventListener(Events.ON_CLICK, this);

    }

    public void test() {
        BaseChosenbox chosenbox = (BaseChosenbox) getFellowIfAny("chosenbox");
        List<Map<String,Object>> datas = chosenbox.getSelectedObjs();

        AlterDialog.alert(datas.size()+"");

        chosenbox.clearSelection();

    }

    public void genReport() {
        String mainXml = "D:\\files\\report\\mainReport.jrxml";


    }

    public static String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }


    @Override
    public void onEvent(Event event) throws Exception {
        if (event.getTarget().getId().equals("submit")) {
            test();
        }
        if (event.getTarget().getId().equals("defaultItem")) {
            AlterDialog.alert("=====");


            String fileName1 = "D:\\files\\report\\report4.jrxml";
            String destName1 = "D:\\files\\report\\report4.jasper";
            String jasperPrint = "D:\\files\\report\\report4.jrprint";
            String pdfPath = "D:\\files\\report\\report4.pdf";

            String subReport1 = "D:\\files\\report\\report4_subreport1.jrxml";
            String subReport2 = "D:\\files\\report\\report4_subreport2.jrxml";


            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("parameter1", "test-1");
            parameters.put("parameter2", "test-2");
            parameters.put("title", "这个是一个测试报表");
            //子报表数据
            Map<Object, Object> subReport = new HashMap<Object, Object>();
            subReport.put("header1", "zibiaotou-1");
            subReport.put("header2", "zibiaotou-2");
            subReport.put("subtitle", "子报表标题");
            Map<Object, Object> param2 = new HashMap<Object, Object>();
            param2.put("subHeader1", "nl-1");
            param2.put("subHeader2", "no-2");
            param2.put("subtitle", "子报表标题");


            List<Map<String, ?>> dataList1 = new ArrayList<Map<String, ?>>();
            Map<String, Object> subParams = new HashMap<String, Object>();
            subParams.put("header1Val", "bt-1");
            subParams.put("header2Val", "bt-2");
            dataList1.add(subParams);
            subParams = new HashMap<String, Object>();
            subParams.put("header1Val", "1-1");
            subParams.put("header2Val", "1-2");
            dataList1.add(subParams);
            subParams = new HashMap<String, Object>();
            subParams.put("header1Val", "2-1");
            subParams.put("header2Val", "2-2");
            dataList1.add(subParams);
            subParams = new HashMap<String, Object>();
            subParams.put("header1Val", "3-1");
            subParams.put("header2Val", "3-2");
            dataList1.add(subParams);
            parameters.put("dataList1", new JRMapCollectionDataSource(dataList1));

            parameters.put("subMap", subReport);
            parameters.put("subMap2", param2);

            List<Map<String, ?>> dataList2 = new ArrayList<Map<String, ?>>();
            subParams = new HashMap<String, Object>();
            subParams.put("subHeader1Val", "1111");
            subParams.put("subHeader2Val", "2222");
            dataList2.add(subParams);
            subParams = new HashMap<String, Object>();
            subParams.put("subHeader1Val", "3333");
            subParams.put("subHeader2Val", "4444");
            dataList2.add(subParams);
            parameters.put("dataList2", new JRMapCollectionDataSource(dataList2));
            // compile master report
            JasperCompileManager.compileReportToFile(fileName1, destName1);

            JasperDesign jDesign2 = JRXmlLoader.load(new File(subReport1));
            JasperReport jReport2 = JasperCompileManager.compileReport(jDesign2);
            JasperDesign jDesign3 = JRXmlLoader.load(new File(subReport2));
            JasperReport jReport3 = JasperCompileManager.compileReport(jDesign3);

            JasperDesign jDesign = JRXmlLoader.load(new File(fileName1));
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            parameters.put("subReportObject", jReport2);
            parameters.put("subReportObject1", jReport3);
            // fill the report
//            JasperFillManager.fillReportToFile(destName1, parameters,new JRMapCollectionDataSource(subReport));
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, new JRMapCollectionDataSource(dataList1));
//            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters);

            //export with pdf
            JRExporter jExporter = new JRPdfExporter();
            jExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pdfPath);
            jExporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
            jExporter.exportReport();

//            //export with xls
//            JRXlsExporter jXlsExporter = new JRXlsExporter();
//            jXlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, System.currentTimeMillis()+".xls");
//            jXlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
//            jXlsExporter.exportReport();

            // export to pdf
//            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
//
//            // see filled report
//            JasperViewer.viewReport(jasperPrint, false, true);


//           new net.sf.jasperreports.engine.data.JRMapCollectionDataSource(((Map<String,List>)$P{subMap}).get("dataList1"))
        }

    }

}
