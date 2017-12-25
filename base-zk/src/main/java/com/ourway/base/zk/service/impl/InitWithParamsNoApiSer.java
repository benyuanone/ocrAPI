package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jack on 2017/5/28.
 */
public class InitWithParamsNoApiSer implements PageInitSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args;
//        && !TextUtils.isEmpty(args.get("ppt"))
        //没有参数传递，不执行
        if (!flag)
            return;
        Map<String, Object> _rowMap = (Map<String, Object>) args.get("ppt");
        if (!TextUtils.isEmpty(_rowMap.get("pageType")))
            window.setWindowType(Integer.parseInt(_rowMap.get("pageType").toString()));
        window.setPpt(_rowMap);
        //查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
        PageUtils.changeWindowTitle(window, args);
    }


}
