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
 * <p>方法: 点击grid上面的修改，自动把grid中的owid作为参数传递到新的页面 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/7/5 13:12  </li>
 * </ul>
 */
public class UpdatePageFromGridAction implements ComponentListinerSer {
    //[{"gridId":"dataList1","pageCa":"页面路径","pageType":"pageType","gridId":"gridId"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
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
            List<Map<String, Object>> objs = baseGrid.getSelectRowsData();
            String rowId = baseGrid.getSelectRows().get(0).getId();
            if (null == objs || objs.size() <= 0) {
                AlterDialog.alert("请选择需要操作的记录");
                return;
            }
            if (objs.size() > 1) {
                AlterDialog.alert("一次只能选择一条进行操作");
                return;
            }

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

            Map<String, Object> rowData = (Map<String, Object>) objs.get(0);
            if (TextUtils.isEmpty(rowData.get("owid"))) {
                AlterDialog.alert("不包含数据的主键，不能操作");
                return;
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

            _params.put("owid", rowData.get("owid"));
            _params.put("ppt", rowData);
            //处理url中带？的参数问题
            Map<String, Object> urlParams = GridUtils.getParamsFromUrl(url);
            if (null != urlParams) {
                Set<String> keys = urlParams.keySet();
                for (String key : keys) {
                    _params.put(key, urlParams.get(key));
                }
                url = url.split("\\?")[0];
            }
            Component winEdit = Executions.createComponents(url, null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss")))
                    _win.setStyle(_params.get("windowCss").toString());
                String tabId = root.openNewTab(_win, _params.get("title").toString());
                _win.setBaseGrid(baseGrid);
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(window);
//                List<String> tabList = ZkUtils.doHandleTabid(window, tabId);
//                _win.setTabId(tabList);
                _win.setSelRowId(rowId);
//                _win.setClosable(true);
//                _win.doModal();
                System.out.println("=====rowId====" + rowId);
                if (_win.isClosePage()) {
                    if ("1".equalsIgnoreCase(_params.get("pageType").toString())) {
                        GridUtils.instance().addNewRow(_win.getPpt(), baseGrid, window);
                    } else {
                        window.getGridUtils().refreshRow(_win.getPpt(), baseGrid, _win.getSelRowId());
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
