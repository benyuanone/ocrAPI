package com.ourway.sys.api.diccontroller;

import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.DicValueService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 UserController : <p>
 * <p>说明:用户端的管理接口</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/12 2:39
 * </pre>
 */
@Controller
@RequestMapping("sysDicApi")
public class DicController {
    @Autowired
    DicService dicService;
    @Autowired
    DicValueService dicValueService;


    @RequestMapping(value = "saveDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        List<OurwaySysDicValue> dicValues = new ArrayList<OurwaySysDicValue>();
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            dicValues = JsonUtil.map2List(components, OurwaySysDicValue.class);
        }
        dicService.saveOrUpdateAll(dicValues, type);
        return ResponseMessage.sendOK(dicService.listDicByType(type, ""));
    }

    @RequestMapping(value = "listDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        String orderColumn = "";
        if (!TextUtils.isEmpty(dataMap.get("param")))
            orderColumn = dataMap.get("param").toString();
        return ResponseMessage.sendOK(dicService.listDicByType(type, orderColumn));
    }

    @RequestMapping(value = "listLanguageDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguageDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        String orderColumn = "";
        String language = "";

        if (!TextUtils.isEmpty(dataMap.get("param")))
            orderColumn = dataMap.get("param").toString();

        if (TextUtils.isEmpty(dataMap.get("language")))
            language = "chn";
        else
            language =  dataMap.get("language").toString();

        return ResponseMessage.sendOK(dicService.listLanguageDicByType(type,"",language, orderColumn));
    }

    /*获取类别，转为相同父亲的树级点*/
    @RequestMapping(value = "listTreeDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTreeDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        String orderColumn = "";
        if (!TextUtils.isEmpty(dataMap.get("param")))
            orderColumn = dataMap.get("param").toString();
        List<Map<String, Object>> datas = dicService.listDicByType(type, orderColumn);
        List<BaseTree> treeList = new ArrayList<BaseTree>();
        int index = 1;
        for (Map<String, Object> map : datas) {
            BaseTree bt = new BaseTree();
            bt.setOwid(index);
            bt.setFid(-1);
            bt.setName(map.get("dicVal2").toString());
            bt.setId(Integer.parseInt(map.get("dicVal4").toString()));

            bt.setPath(map.get("dicVal4").toString());
            treeList.add(bt);
            index++;
        }
        return ResponseMessage.sendOK(treeList);
    }

    @RequestMapping(value = "detailOneDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailOneDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(dataMap.get("property").toString(), dataMap.get("val"));
        Object[] objs = dicService.getSingleDicByType(type, params);
        if (null == objs)
            return ResponseMessage.sendOK(null);
        else {
            OurwaySysDic dic = (OurwaySysDic) objs[0];
            OurwaySysDicValue dicValue = (OurwaySysDicValue) objs[1];
            dicValue.setOwid(dic.getOwid() + "");
            return ResponseMessage.sendOK(dicValue);
        }
    }

    @RequestMapping(value = "detailLanguageOneDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailLanguageOneDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(dataMap.get("property").toString(), dataMap.get("val"));
        if (TextUtils.isEmpty(dataMap.get("language")))
            params.put("language", "chn");
        else
            params.put("language", dataMap.get("language").toString());
        Object[] objs = dicService.getSingleDicByType(type, params);
        if (null == objs)
            return ResponseMessage.sendOK(null);
        else {
            OurwaySysDic dic = (OurwaySysDic) objs[0];
            OurwaySysDicValue dicValue = (OurwaySysDicValue) objs[1];
            dicValue.setOwid(dic.getOwid() + "");
            return ResponseMessage.sendOK(dicValue);
        }
    }

    @RequestMapping(value = "detailLanguageSingleDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailLanguageSingleDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        Map<String, Object> params = new HashMap<String, Object>();
        if ("owid".equals(dataMap.get("property").toString())) {
            params.put(dataMap.get("property").toString(), Integer.parseInt(dataMap.get("val").toString()));
        } else {
            params.put(dataMap.get("property").toString(), dataMap.get("val"));
        }
        if (TextUtils.isEmpty(dataMap.get("language")))
            params.put("language", "chn");
        else
            params.put("language", dataMap.get("language").toString());
        Object[] objs = dicService.getSingleDicByType(type, params);
        if (null == objs)
            return ResponseMessage.sendOK(null);
        else {
            OurwaySysDic dic = (OurwaySysDic) objs[0];
            OurwaySysDicValue dicValue = (OurwaySysDicValue) objs[1];
            dicValue.setOwid(dic.getOwid() + "");
            List<OurwaySysDicValue> valueList = new ArrayList<OurwaySysDicValue>();
            valueList.add(dicValue);
            dic.setDicValueList(valueList);
            return ResponseMessage.sendOK(dic);
        }
    }

    @RequestMapping(value = "listAllDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(dicService.listAllDicForPage(filters, type, data.getPageNo(), data.getPageSize(), "b.dicVal1"));
    }

    @RequestMapping(value = "listAllLikeDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllLikeDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        String key = "";
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        if (null != filters && filters.size() > 0) {
            FilterModel model = filters.get(0);
            if (null != model.getDatas() && model.getDatas().size() > 0) {
                Object obj = model.getDatas().get(0);
                if (!TextUtils.isEmpty(obj))
                    key = obj.toString();
            }
        }
        return ResponseMessage.sendOK(dicService.listAllLikeDicForPage(key, type, data.getPageNo(), data.getPageSize(), "b.dicVal1"));
    }


    @RequestMapping(value = "removeDic/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeDic(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = dicService.removeententDicByIds(owids);
        return ResponseMessage.sendOK(datas);
    }

    @RequestMapping(value = "detailDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailDic(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysDic ", filters);
        return ResponseMessage.sendOK(dicService.listDicByStatement(hql));
    }

    @RequestMapping(value = "listEventType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listEventType(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        Integer type = null;
        for (FilterModel model : filters) {
            if (model.getKey().equals("type")) {
                type = Integer.valueOf(model.getDatas().get(0).toString());
            }
        }
        OurwaySysDic dic = dicService.getSingleDicByType(type);
        return ResponseMessage.sendOK(dicValueService.listEventTypeForPage(dic.getOwid(), filters, data.getPageNo(), data.getPageSize()));
    }

    @RequestMapping(value = "detailEventTypeTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailEventTypeTemplate(HttpServletRequest request, HttpServletResponse response,
                                                   PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(dicValueService.detailEvent(mapData.get("owid").toString()));
    }


    @RequestMapping(value = "saveEvent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveEvent(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "dicVal2", "dicVal3", "dicVal4", "type");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装dic及其字表
        OurwaySysDicValue dicValue = JsonUtil.map2Bean(mapData, OurwaySysDicValue.class);
        if (null != mapData.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) mapData.get("dataList");
        }
        if (null == dicValue.getDicRefOwid()) {
            OurwaySysDic dic = dicService.getSingleDicByType(Integer.parseInt(mapData.get("type").toString()));
            dicValue.setDicRefOwid(dic.getOwid());
        }
        dicValueService.saveOrUpdate(dicValue);
        return ResponseMessage.sendOK(dicValue);
    }

    @RequestMapping(value = "listBusinessDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listBusinessDic(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(dicService.listBusinessDic(filters, data.getPageNo(), data.getPageSize()));
    }

    @RequestMapping(value = "detailBusinessDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailBusinessDic(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK(dicService.detailBusinessDic(mapData));
    }

    @RequestMapping(value = "removeBusinessDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeBusinessDic(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<Integer> owids = new ArrayList<Integer>(list.size());
        for (Object obj : list) {
            owids.add((Integer) ((Map<String, Object>) obj).get("owid"));
        }
        List<Map<String, Object>> datas = dicService.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }

    @RequestMapping(value = "saveBusinessDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveBusinessDic(HttpServletRequest request, PublicDataVO data) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (ValidateUtils.isEmpty(dataMap.get("type"))) {
            return ResponseMessage.sendError(-1, "type不能为空");
        }
        OurwaySysDic ourwaySysDic = JsonUtil.map2Bean(dataMap, OurwaySysDic.class);
        List<OurwaySysDicValue> dicValueList = null;
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            dicValueList = JsonUtil.map2List(components, OurwaySysDicValue.class);
        }
