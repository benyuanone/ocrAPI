package com.ourway.erpcustoms.erpshipblacklist;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-18.
 */
public class BlacklistModifyInit implements ComponentInitSer {

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        Map<String,Object> dataMap = window.getPpt();
        List<String> paramList = new ArrayList<String>();
        List<String> permitParamList = new ArrayList<String>();
        if (!TextUtils.isEmpty(dataMap.get("state"))) {
            if ((Integer)dataMap.get("state") == 0) {
                paramList.add("1_revokeBtn2");
            }
            if ((Integer)dataMap.get("state") == 1) {
                paramList.add("1_addToBlacklist");
                //如果是撤销，则不允许修改
                permitParamList.add("1_updateBtn");
            }
        } else {
            //如果没有state，则默认为0
            window.getPpt().put("state",0);
        }
        //放开删除按钮
        paramList.add("1_removeBtn");
        ComponentUtils.setEditState(window, paramList, false);
        ComponentUtils.setEditState(window, permitParamList, true);
    }
}
