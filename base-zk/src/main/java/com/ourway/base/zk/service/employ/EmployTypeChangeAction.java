package com.ourway.base.zk.service.employ;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
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
public class EmployTypeChangeAction implements ComponentListinerSer {
    //[{"pageType":""}]
    private static final String URL = "/sysRolesApi/listRolesByRoleType";

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        if (event.getTarget() instanceof BaseListbox) {
            BaseListbox empType = (BaseListbox) event.getTarget();
            if (!TextUtils.isEmpty(empType.getSelectedItem().getValue())) {
                String empTypeVal = empType.getSelectedItem().getValue().toString();
                List<FilterModel> models = new ArrayList<FilterModel>();
                List<Object> _vals = new ArrayList<Object>(1);
                _vals.add(Integer.parseInt(empTypeVal));
                FilterModel filterModel = FilterModel.instance("roleType", FilterModel.EQUALS, _vals);
                models.add(filterModel);
                ResponseMessage responseMessage = JsonPostUtils.executeAPI(models, URL);
                if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {
                    List<Map<String, Object>> roles = (List<Map<String, Object>>) responseMessage.getBean();
                    BaseGrid grid = (BaseGrid) window.getFellowIfAny("dataList1");
                    List<Map<String, Object>> _inDatas = window.getGridUtils().getAllDatas(grid, false);
                    List<Map<String, Object>> _datas = getNewPpts(roles, _inDatas);

                    grid.addNewRows(_datas);
                }


            }

        }


    }

    //新老数据进行对比
    List<Map<String, Object>> getNewPpts(List<Map<String, Object>> newList, List<Map<String, Object>> oldList) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> _oldVal = new HashMap<String, Object>();
        String _key = "";
        if (null != oldList && oldList.size() > 0) {
            for (Map<String, Object> _val : oldList) {
                if (_val.get("roleRefOwid") instanceof Map) {
                    Map<String, Object> _map = (Map<String, Object>) _val.get("roleRefOwid");
                    _oldVal.put(_map.get("owid").toString(), 1);
                } else if (!TextUtils.isEmpty(_val.get("roleRefOwid")))
                    _oldVal.put(_val.get("roleRefOwid").toString(), 1);
            }
        }
        if (null != newList && newList.size() > 0) {
            for (Map<String, Object> map : newList) {
                _key = map.get("owid").toString();
                if (null != _oldVal.get(_key))
                    continue;
                Map<String, Object> _data = new HashMap<String, Object>();
                _data.put(GridUtils.SUBEDIT, 3);//表示不经过编辑，直接插入数据到表格中，保存的时候需要保存这批数据
                _data.put("ourwaySysRoles", map);
                _data.put("roleRefOwid", map.get("owid"));
                result.add(_data);

            }
        }

        return result;

    }


}
