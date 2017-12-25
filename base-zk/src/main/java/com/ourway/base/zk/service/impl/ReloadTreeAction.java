package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 刷新表格</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class ReloadTreeAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        try {
            window.loadTreeData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
