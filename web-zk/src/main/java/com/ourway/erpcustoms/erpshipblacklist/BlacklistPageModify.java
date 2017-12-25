package com.ourway.erpcustoms.erpshipblacklist;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-14.
 */
public class BlacklistPageModify implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {

        Map<String,Object> ppt = window.getPpt();
        if (!TextUtils.isEmpty(ppt.get("owid"))) {
            List<String> paramList = new ArrayList<String>();
            if ((Integer) ppt.get("state") == 0) {
                //若状态为有效，则不允许加入黑名单
                paramList.add("1_revokeBtn2");
            }
            if ((Integer) ppt.get("state") == 1) {
                //若状态为有效，则不允许加入黑名单
                paramList.add("1_addToBlacklist");
            }
            setEditable(window,paramList);
        }
    }

    //设置属性公共方法
    private void setEditable(BaseWindow window, List<String> paramList) {
        paramList.add("1_addBtn");
        paramList.add("1_saveBtn");
        paramList.add("1_removeBtn");
        paramList.add("mainTableGrid_event");
        paramList.add("mainTableGrid_deal");
        paramList.add("mainTableGrid_remark");
        ComponentUtils.setEditState(window,paramList,false);
    }
}
