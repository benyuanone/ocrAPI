package com.ourway.syszk.test;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.view.BaseAction;
import org.zkoss.zk.ui.event.CreateEvent;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/12.
 */
public class DIcEdit extends BaseAction {
//    private Map<String, Object> ppt = DataBinder.createPageInfo(new String[]{"code", "name", "type"});
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        System.out.println("zkqidong");
    }
    public void save() {
        bindFromPage(ppt);
        PublicData data = new PublicData();
        data.setMethod("dicApi/saveDic");
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            showMess(result);
            info.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
