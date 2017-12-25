package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.ShiroUtils;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.ZKSessionUtils;
import org.zkoss.zk.ui.Component;

/**
 * Created by David on 2017-09-12.
 */
public class GetCurrentUser implements ComponentInitSer {
    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {

        DataBinder.obj2Component(ZKSessionUtils.getZkUser().getEmpName(),component,null);
    }
}
