package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.BillIdUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.List;
import java.util.Map;

/**
*<p>方法: 单据编号获取公用类 </p>
*<ul>
*<li>@author JackZhou </li>
*<li>@date 2017/6/7 20:43  </li>
*</ul>
*/
public class ComponentDjbhInitSerImpl implements ComponentInitSer {

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"type":"单据类型"}]
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageControlVO.getKjInitData().toString());
        if (null == paramsList || paramsList.size() <= 0) {
            AlterDialog.alert("请传入本接口所需的JSON参数");
            return;
        }
        Map<String, Object> param = paramsList.get(0);
        if (TextUtils.isEmpty(param.get("type"))) {
            AlterDialog.alert("请传入单据类型");
            return;
        }
        String label = BillIdUtils.getBillId(param.get("type").toString());
        if(TextUtils.isEmpty(label)){
            AlterDialog.alert("未获取指定类型的单据编号，请检查配置是否正确");
            return;
        }
        DataBinder.obj2Component(label,component,null);
    }
}
