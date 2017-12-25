package com.ourway.sys.api.LogSettingController;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.model.OurwaySysLogSetting;
import com.ourway.sys.model.OurwaySysLogSettingAttribute;
import com.ourway.sys.model.OurwaySysObject;
import com.ourway.sys.service.LogSettingService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jackson on 17-4-28.
 */
@Controller
@RequestMapping("logSettingApi")
public class LogSettingController {

    @Autowired
    private LogSettingService logSettingSer;

    @RequestMapping(value = "saveOrUpdateLogSetting", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdateLogSetting(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"objectRefOwid");
        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }
        OurwaySysLogSetting object = JsonUtil.map2Bean(dataMap, OurwaySysLogSetting.class);
        //组装子表数据
        List<OurwaySysLogSettingAttribute> attributeList = null;
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            attributeList = JsonUtil.map2List(components, OurwaySysLogSettingAttribute.class);
        }
        if (null != object.getDataList())
            object.getDataList().clear();
        object = logSettingSer.saveOrUpdateLogSetting(object, attributeList);
        return ResponseMessage.sendOK(object);
    }

    @RequestMapping(value = "removeLogSetting", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeLogSetting(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysLogSetting ", filters);
        logSettingSer.removeententLogSettingByIds(hql);
        return ResponseMessage.sendOK(null);
    }

    @RequestMapping(value = "listLogSetting", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listObj(HttpServletRequest request, PublicDataVO data) {
//        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        PageInfo<Object[]> pageInfo = logSettingSer.listHqlForPage(1, 20);
        PageInfo<Map> pageMap = new PageInfo();
        if(null != pageInfo){
            Map<String, Object> map = new HashedMap();
            Object[] objects = pageInfo.getRecords().get(0);
            OurwaySysLogSetting setting = (OurwaySysLogSetting)objects[0];
            OurwaySysObject object = (OurwaySysObject)objects[1];
            map.put("className", object.getClassName());
            map.put("memo", setting.getMemo());
            List<Map> objList = new ArrayList<>();
            objList.add(map);
            pageMap.setRecords(objList);
        }
        return ResponseMessage.sendOK(pageMap);
//    public ResponseMessage listLogSetting(HttpServletRequest request, PublicDataVO data) {
//        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
//        HqlStatement hql = new HqlStatement("from OurwaySysLogSetting ", filters);
//        return ResponseMessage.sendOK(logSetting.listHqlForPage(hql, 1, 20));
    }

    @RequestMapping(value = "detailLogSetting", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailLogSetting(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysLogSetting ", filters);
        return ResponseMessage.sendOK(logSettingSer.listLogSettingByStatement(hql));
    }
}
