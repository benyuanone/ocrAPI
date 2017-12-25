package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class ComponentInitListboxFromLanguageDicSerImpl implements ComponentInitSer {
    public static final String YES = "yes";

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"type":0,"default":"yes"}]
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageControlVO.getKjInitData().toString());
        if (null == paramsList || paramsList.size() <= 0) {
            AlterDialog.alert("请传入本接口所需的JSON参数");
            return;
        }
        Map<String, Object> param = paramsList.get(0);
        if (TextUtils.isEmpty(param.get("type"))) {
            AlterDialog.alert("请传入字典表类型");
            return;
        }
        if(null!=param.get("default")&& YES.equalsIgnoreCase(param.get("default").toString())){
            Listitem item = new Listitem(I18nUtil.getLabelContent(ERCode.LISTBOX_DEFAULT),"");
            item.setParent(component);
            item.setSelected(true);
        }
        String label = "";
        String value = "";
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        //展示顶层showTop（owid=-1）选项
        if(null!=param.get("showTop")&&YES.equalsIgnoreCase(param.get("showTop").toString())) {
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("owid",-1);
            data.put("code",-1);
            data.put("dicVal1","顶层");
            datas.add(data);
        }
        datas.addAll(DicUtil.listLanguageDic(Integer.parseInt(param.get("type").toString()), ""));
        if (null != datas && datas.size() > 0) {
            for (Map<String, Object> data : datas) {
                //展示值showCode
                if(null!=param.get("showCode")&&YES.equalsIgnoreCase(param.get("showCode").toString())){
                    label = data.get("dicVal1").toString()+"("+data.get("code").toString()+")";
                    value = data.get("owid").toString();
                } else {
                    label = data.get("dicVal1").toString();
                    value = data.get("code").toString();
                }
                Listitem item = new Listitem(label,value);
                item.setParent(component);
            }
        }
    }
}
