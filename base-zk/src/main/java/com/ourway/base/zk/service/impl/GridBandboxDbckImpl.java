package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseBandbox;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.GridRowDbclickListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4.
 */
public class GridBandboxDbckImpl implements GridRowDbclickListinerSer {

    @Override
    public void doAction(BaseWindow window, BaseGrid grid, Row row) {
        try {
            if (window.getParent().getParent() instanceof BaseBandbox) {
                BaseBandbox bandbox = (BaseBandbox) window.getParent().getParent();
                List< Map<String, Object>> selObjs = new ArrayList< Map<String, Object>>(1);
                selObjs.add((Map<String,Object>)row.getValue());
                bandbox.setSelVals(selObjs);
                bandbox.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
