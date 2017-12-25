package com.ourway.sys.api.ObjectController;

import com.ourway.ERPTipsConstants;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysObject;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import com.ourway.sys.service.LanguageMultService;
import com.ourway.sys.service.LanguageSer;
import com.ourway.sys.service.LogSettingService;
import com.ourway.sys.service.ObjectService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Text;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * 系统中的业务类
 * Created by jackson on 17-4-27.
 */
@Controller
@RequestMapping("sysObjectApi")
public class ObjectController {

    @Autowired
    private ObjectService objectSer;
    @Autowired
    private LogSettingService logSettingSer;
    @Autowired
    LanguageSer languageSer;

    /**
     * <p>方法:listAll 显示所有的类别，树形显示 </p>
     * <ul>
     * <li> @param type TODO</li>
     * <li> @param request TODO</li>
     * <li> @param data TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/29 9:37  </li>
     * </ul>
     */
    @RequestMapping(value = "listAllType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllType(HttpServletRequest request, PublicDataVO data) {
        return ResponseMessage.sendOK(objectSer.listAllTreeType());
    }


    @RequestMapping(value = "saveOrUpdateObject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdateObject(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "classChnname", "className");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装Object及其字表
        List<OurwaySysObjectAttribute> attributeList = null;
        OurwaySysObject obj = JsonUtil.map2Bean(dataMap, OurwaySysObject.class);
        if (!TextUtils.isEmpty(dataMap.get("dataList"))) {
            attributeList = JsonUtil.map2List((List<Map<String, Object>>) dataMap.get("dataList"), OurwaySysObjectAttribute.class);
        }

        obj = objectSer.saveOrUpdateObject(obj, attributeList);
        return ResponseMessage.sendOK(obj);
    }

    @RequestMapping(value = "generateObjs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage generateObjs(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(ERPTipsConstants.NO_PARAMS, data.getCurrLanguage()));
        }
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData().toString());
        String preClassName = "";
        if (!TextUtils.isEmpty(dataMap.get("preClassName")))
            preClassName = dataMap.get("preClassName").toString();

        List<OurwaySysObject> objectList = getObjFromPackage(dataMap.get("packageName").toString(), preClassName);

        objectSer.saveOrUpdateAllObject(objectList);
        return ResponseMessage.sendOK("");
    }


    private List<OurwaySysObject> getObjFromPackage(String packName, String preClassName) {
        List<OurwaySysObject> datas = new ArrayList<OurwaySysObject>();
        Set<Class<?>> set = ClassTools.getClasses(packName);
        String classPre = "";
        String _str = "";
        String[] _strs = null;
        String lastClassName = "";//类名最后一个
        for (Class aClass : set) {
            OurwaySysObject ourwaySysObject = new OurwaySysObject();
            ourwaySysObject.setClassName(aClass.getName());
            classPre = aClass.getName().substring(0, aClass.getName().lastIndexOf("."));
            ourwaySysObject.setClassPre(classPre);
            lastClassName = aClass.getName().replaceAll(classPre+".", "");

            PropertyDescriptor[] props = BeanUtil.getPropertyDescriptors(aClass);
            List<OurwaySysObjectAttribute> pros = new ArrayList<OurwaySysObjectAttribute>(props.length);
            for (PropertyDescriptor prop : props) {
                if (!prop.getPropertyType().getName().startsWith("java"))
                    continue;
                if (null == prop.getPropertyType() || prop.getPropertyType().getName().contains("List") || prop.getPropertyType().getName().contains("com") || prop.getPropertyType().getName().contains("Map") || prop.getPropertyType().getName().contains("Serializable") || prop.getPropertyType().getName().contains("Class"))
                    continue;
                OurwaySysObjectAttribute propMap = new OurwaySysObjectAttribute();
                propMap.setAttributeName(prop.getName());
                propMap.setAttributeDataType(prop.getPropertyType().getName());
                if (TextUtils.isEmpty(preClassName))
                    propMap.setAttributeLabelId(lastClassName + "." + prop.getName());
                else
                    propMap.setAttributeLabelId(preClassName + "." + lastClassName + "." + prop.getName());

                if (prop.getPropertyType().getName().equalsIgnoreCase("java.util.Date")) {
                    propMap.setAtttributeKjtype("4");
                }
                if (prop.getPropertyType().getName().equalsIgnoreCase("java.lang.String")) {
                    propMap.setAtttributeKjtype("0");
                }
                if (prop.getPropertyType().getName().equalsIgnoreCase("java.lang.Integer")) {
                    propMap.setAtttributeKjtype("5");
                }
                if (prop.getPropertyType().getName().equalsIgnoreCase("java.lang.Byte")) {
                    propMap.setAtttributeKjtype("5");
                }
                if (prop.getPropertyType().getName().equalsIgnoreCase("java.math.BigDecimal")) {
                    propMap.setAtttributeKjtype("6");
                }
                if (prop.getPropertyType().getName().equalsIgnoreCase("java.math.BigDecimal")) {
                    propMap.setAtttributeKjtype("6");
                }
                //做公用的定义
                if (null != Constants.PROPERTY_MAP.get(propMap.getAttributeName())) {
                    _str = Constants.PROPERTY_MAP.get(propMap.getAttributeName());
                    _strs = _str.split("\\-");
                    propMap.setAttributeLabelId(_strs[1]);
                    propMap.setAttributeChname(_strs[0]);
                }
                pros.add(propMap);
            }
            ourwaySysObject.setOurwaySysObjectAttributeList(pros);
            datas.add(ourwaySysObject);
        }
        return datas;
    }

    @RequestMapping(value = "removeObject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeObject(HttpServletRequest request, HttpServletResponse response,
                                        PublicDataVO data) {
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        return ResponseMessage.sendOK(objectSer.removeObjects(owids));
    }

    @RequestMapping(value = "listObj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listObj(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(objectSer.listHqlForPage(filters, data.getPageNo(), data.getPageSize()));
    }

    @RequestMapping(value = "listAttribute", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAttribute(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        return ResponseMessage.sendOK(objectSer.listAllAttributesByOwid(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listAttributeByClassName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAttributeByClassName(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "className");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(objectSer.listAllAttributesByClassName(mapData.get("className").toString()));
    }

    @RequestMapping(value = "detailObj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailObj(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(objectSer.detailObject(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listObjByName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listObjByName(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        String className = "";
        if (TextUtils.isEmpty(mapData.get("className")))
            className = mapData.get("className").toString();

        return ResponseMessage.sendOK(objectSer.listAllObjectByClassName(className));
    }

    @RequestMapping(value = "genLanguage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage genLanguageToTable(HttpServletRequest request, PublicDataVO data) {
        List<Map> datas = JsonUtil.jsonToList(data.getData(), Map.class);
        if (null == datas || datas.size() <= 0)
            return ResponseMessage.sendError(-1, "无数据");
        for(Map map:datas){
            if(TextUtils.isEmpty(map.get("attributeLabelId"))||TextUtils.isEmpty(map.get("attributeChname")))
                continue;
           languageSer.updateNewLanguageForObject(map.get("attributeLabelId").toString(),map.get("attributeChname").toString());
        }

        return ResponseMessage.sendOK("ok");
    }

}
