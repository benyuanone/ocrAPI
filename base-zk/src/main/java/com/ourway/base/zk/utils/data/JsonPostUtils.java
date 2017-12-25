package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>方法 JsonPostUtils : <p>
 * <p>说明:接口调用通用类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/10 10:54
 * </pre>
 */
public class JsonPostUtils {


    /**
     * <p>方法:executeAPI 返回通用的ResponseMessage </p>
     * <ul>
     * <li> @param ppt 传入的数据对象</li>
     * <li> @param apiUrl 调用的接口路径</li>
     * <li>@return com.ourway.base.zk.models.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/10 10:56  </li>
     * </ul>
     */
    public static ResponseMessage executeAPI(Map<String, Object> ppt, String apiUrl) {
        if (TextUtils.isEmpty(apiUrl))
            return null;
        PublicData data = PublicData.instantce();
        data.setMethod(apiUrl);
        data.setData(JsonUtil.toJson(ppt));
        String result = executeAPI(data);
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>方法:executeAPI 返回通用的ResponseMessage </p>
     * <ul>
     * <li> @param apiUrl 调用的接口路径</li>
     * <li>@return com.ourway.base.zk.models.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/10 10:56  </li>
     * </ul>
     */
    public static ResponseMessage executeAPI(String apiUrl) {
        if (TextUtils.isEmpty(apiUrl))
            return null;
        PublicData data = PublicData.instantce();
        data.setMethod(apiUrl);
        String result = executeAPI(data);
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>方法:executeAPI 传入json格式的字符串数据 </p>
     * <ul>
     * <li> @param ppts json格式的字符串数据</li>
     * <li> @param apiUrl 调用接口</li>
     * <li>@return com.ourway.base.zk.models.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/14 21:22  </li>
     * </ul>
     */
    public static ResponseMessage executeAPI(String ppts, String apiUrl) {
        if (TextUtils.isEmpty(apiUrl))
            return null;
        PublicData data = PublicData.instantce();
        data.setMethod(apiUrl);
        if (!TextUtils.isEmpty(ppts))
            data.setData(ppts);
        String result = executeAPI(data);
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>方法:executeAPIAsString 调用返回字符串值，如果为空，返回空字符串 </p>
     * <ul>
     * <li> @param ppt 调用对象</li>
     * <li> @param apiUrl 调用url</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/10 10:57  </li>
     * </ul>
     */
    public static String executeAPIAsString(Map<String, Object> ppt, String apiUrl) {
        PublicData data = PublicData.instantce();
        data.setMethod(apiUrl);
        data.setData(JsonUtil.toJson(ppt));
        String result = executeAPI(data);
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0)
                return mess.getBean().toString();
            else
                return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ResponseMessage executeAPIByMap(Map<String, Object> ppt, String apiUrl) {
        PublicData data = PublicData.instantce();
        data.setMethod(apiUrl);
        data.setData(JsonUtil.toJson(ppt));
        String result = executeAPI(data);
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * <p>方法:executeAPI 带分页参数执行接口 </p>
     * <ul>
     * <li> @param ppt 传递的参数</li>
     * <li> @param apiUrl 调用的路径</li>
     * <li> @param pageNo 当前页面</li>
     * <li> @param pageSize 每页条数</li>
     * <li>@return com.ourway.base.zk.models.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 23:23  </li>
     * </ul>
     */
    public static ResponseMessage executeAPI(List<FilterModel> models, String apiUrl, int pageNo, int pageSize) {
        if (TextUtils.isEmpty(apiUrl))
            return null;
        PublicData data = PublicData.instantce();
        data.setPageNo(pageNo);
        data.setPageSize(pageSize);
        data.setMethod(apiUrl);
        if (null != models && models.size() > 0)
            data.setData(JsonUtil.toJson(models.toArray()));
        String result = executeAPI(data);
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ResponseMessage executeAPI(List<FilterModel> models, String apiUrl) {
        if (TextUtils.isEmpty(apiUrl))
            return null;
        PublicData data = PublicData.instantce();

        data.setMethod(apiUrl);
        if (null != models && models.size() > 0)
            data.setData(JsonUtil.toJson(models.toArray()));
        String result = executeAPI(data);
        try {

            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseMessage executeObjAPI(List models, String apiUrl) {
        if (TextUtils.isEmpty(apiUrl))
            return null;
        PublicData data = PublicData.instantce();

        data.setMethod(apiUrl);
        if (null != models && models.size() > 0)
            data.setData(JsonUtil.toJson(models.toArray()));
        String result = executeAPI(data);
        try {

            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            return mess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //通用的执行
    private static String executeAPI(PublicData data) {
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
