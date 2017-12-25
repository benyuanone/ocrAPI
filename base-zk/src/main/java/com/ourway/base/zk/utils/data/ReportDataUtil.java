package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import net.sf.json.JSONNull;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Row;

import java.util.*;

/**
 * <p>方法 PageDataUtil : <p>
 * <p>说明:获取页面配置信息数据类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 22:03
 * </pre>
 */
public class ReportDataUtil {
    /*页面对应的控件获取api方法*/
    public static String PAGE_COMPONENTS = "sysReportApi/listReport";


    public static Map<String, Object> getCurrReport(String pageCA, String reportCode) {
        PublicData data = PublicData.instantce();
        data.setMethod(PAGE_COMPONENTS);
        Map<String, String> ppt = new HashMap<String, String>();
        ppt.put("pageCa", pageCA);
        ppt.put("reportCode", reportCode);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null == mess || mess.getBackCode() != 0)
                return null;
            if (null != mess.getBean()) {
                return (Map<String, Object>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Map<String, Object> getSubReportMap(String dataList, BaseWindow window) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        BaseGrid grid = (BaseGrid) window.getFellowIfAny(dataList);
        if (null == grid)
            return resultMap;
        for (PageControlVO pageControlVO : grid.getControlVOS()) {
            resultMap.put(pageControlVO.getKjLabelid(), I18nUtil.getLabelContent(pageControlVO.getKjLabelid()));
        }
        return resultMap;
    }

    public static List<Map<String, ?>> getSubReportDataList(String dataList, BaseWindow window) {
        List<Map<String, ?>> resultMap = new ArrayList<Map<String, ?>>();
        BaseGrid grid = (BaseGrid) window.getFellowIfAny(dataList);
        if (null == grid)
            return resultMap;
        List<Row> allData = grid.getRows().getChildren();
        for(Row row:allData){
            Map<String,Object> _dtVal = new HashMap<String,Object>();
           List<Component> _objs = row.getChildren();
           for(Component obj:_objs){
               if(obj instanceof BaseTextbox) {
                   BaseTextbox _tx = (BaseTextbox) obj;
                   if (null != _tx.getPgvo())
                       _dtVal.put(_tx.getPgvo().getKjAttribute(), _tx.getValue());
               }
           }
            resultMap.add(_dtVal);
        }

        return resultMap;
    }


    public static void main(String[] args) {
//        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();
//        ReportDataUtil.getControls("/login.do", pageControls);
    }
}
