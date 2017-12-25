package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

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
public class AddNewNextAction implements ComponentListinerSer {
//[{"pageCa":"页面pageCa","pageType":"1表示新增，2表示查看 3 表示修改状态 4表示列表","gridId":"返回的dataListid","title":"","windowCss":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        window = window.getTopWindow();
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
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
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义gridId");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());

            //根据pageca拿到页面的相关信息
            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在模板中定义指定ca的页面");
                return;
            }
            if (vo.getPageCustomer() == 0)
                url = vo.getPageTemplatePath();
            else
                url = vo.getPageCa();
            //处理url中带？的参数问题
            Map<String, Object> urlParams = GridUtils.getParamsFromUrl(url);
            if (null != urlParams) {
                Set<String> keys = urlParams.keySet();
                for (String key : keys) {
                    _params.put(key, urlParams.get(key));
                }
                url = url.split("\\?")[0];
            }

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
                _win.setBaseGrid(baseGrid);
//                List<String> tabList = ZkUtils.doHandleTabid(window,tabId);
//                _win.setTabId(tabList);
                _win.setTabId(tabId+"_window");
                _win.setTopWindow(window);
                _win.setParentPpt(_params);
//                _win.setClosable(true);
//                _win.doModal();
                if (_win.isClosePage()) {
                    if ("1".equalsIgnoreCase(_params.get("pageType").toString())) {
                        window.getGridUtils().addNewRow(_win.getPpt(), baseGrid, window);
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
