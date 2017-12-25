package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 MenusDataUtil : <p>
 * <p>说明:系统菜单处理类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/6 11:57
 * </pre>
 */
public class MessUtil {
    public static final String FLOW_URL = "/sysMessageApi/listAllTask";
    public static final String READMESSAGE_URL = "/sysMessageApi/readMessage";
    public static final String PAGECA_URL = "/sysPageApi/listLinkByPageCA";

    public static Map<String, Object> listWaitTask() {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        PublicData data = PublicData.instantce();
        data.setMethod(FLOW_URL);
        data.setPageNo(0);
        data.setPageSize(15);
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                if (null != mess.getBean()) {
                    return (Map<String, Object>) mess.getBean();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readMessage(String owid) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        PublicData data = PublicData.instantce();
        data.setMethod(READMESSAGE_URL);
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", owid);
        data.setData(JsonUtil.toJson(params));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据pagaca获取对应的模板的link
    public static String getLinkByPageCa(String pageCa) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        PublicData data = PublicData.instantce();
        data.setMethod(PAGECA_URL);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageCa", pageCa);
        data.setData(com.ourway.base.utils.JsonUtil.toJson(params));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                if (null != mess.getBean()) {
                    return mess.getBean().toString();
                }
            }
            return pageCa;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageCa;
    }


    //把pageca中的参数转为map
    public static Map<String, Object> tranfLink(String pageCa) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (pageCa.indexOf("?") < 0)
            return data;
        String params = pageCa.split("\\?")[1];
        String[] _params = params.split("\\&");
        for (String param : _params) {
            String[] _param = param.split("\\=");
            data.put(_param[0], _param[1]);
        }
        return data;
    }


    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();

    }
}
