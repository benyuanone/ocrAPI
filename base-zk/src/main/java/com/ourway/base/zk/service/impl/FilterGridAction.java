package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
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
public class FilterGridAction implements ComponentListinerSer {
    //[{"gridId":"","conditionGrid":"","tree":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入查询配置参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("gridId"))) {
            AlterDialog.alert("请输入需要查询的Grid");
            return;
        }
        if (TextUtils.isEmpty(_params.get("conditionGrid"))) {
            AlterDialog.alert("请输入需要查询条件的Grid编号");
            return;
        }
        List<FilterModel> models = new ArrayList<FilterModel>();
        BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
        if (null != window.getFellowIfAny(_params.get("gridId").toString())) {
            //组织查询数据
            models = window.bind2Filter(_params.get("conditionGrid").toString());
        }
        if (!TextUtils.isEmpty(_params.get("tree"))) {
            //表示需要树查询
            BaseTree tree = (BaseTree) window.getFellowIfAny(_params.get("tree").toString());
            if (null != tree) {
                FilterModel _treeModel = doTreeModel(tree);
                if(null!=_treeModel)
                    models.add(_treeModel);
            }
        }
        //查询
        grid.filter(models);
        grid.display();
    }

    private FilterModel doTreeModel(BaseTree tree) {
        if (null != tree) {
            Treeitem selItem = tree.getSelectedItem();
            TreeVO treeVO = new TreeVO();
            Object _obj = null;
            if (null == selItem) {
               return null;
            } else {
                treeVO = selItem.getValue();
            }
            try {
                _obj = BeanUtil.getBeanProperty(treeVO, tree.getValProperty());
            } catch (Exception e) {

            }
            if (null != _obj) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(_obj);
                FilterModel model = FilterModel.instance(tree.getProperty(), Integer.parseInt(tree.getFilterType()), datas);
                return model;
            }
        }
        return null;
    }
}
