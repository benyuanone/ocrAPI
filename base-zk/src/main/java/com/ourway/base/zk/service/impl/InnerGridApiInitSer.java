package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.InnerGridInitSer;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class InnerGridApiInitSer implements InnerGridInitSer {

    @Override
    public void initPage(BaseWindow window, PageLayoutVO layoutVO, Map args, BaseGrid grid) {
        if (null == args) {
            return;
        }
        if (TextUtils.isEmpty(layoutVO.getControlInt())) {
            AlterDialog.alert("未定义Grid初始化调用接口");
            return;
        }
//        Map<String, Object> _params = new HashMap<String, Object>(1);
//        _params.put("owid", args.get("owid"));
        if(com.ourway.base.utils.TextUtils.isEmpty(args.get("owid")))
            return;
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(args, layoutVO.getControlInt());
        if (TextUtils.isEmpty(responseMessage))
            responseMessage = null;
        if (null == responseMessage || responseMessage.getBackCode() != 0) {
//            AlterDialog.alert("Grid初始化接口调用错误");
            return;
        }
        if (!TextUtils.isEmpty( responseMessage.getBean())) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) responseMessage.getBean();
            Collections.sort(datas, new CompareIndexOrder());//对数据进行排序，进行展现

            grid.displaySubDatas(datas);
        }
    }
}
