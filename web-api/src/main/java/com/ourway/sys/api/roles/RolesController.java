package com.ourway.sys.api.roles;

import com.ourway.base.CommonConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysPrivsallocate;
import com.ourway.sys.model.OurwaySysPrivsuser;
import com.ourway.sys.model.OurwaySysRoles;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.PrivsallocateService;
import com.ourway.sys.service.PrivsuserService;
import com.ourway.sys.service.RolesService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
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
 * <p>接口 RolesController.java : <p>
 * <p>说明：角色</p>
 * <pre>
 * @author cc
 * @date 2017/4/25 13:02
 * </pre>
 */
@Controller
@RequestMapping("sysRolesApi")
public class RolesController {

    @Autowired
    RolesService rolesService;
    @Autowired
    DicService dicService;
    @Autowired
    PrivsuserService privsuserService;
    @Autowired
    PrivsallocateService privsallocateService;

    /**
     * <p>接口 RolesController.java : <p>
     * <p>说明：保存</p>
     * <pre>
     * @author cc
     * @date 2017/4/25 13:07
     * </pre>
     */
    @RequestMapping(value = "saveRole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveRole(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        //判断必填项目
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "roleName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //转成的具体的entity
        OurwaySysRoles roles = JsonUtil.map2Bean(dataMap, OurwaySysRoles.class);
        rolesService.saveOrUpdateRoles(roles);
        return ResponseMessage.sendOK(roles);
    }

    /**
     * <p>接口 listRoles.java : <p>
     * <p>说明：角色列表</p>
     * <pre>
     * @author cc
     * @date 2017/4/25 13:08
     * </pre>
     */
    @RequestMapping(value = "listRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listRoles(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(rolesService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), " roleType "));
    }

    @RequestMapping(value = "listRolesByRoleType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listRolesByRoleType(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(rolesService.listRoleByParams(filters));
    }

    /**
     * <p>接口 RolesController.java : <p>
     * <p>说明：角色详细 角色详细分配和角色用户分配</p>
     * <pre>
     * @author cc
     * @date 2017/4/25 13:09
     * </pre>
     */
    @RequestMapping(value = "detailRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailRoles(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(rolesService.listOneByOwid(dataMap.get("owid").toString()));
    }

    @RequestMapping(value = "removeRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeRoles(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = rolesService.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }

    @RequestMapping(value = "getRolesTypes", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getRolesTypes(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO data) {
        return ResponseMessage.sendOK(dicService.listDicByType(CommonConstants.ROLES_TYPE, ""));
    }

    @RequestMapping(value = "getEmpRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getEmpRoles(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "isUse");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(rolesService.listEmpRoles(dataMap));
    }


    @RequestMapping(value = "detailRoleByOwid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailRoleByOwid(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "val");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(rolesService.listOneByOwid(dataMap.get("val").toString()));
    }

    //给转换现实类使用，传入主键，获取角色名称
    @RequestMapping(value = "detailRoleNameById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailRoleNameById(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "val");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysRoles role = rolesService.listOneByOwid(dataMap.get("val").toString());
        return ResponseMessage.sendOK(role.getRoleName());
    }

    //根据owid获取相关角色的菜单
    @RequestMapping(value = "listRoleMenus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listRoleMenus(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(privsallocateService.listMenuAllocateByRoleOwid(dataMap.get("owid").toString()));
    }


}
