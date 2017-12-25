package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class APIPageInitWithTreeSer implements PageInitSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args && !TextUtils.isEmpty(args.get("ppt"));
        if (TextUtils.isEmpty(args.get("tree"))) {
            AlterDialog.alert("未定义选中树节点");
            return;
        }
        if (null != args.get("tree")) {
            TreeVO treeVO = (TreeVO) args.get("tree");
            Map<String, Object> pptMap = new HashMap<String, Object>();
            pptMap.put("tree", treeVO.toMap());
            window.setPpt(pptMap);
        }

        if (!flag)
            return;

        if (TextUtils.isEmpty(pageVO.getPageParams())) {
            AlterDialog.alert("未定义apiUrl调用接口");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageVO.getPageParams());
        Map<String, Object> _params = paramsList.get(0);
        Map<String, Object> _rowMap = (Map<String, Object>) args.get("ppt");

        Map<String, Object> params = new HashMap<String, Object>(1);
        if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
            params.put("owid", _rowMap.get("owid"));
        } else {
            params.put("owid", "");
        }
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
        if (TextUtils.isEmpty(responseMessage))
            responseMessage = null;
        if (null == responseMessage || responseMessage.getBackCode() != 0) {
            AlterDialog.alert("不存在详细信息列表");
            return;
        } else {
            Map<String, Object> _ppt = (Map<String, Object>) responseMessage.getBean();
            _ppt.put("tree", _rowMap.get("tree"));
            window.setPpt(_ppt);
        }
//查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
        PageUtils.changeWindowTitle(window, args);

    }
}
