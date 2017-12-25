package com.ourway.erpsystem.goods;

import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.LoginUtil;
import com.ourway.erpsystem.utils.ErpGuestBandbox;
import org.apache.commons.collections.map.HashedMap;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 LoginAction : <p>
 * <p>说明:用户登录和权限处理</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/23 14:02
 * </pre>
 */
public class GoodsEditAction extends BaseWindow {
    public static final String API_SAVE_URL = "goodsApi/save";

    //保存提交
    public void save(){
        bind2Ppt();
        List<Map<String,Object>> subdatas = new ArrayList<Map<String,Object>>();
        Map<String,Object> dataList = new HashMap<String,Object>();
        ErpGuestBandbox guestId = (ErpGuestBandbox)getFellowIfAny("dataList.erpguestRefOwid");
        BaseTextbox otherName = (BaseTextbox)getFellowIfAny("dataList.otherName");
        Map<String,Object> map = guestId.getDbClickVals();
        dataList.put("erpguestRefOwid",map.get("owid").toString());
        dataList.put("otherName",otherName.getText());
        subdatas.add(dataList);
        ppt.put("dataList",subdatas);

        ResponseMessage mess = JsonPostUtils.executeAPI(ppt,API_SAVE_URL);
        if(mess.getBackCode()==0)
            AlterDialog.alert("数据新增成功");
        else
            AlterDialog.alert(mess.getErrorMess());

    }




}
