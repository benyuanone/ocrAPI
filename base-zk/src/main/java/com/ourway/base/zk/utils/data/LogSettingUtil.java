package com.ourway.base.zk.utils.data;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>方法 PageDataUtil : <p>
 * <p>说明:获取页面配置信息数据类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 22:03
 * </pre>
 */
public class LogSettingUtil {
    /*获取指定的code代码表*/
    public static String logSettingUrl = "objectApi/listObj";


    /**
     * <p>方法:listLogSetting 获取 </p>
     * <ul>
     * <li> @param type 字典类别</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/31 23:57  </li>
     * </ul>
     */
    public static List<Map<String, Object>> listLog() {
        PublicData data = PublicData.instantce();
        data.setMethod(logSettingUrl);
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                Map beanMap = (Map<String, Object>)mess.getBean();
                return (List<Map<String, Object>>) beanMap.get("records");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        List<Map<String, Object>> list = listLog();
        System.out.println(list);
    }
}
