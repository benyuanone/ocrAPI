package com.ourway.sys.api.menuscontroller;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTemplate;
import com.ourway.sys.service.MenusService;
import com.ourway.sys.service.PageService;
import com.ourway.sys.service.PrivsallocateService;
import com.ourway.sys.service.TemplateService;
import com.ourway.sys.utils.ShiroUtilsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 MenusController.java : <p>
 * <p>说明：系统菜单</p>
 * <pre>
 * @author cc
 * @date 2017/4/26 10:37
 * </pre>
 */
@Controller
@RequestMapping("sysMenusApi")
public class MenusController {
    @Autowired
    MenusService menuService;
    @Autowired
    PageService pageService;
    @Autowired
    TemplateService templateService;

    @Autowired
    PrivsallocateService privsallocateService;

    /**
     * <p>接口 saveMenu.java : <p>
     * <p>说明：</p>
     * <pre>
     * @author cc
     * @date 2017/4/26 10:51
     * </pre>
     */
    @RequestMapping(value = "saveMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveMenu(HttpServletRequest request, PublicDataVO dataVo) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "name", "fid", "path", "px");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //转成的具体的entity
        if (!TextUtils.isEmpty(dataMap.get("pageId"))) {
            if (dataMap.get("pageId") instanceof List) {
                List _pageMap = (List) dataMap.get("pageId");
                if (_pageMap.size() > 0)
                    dataMap.put("pageId", _pageMap.get(0).toString());
                else
                    dataMap.put("pageId", "");
            }
            if (dataMap.get("pageId") instanceof HashMap) {
                Map<String, Object> _pageMap = (Map<String, Object>) dataMap.get("pageId");
                dataMap.put("pageId", _pageMap.get("owid").toString());
            }

        } else
            dataMap.put("pageId", "");
        OurwaySysMenus menu = JsonUtil.map2Bean(dataMap, OurwaySysMenus.class);

        if (!TextUtils.isEmpty(menu.getPageId())) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", menu.getPageId().replaceAll(",", ""));
            OurwaySysPage sysPage = pageService.queryPage(params, "");
            if (null != sysPage) {
                menu.setPageCa(sysPage.getPageCa());
                if (null != sysPage.getPageCustomer() && sysPage.getPageCustomer().intValue() == 1)
                    menu.setLink(sysPage.getPageCa());
                else {
                    OurwaySysTemplate template = templateService.detailTemplate(sysPage.getPageTemplate());
                    if (null != template)
                        menu.setLink(template.getTemplatePath());
                }
            }
        }
        menuService.saveOrUpdate(menu);
        menu.setPath(menu.getPath() + menu.getOwid());
        menuService.saveOrUpdate(menu);
        return ResponseMessage.sendOK(menu);
    }

    @RequestMapping(value = "removeMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeMenu(HttpServletRequest request, PublicDataVO dataVo) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //转成的具体的entity
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owid", dataMap.get("owid").toString());
        menuService.removeMenu(map);
        return ResponseMessage.sendOK(map);
    }

    /**
     * <p>接口 listMenus.java : <p>
     * <p>说明：</p>
     * <pre>
     * @author cc
     * @date 2017/4/26 10:51
     * </pre>
     */
    @RequestMapping(value = "listMenus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listMenus(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(menuService.listHqlForPage(filters, data.getPageNo(), data.getPageSize()));
    }

    @RequestMapping(value = "listMenusByPageCA", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listMenusByPageCA(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(menuService.listMenusByPageCA(dataMap.get("pageCa").toString()));
    }

    /*
     根据owid，获取指定menu的内容

     */
    @RequestMapping(value = "listOneMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listOneMenu(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(menuService.listOneByOwid(Integer.parseInt(dataMap.get("owid").toString())));
    }


    @RequestMapping(value = "listAllMenus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllMenus(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = null;
        if (!TextUtils.isEmpty(data.getData()))
            filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(menuService.listRoleByStatement(filters));
    }


    @RequestMapping(value = "detailMenus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailMenus(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(menuService.listRoleByStatement(filters));
    }

    /**
     * <p>方法:detailMenus  根据用户获取其菜单和控件的权限。 </p>
     * <ul>
     * <li> @param request </li>
     * <li> @param data </li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/6 11:58  </li>
     * </ul>
     */
    @RequestMapping(value = "listUserMenus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listUserMenus(HttpServletRequest request, PublicDataVO data) {
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        if (null == employs) {
            return ResponseMessage.sendError(ResponseMessage.RELOGIN, "用户登录失效，请重新登录");
        }
        return ResponseMessage.sendOK(menuService.listEmployMenus(employs));
    }

    //用于节点移动等操作
    //用于废弃的操作  2017-11-17
    @RequestMapping(value = "updateMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateMenu(HttpServletRequest request, PublicDataVO data) {
        List<OurwaySysMenus> ourwayMenus = JsonUtil.jsonToList(data.getData(), OurwaySysMenus.class);
        if (null == ourwayMenus || ourwayMenus.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.RELOGIN, "请上传需要更改的菜单");
        }
        List<OurwaySysMenus> svMenus = new ArrayList<OurwaySysMenus>();
        //对可能存在节点fid更换的进行处理
        String[] tmp = null;
        String _path = "";
        for (OurwaySysMenus ourwayMenu : ourwayMenus) {
            tmp = ourwayMenu.getPath().split("\\/");
            if (!tmp[tmp.length - 2].equals(ourwayMenu.getFid().toString())) {
                //表示父节点更新了
                List<OurwaySysMenus> menus = menuService.listMenusByPath(ourwayMenu.getPath());
                for (OurwaySysMenus menu : menus) {
                    _path = menu.getPath().replaceAll("/" + tmp[tmp.length - 2] + "/", "/" + ourwayMenu.getFid() + "/");
                    menu.setPath(_path);
                    menu.setFid(ourwayMenu.getFid());
                    svMenus.add(menu);
                }
            } else
                svMenus.add(ourwayMenu);
        }
        menuService.saveOrUpdateAll(svMenus);
        return ResponseMessage.sendOK(null);
    }

    @RequestMapping(value = "roleMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage roleMenu(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK(privsallocateService.getSelMenus(dataMap));
    }

    @RequestMapping(value = "getAllMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getAll(HttpServletRequest request, PublicDataVO data) {
        return ResponseMessage.sendOK(menuService.listAllMenu());
    }

    //用于节点移动等操作
    @RequestMapping(value = "updateMoveMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateMoveMenu(HttpServletRequest request, PublicDataVO data) {
        System.out.println(data.getData());
        List<Map> menuDatas = JsonUtil.jsonToList(data.getData(), Map.class);
        List<OurwaySysMenus> ourwayMenus = new ArrayList<OurwaySysMenus>(menuDatas.size());
        for (Map menuData : menuDatas) {
            OurwaySysMenus menu = JsonUtil.map2Bean(menuData, OurwaySysMenus.class);
            ourwayMenus.add(menu);
        }
        if (null == ourwayMenus || ourwayMenus.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.RELOGIN, "请上传需要更改的菜单");
        }
        menuService.update(ourwayMenus);
        return ResponseMessage.sendOK(null);
    }


}
