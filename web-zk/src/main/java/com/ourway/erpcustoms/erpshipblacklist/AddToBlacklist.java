package com.ourway.erpcustoms.erpshipblacklist;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.ComponentUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-13.
 */
public class AddToBlacklist implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        //设置可编辑/不可编辑状态
        List<String> paramList = new ArrayList<String>();
        paramList.add("1_addBtn");
        paramList.add("1_saveBtn");
        paramList.add("1_removeBtn");
        paramList.add("mainTableGrid_event");
        paramList.add("mainTableGrid_deal");
        paramList.add("mainTableGrid_remark");
        ComponentUtils.setEditState(window, paramList, false);
        paramList = new ArrayList<String>();
        paramList.add("1_addToBlacklist");
        paramList.add("1_revokeBtn2");
        ComponentUtils.setEditState(window, paramList, true);
        //设置状态为“有效”
        Map<String, Object> dataMap = window.getPpt();
        dataMap.put("addToBlacklistFlag", true);
        ShipBlacklistAutoData.binderToPageSingle(window,"event","");
        ShipBlacklistAutoData.binderToPageSingle(window,"deal","");
        ShipBlacklistAutoData.binderToPageSingle(window,"remark","");
    }
}
