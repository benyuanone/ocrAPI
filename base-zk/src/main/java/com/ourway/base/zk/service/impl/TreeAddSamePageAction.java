package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.*;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.template.DetailTreeNodeAction;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.treeutils.NodeUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 在同一个页面中完成节点的新增和修改 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class TreeAddSamePageAction implements TreeListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
//        if (TextUtils.isEmpty(_params.get("url"))) {
//            AlterDialog.alert("请定义树单节点详情");
//            return;
//        }

        TreeVO treeVO = new TreeVO();
        switch (pgvo.getTreeType()) {
            case 1://新增子节点
                if (null == treeitem || null == treeitem.getValue()) {
                    //表示根节点
                    treeVO.setFid(-1);
                    treeVO.setPath("-1/");
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem));
                    treeVO.setCc(1);
                } else {
                    TreeVO _treeVO = treeitem.getValue();
                    treeVO.setOwid(_treeVO.getOwid());
                    treeVO.setFid(_treeVO.getOwid());
                    treeVO.setPath(_treeVO.getPath() + "/");
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem));
                    treeVO.setCc(_treeVO.getCc() + 1);
                }
                break;
            case 2://新增同级节点
                if (null == treeitem || null == treeitem.getValue()) {
                    //表示根节点
                    treeVO.setFid(-1);
                    treeVO.setPath("-1/");
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem));
                    treeVO.setCc(1);
                } else {
                    TreeVO _treeVO = treeitem.getValue();
                    treeVO.setOwid(_treeVO.getOwid());
                    treeVO.setFid(_treeVO.getOwid());
                    treeVO.setPath(_treeVO.getPath() + "/");
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem.getParentItem()));
                    treeVO.setCc(_treeVO.getCc());
                }
                break;
            case 3://修改
                TreeVO _treeVO = treeitem.getValue();
                treeVO = _treeVO;
                break;
        }

        try {
            window.setBaseTree(tree);
            window.setBaseTreeItem(treeitem);
            window.setBaseTreeType(pgvo.getTreeType());

            if (!TextUtils.isEmpty(treeVO.getOwid())) {
                Map<String, Object> params = JsonUtil.jsonToMap(JsonUtil.toJson(treeVO));
                if (!TextUtils.isEmpty(_params.get("url").toString())) {
                    ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, _params.get("url").toString());
                    if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {
                        Map<String, Object> _ppt = (Map<String, Object>) responseMessage.getBean();
                        _ppt.put("fid", treeVO.getFid());
                        _ppt.put("path", treeVO.getPath());
                        _ppt.put("px", treeVO.getPx());
                        _ppt.put("cc", treeVO.getCc());
                        window.setPpt(_ppt);
                    } else {
                        Map<String, Object> _ppt = new HashMap<String, Object>();
                        _ppt.put("fid", treeVO.getFid());
                        _ppt.put("path", treeVO.getPath());
                        _ppt.put("px", treeVO.getPx());
                        _ppt.put("cc", treeVO.getCc());
                        window.setPpt(_ppt);
                    }
                } else {
                    Map<String, Object> _ppt = new HashMap<String, Object>();
                    _ppt.put("fid", treeVO.getFid());
                    _ppt.put("path", treeVO.getPath());
                    _ppt.put("px", treeVO.getPx());
                    _ppt.put("cc", treeVO.getCc());
                    window.setPpt(_ppt);
                }
            } else {
                Map<String, Object> _ppt = new HashMap<String, Object>();
                _ppt.put("fid", treeVO.getFid());
                _ppt.put("path", treeVO.getPath());
                _ppt.put("px", treeVO.getPx());
                _ppt.put("cc", treeVO.getCc());
                window.setPpt(_ppt);
            }
            ComponentUtils.setEditable(window);
            window.setWindowType(1);
            ComponentUtils.doCheckButtonStatus(window);
            PageUtils.resetGridComponent(window);
            window.bind2Page();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
