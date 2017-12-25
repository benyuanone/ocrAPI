package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treecell;

import java.util.List;
import java.util.Map;

/**
 * <p>方法: 树保存方法，用于新增子节点，同级节点</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class SaveTreeAction implements ComponentListinerSer {
    //[{"url":"保存接口"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
//        try {
        //获取所有主表中当个控件的值
        Map<String, Object> ppt = window.bindAll2Ppt(true);
        List<Map<String, Object>> paramsList = JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("url"))) {
            AlterDialog.alert("请定义需要提交保存的API接口");
            return;
        }
        //调用接口进行保存
        ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
        if (null == message) {
            AlterDialog.alert("操作失败");
            return;
        }
        if (message.getBackCode() == 0) {
            if (null == message.getBean()) {
                AlterDialog.alert("未返回值");
            } else {
                Map<String, Object> _ppt = (Map<String, Object>) message.getBean();
                TreeVO treeVO = BeanUtil.map2Obj(_ppt, TreeVO.class);
                switch (window.getBaseTreeType()) {
                    case 1:
                        TreeUtils.doCreateSubTreeItem(treeVO, window.getBaseTreeItem(), window.getBaseTree(), window);
                        break;
                    case 2:
                        TreeUtils.doCreateSameLevelTreeItem(treeVO, window.getBaseTreeItem(), window.getBaseTree(), window);
                        break;
                    case 3:
                        window.getBaseTreeItem().setValue(treeVO);
                        Treecell cell = (Treecell) window.getBaseTreeItem().getFirstChild().getFirstChild();
                        cell.setLabel(treeVO.getName());
                        break;
                }
                window.setBaseTreeType(3);
                window.setWindowType(2);
                ComponentUtils.setDisableEdit(window);
                ComponentUtils.doCheckButtonStatus(window);
                PageUtils.resetGridComponent(window);
            }
        } else {
            AlterDialog.alert(message.getErrorMess());
            return;
        }

    }
}
