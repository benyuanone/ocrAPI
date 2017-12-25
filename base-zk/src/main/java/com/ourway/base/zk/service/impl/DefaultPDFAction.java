package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JasperXMLUtils;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import com.ourway.base.zk.utils.data.ReportDataUtil;
import net.sf.ehcache.search.attribute.KeyObjectAttributeExtractor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/*<p>默认PDF导出PDF文件 <p>
*<pre>
*@date 2017-06-02 10:01
</pre>
*/
public class DefaultPDFAction implements ComponentListinerSer {
    //
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        String pageCa = window.getPageCA();
        String reportName = pgvo.getKjAttribute();
        //根据pageca和reportName获取报表配置
        Map<String, Object> reportMap = ReportDataUtil.getCurrReport(pageCa, reportName);
        if (null == reportMap) {
            AlterDialog.alert(ERCode.JASPER_NOT_EXIST);
            return;
        }
        //所有的内容
        Map<String, Object> allPpt = window.getPpt();
        //fang
        if (!TextUtils.isEmpty(reportMap.get("reportTitle")))
            allPpt.put("title", I18nUtil.getLabelContent(reportMap.get("reportTitle").toString()));
        else
            allPpt.put("title", I18nUtil.getLabelContent(ERCode.JASPER_DEFAULT_TITLE));
        //主表的pageca获取多语言标签
        Map<String, Object> labelMap = PageDataUtil.listAllComponentLabelId(pageCa);
        Set<String> labelSet = labelMap.keySet();
        for (String s : labelSet) {
            allPpt.put(s, labelMap.get(s));
        }

        //判断是否有子报表
        if (null != reportMap.get("subreportList")) {
            String subReportName = "";
            List<Map<String, Object>> subreports = (List<Map<String, Object>>) reportMap.get("subreportList");
            for (Map<String, Object> subreport : subreports) {
                subReportName = subreport.get("reportCode").toString();
                try {
                    System.out.println(subReportName);
                    JasperDesign subJdesign = JRXmlLoader.load(new File(JasperXMLUtils.REPORT_ROOT_PATH + subreport.get("jrxmlPath").toString()));
                    JasperReport subJreport = JasperCompileManager.compileReport(subJdesign);
                    allPpt.put(subReportName + "Report", subJreport);
                    Map<String, Object> subMap = ReportDataUtil.getSubReportMap(subReportName.replaceAll(reportName, ""), window);
                    if (TextUtils.isEmpty(subreport.get("reportName")))
                        subMap.put("subtitle", I18nUtil.getLabelContent(ERCode.JASPER_DEFAULT_TITLE));
                    else
                        subMap.put("subtitle", I18nUtil.getLabelContent(subreport.get("reportName").toString()));
                    List<Map<String, ?>> subMapData = ReportDataUtil.getSubReportDataList(subReportName.replaceAll(reportName, ""), window);
                    allPpt.put(subReportName + "Map", subMap);
                    allPpt.put(subReportName + "Obj", new JRMapCollectionDataSource(subMapData));
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
        try {
            //开始生产主报表
            List<Map<String, ?>> _dataList = new ArrayList<Map<String, ?>>();
            Map<String, Object> _map = new HashMap<String, Object>();
            _map.put("test", "test");
            _dataList.add(_map);
            JasperDesign jDesign = JRXmlLoader.load(new File(JasperXMLUtils.REPORT_ROOT_PATH + reportMap.get("jrxmlPath").toString()));
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, allPpt, new JRMapCollectionDataSource(_dataList));
            JRExporter jExporter = new JRPdfExporter();
            jExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, JasperXMLUtils.REPORT_ROOT_PATH + reportName + ".pdf");
            jExporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
            jExporter.exportReport();
            FileInputStream inputStream;
            try {
                File dosfile = new File(JasperXMLUtils.REPORT_ROOT_PATH + reportName + ".pdf");
                if (dosfile.exists()) {
                    inputStream = new FileInputStream(dosfile);
                    Filedownload.save(inputStream, "application/pdf", reportName + ".pdf");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
