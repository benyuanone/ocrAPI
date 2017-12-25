package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 新增弹出新界面的方法 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class RemoveItemGridAction implements ComponentListinerSer {
//[{"gridId":"","url":" ","freshTree":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object params = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> removeCs = new ArrayList<Object>();
        if (null != params){
            JSONArray jsonArray = JSONArray.fromObject(params.toString());
            removeCs = JsonUtil.jsonToList(jsonArray);
            if(!AlterDialog.corfirm("确定需要删除选中的数据"))
                return;
            for(Object object:removeCs){
                Map<String,Object> map = (Map<String,Object>)object;
                BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(map.get("gridId").toString());
                baseGrid.removeItems(map.get("url").toString());
                if (!TextUtils.isEmpty(map.get("freshTree"))&&map.get("freshTree").toString().equalsIgnoreCase("1")) {
                    window.loadTreeData();
                }
            }
//            System.out.println(((Map<String,Object>)removeCs.get(0)).get("gridId"));
        }
    }
}
