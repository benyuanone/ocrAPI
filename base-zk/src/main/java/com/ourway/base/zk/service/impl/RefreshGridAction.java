package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 刷新表格</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class RefreshGridAction implements ComponentListinerSer {
//[{"gridId":"dataList","conditionGrid":"conditionGrid,tree"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            //"gridId"指定表格
            //获取所有主表中当个控件的值
            Map<String, Object> ppt = window.bindAll2Ppt(false);
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (null == grid) {
                AlterDialog.alert("刷新对象不存在");
                return;
            }

            List<FilterModel> models = new ArrayList<FilterModel>();
            if (!TextUtils.isEmpty(_params.get("conditionGrid")))
                models = window.bind2Filter(_params.get("conditionGrid").toString());
            if (!TextUtils.isEmpty(_params.get("tree"))){
                BaseTree tree = (BaseTree) window.getFellowIfAny(_params.get("tree").toString());
                if(null!=tree.doComposeFilter())
                    models.add(tree.doComposeFilter());
            }
            grid.filter(models);
            grid.display();
            if(!TextUtils.isEmpty(_params.get("freshTree"))&&_params.get("freshTree").toString().equalsIgnoreCase("1")){
                window.loadTreeData();
            }
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.GRID_REFRESH_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
