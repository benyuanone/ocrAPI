package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 从接口中获取书节点信息
 * Created by jack on 2017/5/28.
 */
public class ComponentInitTreeFromApiSerImpl implements ComponentInitSer {
    public static final String YES = "yes";

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"apiUrl":""}]
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageControlVO.getKjInitData().toString());
        if (null == paramsList || paramsList.size() <= 0) {
            AlterDialog.alert("请传入本接口所需的JSON参数");
            return;
        }
        Map<String, Object> param = paramsList.get(0);
        if (TextUtils.isEmpty(param.get("apiUrl"))) {
            AlterDialog.alert("请传入Api接口");
            return;
        }
        ResponseMessage mess = JsonPostUtils.executeAPI("", param.get("apiUrl").toString());
        if (null == mess || mess.getBackCode() != 0 || null == mess.getBean()) {
            return;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
        List<TreeVO> treeVOList = new ArrayList<TreeVO>();
        for (Map<String, Object> map : list) {
            TreeVO treeVO = JsonUtil.map2Bean(map, TreeVO.class);
            treeVOList.add(treeVO);
        }
        if (null != treeVOList && treeVOList.size() > 0) {
            TreeUtils treeUtils = new TreeUtils();
            treeVOList = TreeUtils.getTrees(treeVOList);
            treeUtils.showMultipleTree(window, null, (BaseTree) component, treeVOList,null);
        }
    }
}
