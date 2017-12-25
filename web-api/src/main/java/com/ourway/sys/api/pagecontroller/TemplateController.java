package com.ourway.sys.api.pagecontroller;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysTempcontrol;
import com.ourway.sys.model.OurwaySysTemplate;
import com.ourway.sys.service.TempcontrolService;
import com.ourway.sys.service.TemplateService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <dl>
 * <dt>ApiController 系统接Api controller</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2017/4/24 0024</dd>
 * </dl>
 *
 * @author xby
 */

@Controller
@RequestMapping("sysPageTemplateApi")
public class TemplateController {
    @Autowired
    TemplateService templateService;
    @Autowired
    TempcontrolService tempcontrolService;

    @RequestMapping(value = "saveTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveTemplate(HttpServletRequest request, HttpServletResponse response,
                                        PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "templatePath", "templateNo", "templateName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装template及其字表
        OurwaySysTemplate template = JsonUtil.map2Bean(mapData, OurwaySysTemplate.class);
        List<OurwaySysTempcontrol> ourwaySysTempcontrolList = null;
        if (null != mapData.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) mapData.get("dataList");
            ourwaySysTempcontrolList = JsonUtil.map2List(components, OurwaySysTempcontrol.class);
        }
        if (null != template.getDataList())
            template.getDataList().clear();
        template = templateService.saveOrUpdate(template, ourwaySysTempcontrolList);

        return ResponseMessage.sendOK(template);
    }

    @RequestMapping(value = "listTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTemplate(HttpServletRequest request, HttpServletResponse response,
                                        PublicDataVO data) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("templateVisible", 1);
        HqlStatement hqlStatement = new HqlStatement(OurwaySysTemplate.class, params);
        return ResponseMessage.sendOK(templateService.listAllTemplate(hqlStatement));
    }

    @RequestMapping(value = "detailTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailTemplate(HttpServletRequest request, HttpServletResponse response,
                                          PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "pageTemplate");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(templateService.detailTemplate(mapData.get("pageTemplate").toString()));
    }


    @RequestMapping(value = "listPageTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTemplatesByPage(HttpServletRequest request, HttpServletResponse response,
                                               PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(templateService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), " templateNo "));
    }


    @RequestMapping(value = "removeTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeTemplate(HttpServletRequest request, HttpServletResponse response,
                                          PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = templateService.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }


    @RequestMapping(value = "detailOneTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailOneTemplate(HttpServletRequest request, HttpServletResponse response,
                                          PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(templateService.detailTemplate(mapData.get("owid").toString()));
    }


    @RequestMapping(value = "listTemplateControls", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTemplateControls(HttpServletRequest request, HttpServletResponse response,
                                               PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        return ResponseMessage.sendOK(tempcontrolService.listTempControlByOwid(mapData.get("owid").toString()));
    }

}