//        ourwaySysDic.setDataList(dicValueList);

        if (!dicService.doUniqueCheckType(ourwaySysDic)) {
            return ResponseMessage.sendError(-1, "type重复，请重新输入");
        }
        dicService.saveOrUpdateDic(ourwaySysDic);
        return ResponseMessage.sendOK(ourwaySysDic);
    }


    //多语言字典分页
    @RequestMapping(value = "listLanguageDicByType/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguageDicByType(@PathVariable("type") Integer type,HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        String language = SessionUtils.getCurrLanguage();
        return ResponseMessage.sendOK(dicService.listLanguageDicByType(type,"", language, ""));
    }

    //多语言字典批查询
    @RequestMapping(value = "listLanguageDicByDicList/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguageDicByDicList(@PathVariable("type") Integer type,HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        String language = SessionUtils.getCurrLanguage();
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        @SuppressWarnings("unchecked")
        List<String> dicList = (List<String>) dataMap.get("param");
        return ResponseMessage.sendOK(dicService.listLanguageDicByDicList(dicList,type, language, ""));
    }

    @RequestMapping(value = "listLanguageDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguageDic(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        boolean flag = false;
        for (FilterModel filter : filters) {
            if (filter.getKey().equalsIgnoreCase("type")) {
                flag = true;
                List<Object> objs = filter.getDatas();
                List<Object> newObjs = new ArrayList<Object>(1);
                newObjs.add(new Integer(objs.get(0).toString()));
                filter.setDatas(newObjs);
                break;
            }
        }
        //若没有type，则加一个默认的
        if (!flag) {
            FilterModel model = new FilterModel();
            model.setKey("type");
            List<Object> valueList = new ArrayList<Object>(1);
            valueList.add(-99999);
            model.setDatas(valueList);
            model.setType(FilterModel.EQUALS);
            if (null == filters)
                filters = new ArrayList<FilterModel>(1);
            filters.add(model);
        }
        return ResponseMessage.sendOK(dicService.listHqlForPage(filters, data.getPageNo(), data.getPageSize()));
    }

    //多语言字典分页
    @RequestMapping(value = "listLanguageDicVals", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguageDicVals(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (ValidateUtils.isEmpty(dataMap.get("owid"))) {
            return ResponseMessage.sendError(-1, "owid不能为空");
        }
        OurwaySysDic dic = dicService.listOneDicByOwid(dataMap.get("owid").toString());
        return ResponseMessage.sendOK(dicValueService.listDicValuesByRefOwid(dic));
    }

    //多语言字典详情
    @RequestMapping(value = "detailLanguageDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailLanguageDic(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (ValidateUtils.isEmpty(dataMap.get("owid"))) {
            return ResponseMessage.sendError(-1, "owid不能为空");
        }
        OurwaySysDic dic = dicService.listOneDicByOwid(dataMap.get("owid").toString());
        return ResponseMessage.sendOK(dic);
    }

    //多语言字典详情
    @RequestMapping(value = "saveLanguageDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveLanguageDic(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (TextUtils.isEmpty(dataMap.get("type")) && !TextUtils.isEmpty(dataMap.get("tree"))) {
            Map<String, Object> treeMap = (Map<String, Object>) dataMap.get("tree");
            if (!TextUtils.isEmpty(treeMap.get("path")))
                dataMap.put("type", treeMap.get("path"));
        }
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "code", "name", "type");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装Object及其字表
        List<OurwaySysDicValue> attributeList = null;
        OurwaySysDic obj = JsonUtil.map2Bean(dataMap, OurwaySysDic.class);
        if (!TextUtils.isEmpty(dataMap.get("dataList"))) {
            attributeList = JsonUtil.map2List((List<Map<String, Object>>) dataMap.get("dataList"), OurwaySysDicValue.class);
        }

        obj = dicService.saveOrUpdateObject(obj, attributeList);
        return ResponseMessage.sendOK(obj);
    }

}
