package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseComboitem;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 用API进行导入</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class ImportInnerByAPIAction implements ComponentListinerSer {
    //[{"gridId":"对象表格","apiUrl":"API路径","key":"重复的key"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (null == grid) {
                AlterDialog.alert("刷新对象不存在");
                return;
            }
            if (TextUtils.isEmpty(_params.get("apiUrl"))) {
                AlterDialog.alert("请输入api路径");
                return;
            }
            if (TextUtils.isEmpty(_params.get("key"))) {
                AlterDialog.alert("请输入判断重复的key");
                return;
            }
            String key = _params.get("key").toString();
            ResponseMessage mess = JsonPostUtils.executeAPI("", _params.get("apiUrl").toString());
            if (null != mess && mess.getBackCode() == 0) {
                if (mess.getBean() instanceof ArrayList) {
                    List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
                    if(null==list||list.size()<=0)
                        return;
                    Rows rows = grid.getRows();
                    if(null==rows){
                        rows = new Rows();
                        rows.setParent(grid);
                    }

                    List<Row> rowList = rows.getChildren();
                    boolean flag = false;
                    for (Map<String, Object> map : list) {
                        flag = true;
                        if(TextUtils.isEmpty(map.get(key)))
                            continue;
                        if (null != rowList && rowList.size() > 0) {
                            for (Row row : rowList) {
                                 Map<String,Object> _map = row.getValue();
                                if(TextUtils.isEmpty(_map.get(key)))
                                    continue;
                                 if(map.get(key).toString().equalsIgnoreCase(_map.get(key).toString())){
                                     flag = false;
                                     break;
                                 }
                            }
                        }else{
                           flag = true;
                        }
                        if(flag){
                            result.add(map);
                        }
                    }
                    window.getGridUtils().displayAppendDataWithTextbox(grid,result,window,true);
                } else {
                    AlterDialog.alert("必须是返回列表的接口");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
