package com.ourway.base.zk.utils.data;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 获取单据编号 </p>
 * <ul>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/6/1 21:02  </li>
 * </ul>
 */
public class BillIdUtils {
    /*获取指定的code代码表*/
    public static String BILL_URL = "sysBillIdApi/getBillId";


    /**
     * <p>方法:listDic 获取指定的单据编号 </p>
     * <ul>
     * <li> @param type 字典类别</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/31 23:57  </li>
     * </ul>
     */
    public static String getBillId(String key) {
        PublicData data = PublicData.instantce();
        data.setMethod(BILL_URL);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("key", key);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                Map<String, Object> bean = (Map<String, Object>) mess.getBean();
                if (null != bean && null != bean.get("billXhlabel"))
                    return bean.get("billXhlabel").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {
        System.out.println(getBillId("test001"));

    }
}
