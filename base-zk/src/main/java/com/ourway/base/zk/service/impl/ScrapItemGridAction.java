package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>ScrapItemGridAction</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2017</dd>
 * <dd>Company:步长科技有限公司</dd>
 * <dd>CreateDate: 2017/7/21</dd>
 * </dl>
 *
 * @author xby
 */
public class ScrapItemGridAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object params = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> removeCs = new ArrayList<Object>();
        if (null != params){
            JSONArray jsonArray = JSONArray.fromObject(params.toString());
            removeCs = JsonUtil.jsonToList(jsonArray);
            if(!AlterDialog.corfirm("确定需要报废选中的数据"))
                return;
            for(Object object:removeCs){
                Map<String,Object> map = (Map<String,Object>)object;
                BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(map.get("gridId").toString());
                baseGrid.removeItems(map.get("url").toString());
            }
//            System.out.println(((Map<String,Object>)removeCs.get(0)).get("gridId"));
        }
    }
}