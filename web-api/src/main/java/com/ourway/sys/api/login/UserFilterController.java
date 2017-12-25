package com.ourway.sys.api.login;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysDfilter;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysPrivsuser;
import com.ourway.sys.service.*;
import com.ourway.sys.utils.I18nUtils;
import com.ourway.sys.utils.ShiroUtilsClient;
import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
@RequestMapping("sysUserFilterApi")
public class UserFilterController {
    @Autowired
    EmploysService employsSer;
    @Autowired
    DepatempService depatempService;
    @Autowired
    RolesService rolesService;
    @Autowired
    PrivsuserService privsuserService;
    @Autowired
    DfilterService dfilterService;


    @RequestMapping(value = "saveFilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveFilter(HttpServletRequest request, PublicDataVO data) {
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "userRefOwid", "pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        //解析主表和子表信息
        OurwaySysDfilter ourwaySysDfilter = JsonUtil.map2Bean(mapData, OurwaySysDfilter.class);
        if (mapData.get("userRefOwid") instanceof Map) {
            Map<String, Object> picMap = (Map<String, Object>) mapData.get("userRefOwid");
            if (null != picMap && !TextUtils.isEmpty(picMap.get("owid")))
                ourwaySysDfilter.setUserRefOwid(picMap.get("owid").toString());
        }

        //判断是否唯一
        if (!dfilterService.doCheckSameUserApi(ourwaySysDfilter)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent("pulic.sys.filter.repeat", data.getCurrLanguage()));
        }
        dfilterService.saveOrUpdate(ourwaySysDfilter);

        return ResponseMessage.sendOK(dfilterService.detailOneDfilter(ourwaySysDfilter.getOwid()));
    }

    @RequestMapping(value = "listUserFilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listUserFilter(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(dfilterService.listDfilterForPage(filters, data.getPageNo(), data.getPageSize(), "pageCa"));
    }


    @RequestMapping(value = "removeFilters", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeFilters(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = dfilterService.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }

    @RequestMapping(value = "removeDetailFilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeDetailFilter(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        List<String> owids = new ArrayList<String>(1);
        owids.add(mapData.get("owid").toString());
        List<Map<String, Object>> datas = dfilterService.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }

    /**
     * <p>接口 UserController.java : <p>
     * <p>说明：用户信息和子表（用户部门、角色用户分配、数据过滤、快捷菜单）</p>
     * <pre>
     * @author cc
     * @date 2017/4/21 12:53
     * </pre>
     */
    @RequestMapping(value = "detailDfilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailDfilter(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(dfilterService.detailOneDfilter(mapData.get("owid").toString()));
    }
}
