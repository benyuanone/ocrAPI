package com.ourway.erpsystem.utils;

import com.ourway.base.zk.bandbox.ListBandbox;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZkUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>方法: 新增弹出新界面的方法 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class ErpTechniqueHeartTypeBandBox implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {

        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        BaseListbox listbox = (BaseListbox)window.getFellowIfAny("mainTableGrid_heatTyep");
        listbox.setDisabled(true);
        String url = "";
        try {
            ListBandbox bandbox = (ListBandbox)event.getTarget();
            List<Map<String,Object>> selVaals = bandbox.getSelVals();
            // 判断选中的值进行操作
            for(Map<String,Object> _map :selVaals){
                if(_map.get("indexno").equals("3")){
                    listbox.setDisabled(false);
                }else{
                    listbox.setDisabled(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
