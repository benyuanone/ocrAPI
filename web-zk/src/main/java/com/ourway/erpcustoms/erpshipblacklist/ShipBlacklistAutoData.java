package com.ourway.erpcustoms.erpshipblacklist;

import com.ourway.base.zk.bandbox.ListBandbox;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.KeyEvent;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by David on 2017-09-14.
 */
public class ShipBlacklistAutoData implements ComponentListinerSer {
    //str的格式： a-b,c-d,e-f
    private void setValueToMainGrid(BaseWindow window, String str, Map<String, Object> dataMap) {
        String[] strs = str.split("\\,");
        String[] eles = null;
        Object obj = null;
        for (String s : strs) {
            eles = s.split("\\-");
            obj = ComponentUtils.doGetObjByLabel(eles[0], dataMap);
            if (!TextUtils.isEmpty(eles[1])) {
                Component component = window.getFellowIfAny("mainTableGrid_" + eles[1]);
                DataBinder.binderToPage(eles[1], obj, component);
            }
        }
    }

    //把查询值set到历史情况中
    private void setValueToHistoryList(BaseWindow window, Map<String, Object> dataMap) {
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(dataMap,"/shipBlacklistApi/historyErpShipBlacklist");
        List<Map<String,Object>> historyList = (List<Map<String,Object>>)responseMessage.getBean();
        Component component = window.getFellowIfAny("dataList1");
        BaseGrid grid = (BaseGrid)component;
        //下面的方法是追加，不是替换。展示的不是预期的数据，所以算了
        window.getGridUtils().displayAppendDataWithTextbox(grid,historyList,window,true);
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        Map<String, Object> selVal = null;
        ListBandbox listBandbox = null;
        try {
            if (null != event && event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                listBandbox = (ListBandbox) keyEvent.getTarget();
                List<Map<String,Object>> objects = listBandbox.getSelVals();
                if (null != objects && objects.size() > 0)
                    selVal = (Map<String, Object>) objects.get(0);
            }
            if (null == selVal)
                return;
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (!TextUtils.isEmpty(_params.get("refTable"))) {
                setValueToMainGrid(window, _params.get("refTable").toString(), selVal);
            }
            if (!TextUtils.isEmpty(_params.get("mainTable"))) {
                Map<String,Object> _map = new HashMap<String,Object>();
                _map.put("erpshipRefOwid",selVal.get("owid"));
                ResponseMessage responseMessage = JsonPostUtils.executeAPI(_map,"/shipBlacklistApi/detailErpShipBlacklist");
                if (!TextUtils.isEmpty(responseMessage) && !((Map)responseMessage.getBean()).isEmpty()) {
                    _map.putAll((Map<String,Object>)responseMessage.getBean());
                    setValueToMainGrid(window, _params.get("mainTable").toString(), _map);
                    //setValueToHistoryList(window, _map);
                } else {
                    binderToPageSingle(window,"event","");
                    binderToPageSingle(window,"deal","");
                    binderToPageSingle(window,"creatorName", ZKSessionUtils.getZkUser().getEmpName());
                    binderToPageSingle(window,"createtime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    binderToPageSingle(window,"stateStr","有效");
                    binderToPageSingle(window,"remark","");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void binderToPageSingle (BaseWindow window, String fromStr, String value){
        Component component = window.getFellowIfAny("mainTableGrid_"+fromStr);
        DataBinder.binderToPage(fromStr, value, component);
    }
}
