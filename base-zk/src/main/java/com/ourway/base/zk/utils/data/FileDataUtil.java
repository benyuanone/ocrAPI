package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import net.sf.json.JSONNull;

import java.util.*;

/**
 * <p>方法 PageDataUtil : <p>
 * <p>说明:获取页面配置信息数据类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 22:03
 * </pre>
 */
public class FileDataUtil {
    /*页面对应的控件获取api方法*/
    public static String SAVE_URL = "sysAttachFileApi/saveFile";
    public static String LIST_URL = "sysAttachFileApi/list";
    public static String REMOVE_URL = "sysAttachFileApi/remove";
    public static String UPDATE_URL = "sysAttachFileApi/update";


    public static List<Map<String, Object>> listFiles(String fileExtendId,String fileCode) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        PublicData data = PublicData.instantce();
        data.setMethod(LIST_URL);
        Map<String, String> ppt = new HashMap<String, String>();
        List<Object> objs = new ArrayList<Object>();
        List<FilterModel> models = new ArrayList<FilterModel>(1);
        FilterModel model = new FilterModel();
        model.setType(FilterModel.EQUALS);
        model.setKey("fileClassId");
        objs.add(fileExtendId);
        model.setDatas(objs);
        models.add(model);
        objs = new ArrayList<Object>();
        objs.add(fileCode);
        model = FilterModel.instance("fileClass",FilterModel.EQUALS,objs);
        models.add(model);
        data.setData(JsonUtil.toJson(models.toArray()));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null == mess || mess.getBackCode() != 0)
                return null;

            if (mess.getBean() instanceof JSONNull)
                return null;
            if (null != mess.getBean()) {
                datas = (List<Map<String, Object>>) mess.getBean();
                return datas;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String saveFiles(Map<String, Object> ppt) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        PublicData data = PublicData.instantce();
        data.setMethod(SAVE_URL);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null == mess || mess.getBackCode() != 0)
                return null;
            if (mess.getBean() instanceof JSONNull)
                return null;
            if (null != mess.getBean()) {
                return mess.getBean().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeFiles(List<String> owids) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        PublicData data = PublicData.instantce();
        data.setMethod(REMOVE_URL);
        data.setData(JsonUtil.toJson(owids.toArray()));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateFiles(List<Map<String,Object>> lbdata) {
        PublicData data = PublicData.instantce();
        data.setMethod(UPDATE_URL);
        data.setData(JsonUtil.toJson(lbdata.toArray()));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();

    }
}
