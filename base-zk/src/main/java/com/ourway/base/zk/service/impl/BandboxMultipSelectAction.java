package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.bandbox.ListBandbox;
import com.ourway.base.zk.component.BaseBandbox;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
*<p>方法: bandbox 中多选 </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author JackZhou </li>
*<li>@date 2017/5/24 22:31  </li>
*</ul>
*/
public class BandboxMultipSelectAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event,PageControlVO pgvo) {
        //[{"gridId":"dataList","property":"需要显示的字段名"}]
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if(TextUtils.isEmpty(windowParams)){
            AlterDialog.alert("请输入查询配置参数");
            return;
        }
        List<Map<String,Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params =paramsList.get(0);
        if(TextUtils.isEmpty(_params.get("gridId"))){
            AlterDialog.alert("请输入需要查询的Grid");
            return;
        }
        if (null != window.getFellowIfAny(_params.get("gridId").toString())) {
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            List<Map<String,Object>> selObj = grid.getSelectRowsData();

            BaseBandbox bandbox = doGetParentListbox(window);
            String bindStr = "";
            for (Map<String,Object> map:selObj) {
                if (!"".equals(bindStr)) {
                    bindStr += ",";
                }
                bindStr += map.get(_params.get("property").toString());
            }
            DataBinder.binderToPage(_params.get("property").toString(), bindStr, bandbox);
            bandbox.setSelVals(selObj);
            bandbox.close();
        }
    }

    private BaseBandbox doGetParentListbox(Component component){
        if(component.getParent() instanceof BaseBandbox)
            return (BaseBandbox)component.getParent();
        else
            return doGetParentListbox(component.getParent());
    }
}
