package com.ourway.test;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.service.impl.APIPageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;

import java.util.*;

/**
 * Created by David on 2017-09-14.
 */
public class BlacklistPageInitSer extends APIPageInitSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        super.initPage(window, args, pageVO);
/*        Map<String,Object> _rowMap = (Map<String,Object>)args.get("ppt");
        Map<String,Object> map = window.getAttributes();
        PageControlVO subComp = (PageControlVO) window.getAttributes();
        Component revokeBtn2Component = window.getFellowIfAny("mainButtonGrid_revokeBtn2");*/
/*        if ("0".equals(_rowMap.get("state").toString())) {
            //当状态为有效，则将“加入黑名单”按钮置为无效
            ((BaseButton) addToBlacklistComponent).setDisabled(true);
            ((BaseButton) revokeBtn2Component).setDisabled(false);
        }
        if ("1".equals(_rowMap.get("state").toString())) {
            //当状态为撤销，则将“撤销”按钮置为无效
            ((BaseButton) revokeBtn2Component).setDisabled(true);
            ((BaseButton) addToBlacklistComponent).setDisabled(false);
        }*/
//查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
        PageUtils.changeWindowTitle(window, args);
    }
}
