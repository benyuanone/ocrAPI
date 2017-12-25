package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

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
public class TreeClickQueryPathAction implements TreeListinerSer {
    //[{"gridId":"dataList","conditionGrid":"conditionGrid","property":"path","queryProperty":"classPre"}]
    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        int filterType = 0;
        if (!TextUtils.isEmpty(_params.get("filterType")))
            filterType = Integer.parseInt(_params.get("filterType").toString());
        if (TextUtils.isEmpty(_params.get("gridId"))) {
            AlterDialog.alert("请定义加载的dataList");
            return;
        }
//        if (TextUtils.isEmpty(_params.get("property"))) {
//            AlterDialog.alert("请定义传递的数据属性");
//            return;
//        }
//        if (TextUtils.isEmpty(_params.get("queryProperty"))) {
//            AlterDialog.alert("请定义查询的属性名");
//            return;
//        }

        List<FilterModel> models = new ArrayList<FilterModel>();
        BaseGrid grid = null;
        if (null != window.getFellowIfAny(_params.get("gridId").toString())) {
            grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (!TextUtils.isEmpty(_params.get("conditionGrid"))) {
                models = window.bind2Filter(_params.get("conditionGrid").toString());
            }
        } else
            return;
        TreeVO treeVO = new TreeVO();
        if (null != treeitem.getValue()) {
            treeVO = treeitem.getValue();
            Object _obj = null;
            try {
                _obj = BeanUtil.getBeanProperty(treeVO, tree.getValProperty());
            } catch (Exception e) {

            }
            List<Object> datas = new ArrayList<Object>(1);
            datas.add(_obj);
            FilterModel model = FilterModel.instance(tree.getProperty(), Integer.parseInt(tree.getFilterType()), datas);
            models.add(model);
        }
        grid.filter(models);
        grid.display();

    }

}
