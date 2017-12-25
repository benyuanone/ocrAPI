package com.ourway.sys.api.ObjectAttrController;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import com.ourway.sys.service.ObjectAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 对象属性接口
 * Created by jackson on 17-4-27.
 */
@Controller
@RequestMapping("objectAttrApi")
public class ObjectAttrController {

    @Autowired
    private ObjectAttributeService objectAttributeSer;

    @RequestMapping(value = "saveOrUpdateObjectAttr", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdateObjectAttr(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"attributeName","objectRefOwid");
        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }
        OurwaySysObjectAttribute objectAttr = JsonUtil.map2Bean(dataMap, OurwaySysObjectAttribute.class);
        objectAttributeSer.saveOrUpdateObject(objectAttr);
        return ResponseMessage.sendOK(null);
    }

    @RequestMapping(value = "removeObjectAttr", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeObjectAttr(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysObjectAttribute ", filters);
        objectAttributeSer.removeententObjByIds(hql);
        return ResponseMessage.sendOK(null);
    }

    @RequestMapping(value = "listObjAttr", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listObj(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        HqlStatement hql = new HqlStatement("from OurwaySysObjectAttribute ", filters);
        return ResponseMessage.sendOK(objectAttributeSer.listHqlForPage(hql, 1, 20));
    }

}
