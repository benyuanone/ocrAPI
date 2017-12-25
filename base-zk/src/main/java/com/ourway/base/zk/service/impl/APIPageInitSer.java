package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import net.sf.json.JSONNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jack on 2017/5/28.
 */
public class APIPageInitSer implements PageInitSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args;
//        && !TextUtils.isEmpty(args.get("ppt"))
        if (flag) {
            if (TextUtils.isEmpty(pageVO.getPageParams())) {
                AlterDialog.alert("未定义apiUrl调用接口");
                return;
            }
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageVO.getPageParams());
            Map<String, Object> _params = paramsList.get(0);
            Map<String, Object> _rowMap = (Map<String, Object>) args.get("ppt");
            Map<String, Object> params = new HashMap<String, Object>(1);
            Set<String> keys = args.keySet();
            for (String key : keys) {
                if (!key.equalsIgnoreCase("ppt"))
                    params.put(key, args.get(key));
            }
            keys = _params.keySet();
            for (String key : keys) {
                params.put(key, _params.get(key));
            }
            if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                keys = _rowMap.keySet();
                for (String key : keys) {
                    params.put(key, _rowMap.get(key));
                }
            }
            ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
            if (TextUtils.isEmpty(responseMessage))
                responseMessage = null;
            if (null == responseMessage || responseMessage.getBackCode() != 0) {
//                AlterDialog.alert("不存在详细信息列表");
                return;
            } else
                window.setPpt((Map<String, Object>) responseMessage.getBean());
            //查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
            PageUtils.changeWindowTitle(window, args);
        }
    }
}
