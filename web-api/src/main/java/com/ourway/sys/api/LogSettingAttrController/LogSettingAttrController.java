package com.ourway.sys.api.LogSettingAttrController;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.model.OurwaySysLogSettingAttribute;
import com.ourway.sys.service.LogSettingAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by jackson on 17-4-28.
 */
public class LogSettingAttrController {

    @Autowired
    private LogSettingAttributeService logSettingAttrSer;

    @RequestMapping(value = "saveOrUpdateLogSettingAttr", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdateLogSettingAttr(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"attributeRefOwid","logRefOwid");
        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }
        OurwaySysLogSettingAttribute object = JsonUtil.map2Bean(dataMap, OurwaySysLogSettingAttribute.class);
        logSettingAttrSer.saveOrUpdateLogSettingAttr(object);
        return ResponseMessage.sendOK(object);
    }

    @RequestMapping(value = "removeObject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeObject(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysLogSettingAttribute ", filters);
        logSettingAttrSer.removeententLogSettingAttrByIds(hql);
        return ResponseMessage.sendOK(null);
    }

    @RequestMapping(value = "listObj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listObj(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysLogSettingAttribute ", filters);
        return ResponseMessage.sendOK(logSettingAttrSer.listHqlForPage(hql, 1, 20));
    }

    @RequestMapping(value = "detailObj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailObj(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysLogSettingAttribute ", filters);
        return ResponseMessage.sendOK(logSettingAttrSer.listLogSettingAttrByStatement(hql));
    }
}
