package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.GridRowDbclickListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZkUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Row;
import org.zkoss.zul.Treeitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4.
 */
public class GridDefaultDbckImpl implements GridRowDbclickListinerSer {
//[{"pageCa":"","pageType":"","gridId":"","title":"","windowCss":"","tree":""}]
    @Override
    public void doAction(BaseWindow window, BaseGrid grid, Row row) {
        Object windowParams = grid.getLayoutVO().getControlDbclickEventParam();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        try {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }
            if (TextUtils.isEmpty(_params.get("pageType"))) {
                AlterDialog.alert("请定义页面类型");
                return;
            }
            //根据pageca拿到页面的相关信息
            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在grid的双击事件参数中定义新页面的pageca");
                return;
            }
            if (vo.getPageCustomer() == 0)
                url = vo.getPageTemplatePath();
            else
                url = vo.getPageCa();
            if (null == grid.getDbData() || TextUtils.isEmpty(grid.getDbData().get("owid"))) {
                AlterDialog.alert("不包含OWID,不能获取指定关键字值");
                return;
            }
            if (!TextUtils.isEmpty(_params.get("tree"))) {
                BaseTree tree = (BaseTree) window.getFellowIfAny(_params.get("tree").toString());
                if (null != tree.getSelectedItem()) {
                    Treeitem treeitem = tree.getSelectedItem();
                    _params.put("tree", treeitem.getValue());
                } else {
                    AlterDialog.alert(I18nUtil.getLabelContent(ERCode.TREE_NO_VALUE));
                    return;
                }

            }
            _params.put("owid", grid.getDbData().get("owid"));
            _params.put("ppt", grid.getDbData());
            Component comp = Path.getComponent("/mainWin");
            MainAction root = null;
            if (comp instanceof MainAction) {
                root = (MainAction) comp;
            }
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }
            Component winEdit = Executions.createComponents(url, null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss")))
                    _win.setStyle(_params.get("windowCss").toString());
                String tabId = root.openNewTab(_win, _params.get("title").toString());
                _win.setBaseGrid(grid);
//                List<String> tabList = ZkUtils.doHandleTabid(window, tabId);
//                _win.setTabId(tabList);
                _win.setTabId(tabId+"_window");
                _win.setTopWindow(window);
                if(null!=row)
                    _win.setSelRowId(row.getId());
                if (_win.isClosePage()) {
                    if ("1".equalsIgnoreCase(_params.get("pageType").toString())) {
                        window.getGridUtils().addNewRow(_win.getPpt(), grid, window);
                    } else {
                        grid.refreshRow(_win.getPpt(), row);
                    }
                    root.closeTabWin(_win);
                }
                if (_win.isDetach()) {
                    root.closeTabWin(_win);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
