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
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("sysUserApi")
public class UserController {
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


    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage login(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "empNo", "empPsw", "language");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        UsernamePasswordToken token = new UsernamePasswordToken(dataMap.get("empNo").toString(), TextUtils.MD5(dataMap.get("empPsw").toString()).toUpperCase());
        Subject currentUser = ShiroUtilsClient.getSubject();
        try {
            if (!currentUser.isAuthenticated())
//            调用shiro框架进行用户判断
                currentUser.login(token);
//            System.out.println("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
//            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            return ResponseMessage.sendError(ResponseMessage.FAIL, "用户不存在");
        } catch (IncorrectCredentialsException ice) {
//            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            return ResponseMessage.sendError(ResponseMessage.FAIL, "密码不正确");
        } catch (LockedAccountException lae) {
//            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            return ResponseMessage.sendError(ResponseMessage.FAIL, "账户已经锁定，不能登录");
        } catch (ExcessiveAttemptsException eae) {
//            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            return ResponseMessage.sendError(ResponseMessage.FAIL, "验证未通过,错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
//            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            return ResponseMessage.sendError(ResponseMessage.FAIL, "用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
            employs.setLanguage(dataMap.get("language").toString());
            employs.setSessionId(data.getSessionId());
            currentUser.getSession().setAttribute(SessionUtils.USER_LANGUAGE, employs.getLanguage());
            ShiroUtils.getSession().setAttribute(SessionUtils.USER_KEY, employs);
            //放到redis中
            employs.setLasupdate(new java.util.Date());//放一个起始时间，用于判断用户是否失效，放入到redis中
            CacheUtil.setVal(employs.getEmpId(), employs);
            return ResponseMessage.sendOK(employs);
//            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        } else {
            token.clear();
        }
        return null;
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage logout(HttpServletRequest request, PublicDataVO data) {
        SecurityUtils.getSubject().logout();
        return ResponseMessage.sendOK(null);
    }


    @RequestMapping(value = "saveEmploy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveEmploy(HttpServletRequest request, PublicDataVO data) {
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "empId", "empType", "empName", "empMobile1");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        //解析主表和子表信息
        OurwaySysEmploys ourwaySysEmploys = JsonUtil.map2Bean(mapData, OurwaySysEmploys.class);
        if (TextUtils.isEmpty(ourwaySysEmploys.getEmpPsw()))
            ourwaySysEmploys.setEmpPsw(TextUtils.MD5("123456").toUpperCase());
        if (null == ourwaySysEmploys.getEmpStatue())
            ourwaySysEmploys.setEmpStatue(1);
        if (!TextUtils.isEmpty(mapData.get("empPic"))) {
            if (mapData.get("empPic") instanceof Map) {
                Map<String, Object> picMap = (Map<String, Object>) mapData.get("empPic");
                if (null != picMap && !TextUtils.isEmpty(picMap.get("filePath")))
                    ourwaySysEmploys.setEmpPic(picMap.get("filePath").toString());
            }
        }
        //用户角色
        List<OurwaySysPrivsuser> privsuserList = new ArrayList<OurwaySysPrivsuser>();
        if (!TextUtils.isEmpty(mapData.get("dataList1"))) {
            List<Map<String, Object>> dataList1 = (List<Map<String, Object>>) mapData.get("dataList1");
            for (Map<String, Object> map : dataList1) {
                OurwaySysPrivsuser privsuser = JsonUtil.map2Bean(map, OurwaySysPrivsuser.class);
                if (!TextUtils.isEmpty(map.get("roleRefOwid")) && map.get("roleRefOwid") instanceof Map) {
                    Map<String, Object> _map = (Map<String, Object>) map.get("roleRefOwid");
                    privsuser.setRoleRefOwid(_map.get("owid").toString());
                }
                privsuserList.add(privsuser);
            }
        }
        //用户过滤
        List<OurwaySysDfilter> userFilters = new ArrayList<OurwaySysDfilter>();
        if (!TextUtils.isEmpty(mapData.get("dataList2"))) {
            List<Map<String, Object>> dataList2 = (List<Map<String, Object>>) mapData.get("dataList2");
            for (Map<String, Object> map : dataList2) {
                OurwaySysDfilter privsuser = JsonUtil.map2Bean(map, OurwaySysDfilter.class);
                userFilters.add(privsuser);
            }
        }
        //用户部门
        List<OurwaySysDepatemp> userEmplist = new ArrayList<OurwaySysDepatemp>();
        if (!TextUtils.isEmpty(mapData.get("dataList3"))) {
            List<Map<String, Object>> dataList3 = (List<Map<String, Object>>) mapData.get("dataList3");
            for (Map<String, Object> map : dataList3) {
                OurwaySysDepatemp depatemp = JsonUtil.map2Bean(map, OurwaySysDepatemp.class);
                if (!TextUtils.isEmpty(map.get("deptRefOwid"))) {
                    Map<String, Object> _map = (Map<String, Object>) map.get("deptRefOwid");
                    depatemp.setDeptRefOwid((Integer) _map.get("owid"));
                }
                userEmplist.add(depatemp);
            }
        }
        //判断iintUrl是否唯一
        if (!employsSer.doCheckUniqueLabel(ourwaySysEmploys)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "员工号重复，请更换");
        }


        //保存或者修改
        ourwaySysEmploys = employsSer.saveAllEmploye(ourwaySysEmploys, privsuserList, userFilters, userEmplist);
        return ResponseMessage.sendOK(ourwaySysEmploys);

    }

    @RequestMapping(value = "listEmploy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listEmploy(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(employsSer.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), "empId"));
    }


    @RequestMapping(value = "removeEmploy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeEmploy(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = employsSer.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }

    //修改员工的状态
    @RequestMapping(value = "updateEmployState/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateEmployState(@PathVariable("type") Integer state, HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<Object> datas = employsSer.updateState(list,state);
        return ResponseMessage.sendOK(datas);
    }
    //修改员工的状态
    @RequestMapping(value = "resetPsw", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage resetPsw(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<Object> datas = employsSer.updateResetPsw(list);
        return ResponseMessage.sendOK(datas);
    }


    @RequestMapping(value = "removeDetailEmploy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeDetailEmploy(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        List<String> owids = new ArrayList<String>(1);
        owids.add(mapData.get("owid").toString());
        List<Map<String, Object>> datas = employsSer.removeItems(owids);
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
    @RequestMapping(value = "detailEmployDep", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailEmployDep(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(employsSer.detailEmploy(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listUserDepatemp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listUserDepatemp(HttpServletRequest request, HttpServletResponse response,
                                            PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(depatempService.listAllDepatByUserId(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listRoles(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(privsuserService.listByUserOwid(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listDfilters", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listDfilters(HttpServletRequest request, HttpServletResponse response,
                                        PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(dfilterService.listByUserOwid(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listDeparts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listDeparts(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(depatempService.listAllDepatByUserId(mapData.get("owid").toString()));
    }
}
