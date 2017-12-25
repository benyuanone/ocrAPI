package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.ZKSessionUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
public class DicUtil {
    /*获取指定的code代码表*/
    public static String DIC_URL = "sysDicApi/listDic/";
    public static String DIC_LANGUAGE_URL = "sysDicApi/listLanguageDic/";
    public static String DIC_LANGUAGE_LIST_URL = "sysDicApi/listLanguageDicByDicList/";
    public static String DIC_DETAIL_URL = "sysDicApi/detailOneDic/";
    public static String DIC_DETAIL_WITHLANGUAGE_URL = "sysDicApi/detailLanguageOneDic/";
    public static String DIC_SINGLE_WITHLANGUAGE_URL = "sysDicApi/detailLanguageSingleDic/";


    public static Map<String, Object> listOneDetail(int type, String property, Object val) {
        PublicData data = PublicData.instantce();
        data.setMethod(DIC_DETAIL_URL + type);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("property", property);
        if (!TextUtils.isEmpty(val))
            ppt.put("val", val.toString());
        else
            ppt.put("val", "");
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                return (Map<String, Object>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Map<String, Object> listOneDetailWithLanguage(int type, String property, Object val) {
        PublicData data = PublicData.instantce();
        data.setMethod(DIC_DETAIL_WITHLANGUAGE_URL + type);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("property", property);
        if (!TextUtils.isEmpty(val))
            ppt.put("val", val.toString());
        else
            ppt.put("val", "");
//        System.out.println();
        ppt.put("language",ZKSessionUtils.getZkUser().getLanguage());
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                return (Map<String, Object>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> detailDicWithLanguage(int type, String property, Object val) {
        PublicData data = PublicData.instantce();
        data.setMethod(DIC_SINGLE_WITHLANGUAGE_URL + type);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("property", property);
        if (!TextUtils.isEmpty(val))
            ppt.put("val", val.toString());
        else
            ppt.put("val", "");
//        System.out.println();
        ppt.put("language",ZKSessionUtils.getZkUser().getLanguage());
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                return (Map<String, Object>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>方法:listDic 获取指定类别的字典数据 </p>
     * <ul>
     * <li> @param type 字典类别</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/31 23:57  </li>
     * </ul>
     */
    public static List<Map<String, Object>> listDic(int type) {
        PublicData data = PublicData.instantce();
        data.setMethod(DIC_URL + type);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("type", type);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                return (List<Map<String, Object>>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Map<String, Object>> listDic(int type, String sortStr) {
        return listDicMain(DIC_URL + type, sortStr);
    }
    public static List<Map<String, Object>> listLanguageDic(int type, String sortStr) {
        return listLanguageDicMain(DIC_LANGUAGE_URL + type, sortStr);
    }
    public static List<Map<String, Object>> listLanguageDicList(int type, List dicList) {
        return listLanguageDicMain(DIC_LANGUAGE_LIST_URL + type, dicList);
    }

    public static List<Map<String, Object>> listDicMain(String url, String sortStr) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("param", sortStr);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                return (List<Map<String, Object>>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Map<String, Object>> listLanguageDicMain(String url, Object param) {
        PublicData data = PublicData.instantce();
        data.setMethod(url);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("param", param);
        ppt.put("language",ZKSessionUtils.getZkUser().getLanguage());
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                return (List<Map<String, Object>>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();
    }
}
