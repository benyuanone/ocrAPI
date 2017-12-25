package com.ourway.sys.api.treedic;

import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.model.*;
import com.ourway.sys.service.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("sysTreeDicApi")
public class TreeDicController {
    @Autowired
    DicService dicService;
//
//    /**
//     * <p>接口 saveMenu.java : <p>
//     * <p>说明：</p>
//     * <pre>
//     * @author cc
//     * @date 2017/4/26 10:51
//     * </pre>
//     */
//    @RequestMapping(value = "saveMenu", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage saveMenu(HttpServletRequest request, PublicDataVO dataVo) {
//        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
//        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "name", "fid", "path","px");
//        if (!msg.getSuccess()) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
//        }
//        //转成的具体的entity
//        OurwaySysMenus menu = JsonUtil.map2Bean(dataMap, OurwaySysMenus.class);
//
//        if (!TextUtils.isEmpty(menu.getPageId())) {
//            Map<String, Object> params = new HashMap<String, Object>(1);
//            params.put("owid", menu.getPageId().replaceAll(",", ""));
//            OurwaySysPage sysPage = pageService.queryPage(params, "");
//            menu.setPageCa(sysPage.getPageCa());
//            if (null != sysPage.getPageCustomer() && sysPage.getPageCustomer().intValue() == 1)
//                menu.setLink(sysPage.getPageCa());
//            else {
//                OurwaySysTemplate template = templateService.detailTemplate(sysPage.getPageTemplate());
//                if (null != template)
//                    menu.setLink(template.getTemplatePath());
//            }
//        }
//        menuService.saveOrUpdate(menu);
//        menu.setPath(menu.getPath() + menu.getOwid());
//        menuService.saveOrUpdate(menu);
//        return ResponseMessage.sendOK(menu);
//    }

//    @RequestMapping(value = "removeMenu", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage removeMenu(HttpServletRequest request, PublicDataVO dataVo) {
//        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
//        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
//        if (!msg.getSuccess()) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
//        }
//        //转成的具体的entity
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("owid", dataMap.get("owid").toString());
//        menuService.removeMenu(map);
//        return ResponseMessage.sendOK(map);
//    }

    /**
     * <p>接口 listMenus.java : <p>
     * <p>说明：</p>
     * <pre>
     * @author cc
     * @date 2017/4/26 10:51
     * </pre>
     */
    @RequestMapping(value = "listAll/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAll(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (null == type)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "no Type");
        List<OurwaySysDic> dics = dicService.listAllTreeDic(type);
        //BaseTree 返回
        List<BaseTree> baseTrees = new ArrayList<BaseTree>(dics.size());
        if (null != dics && dics.size() > 0)
            for (OurwaySysDic dic : dics) {
                baseTrees.add(TreeUtils.convert(dic, new String[]{"owid-owid", "fid-fid", "path-path", "name-name"}));
            }
        return ResponseMessage.sendOK(baseTrees);
    }

    @RequestMapping(value = "save/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (null == type)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "no Type");
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "fid", "path", "name", "px");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        BaseTree tree = JsonUtil.map2Bean(dataMap, BaseTree.class);

        OurwaySysDic dic = dicService.saveOrUpdateTree(tree, type);
        if(dic.getPath().lastIndexOf(dic.getOwid()+"/")<0){
            //需要更新path
            dic.setPath(dic.getPath()+dic.getOwid()+"/");
            dicService.saveOrUpdate(dic);
        }
        return ResponseMessage.sendOK(dic);
    }

    @RequestMapping(value = "remove/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage remove(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (null == type)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "no Type");
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "id");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        BaseTree tree = JsonUtil.map2Bean(dataMap, BaseTree.class);
        tree.setOwid(Integer.parseInt(dataMap.get("id").toString()));

        dicService.removeTree(tree, type);
        return ResponseMessage.sendOK("");
    }

    @RequestMapping(value = "move/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage move(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> trees = JsonUtil.jsonToList(jsonArray);
        if (null == trees || trees.size()<=0)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "no Type");
        for(Object obj:trees){
            Map<String,Object> map = (Map<String,Object>)obj;
            BaseTree tree = JsonUtil.map2Bean(map,BaseTree.class);
           OurwaySysDic dic = dicService.saveOrUpdateTree(tree,type);
        }
        return ResponseMessage.sendOK("");
    }

    @RequestMapping(value = "listLanguageAll/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguageAll(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        if (null == type)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "no Type");
        String code = "";
        for (FilterModel filterModel:filterModels) {
            if ("code".equals(filterModel.getKey())) {
                code = filterModel.getDatas().get(0).toString();
            }
        }
        List<Map<String,Object>> dics = dicService.listLanguageDicByType(type,code,SessionUtils.getCurrLanguage(),"");
        //BaseTree 返回
        List<BaseTree> baseTrees = new ArrayList<BaseTree>(dics.size());
        if (dics.size() > 0)
            for (Map<String,Object> dic : dics) {
                baseTrees.add(TreeUtils.convert(dic, new String[]{"owid-owid", "fid-fid", "code-path", "dicVal1-name"}));
            }
        return ResponseMessage.sendOK(baseTrees);
    }

////
//
//    /**
//     * <p>方法:detailMenus  根据用户获取其菜单和控件的权限。 </p>
//     * <ul>
//     * <li> @param request </li>
//     * <li> @param data </li>
//     * <li>@return com.ourway.base.model.ResponseMessage  </li>
//     * <li>@author JackZhou </li>
//     * <li>@date 2017/5/6 11:58  </li>
//     * </ul>
//     */
//    @RequestMapping(value = "listUserMenus", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage listUserMenus(HttpServletRequest request, PublicDataVO data) {
//        OurwaySysEmploys employs = ShiroUtils.getUserEntity();
//        if (null == employs) {
//            return ResponseMessage.sendError(ResponseMessage.RELOGIN, "用户登录失效，请重新登录");
//        }
//        return ResponseMessage.sendOK(menuService.listEmployMenus(employs));
//    }
//
//    //用于节点移动等操作
//    @RequestMapping(value = "updateMenu", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage updateMenu(HttpServletRequest request, PublicDataVO data) {
//        List<OurwaySysMenus> ourwayMenus = JsonUtil.jsonToList(data.getData(), OurwaySysMenus.class);
//        if (null == ourwayMenus || ourwayMenus.size() <= 0) {
//            return ResponseMessage.sendError(ResponseMessage.RELOGIN, "请上传需要更改的菜单");
//        }
//        List<OurwaySysMenus> svMenus = new ArrayList<OurwaySysMenus>();
//        //对可能存在节点fid更换的进行处理
//        String[] tmp = null;
//        String _path = "";
//        for (OurwaySysMenus ourwayMenu : ourwayMenus) {
//            tmp = ourwayMenu.getPath().split("\\/");
//            if (!tmp[tmp.length - 2].equals(ourwayMenu.getFid().toString())) {
//                //表示父节点更新了
//                List<OurwaySysMenus> menus = menuService.listMenusByPath(ourwayMenu.getPath());
//                for (OurwaySysMenus menu : menus) {
//                    _path = menu.getPath().replaceAll("/" + tmp[tmp.length - 2] + "/", "/" + ourwayMenu.getFid() + "/");
//                    menu.setPath(_path);
//                    menu.setFid(ourwayMenu.getFid());
//                    svMenus.add(menu);
//                }
//            }else
//                svMenus.add(ourwayMenu);
//        }
//        menuService.saveOrUpdateAll(svMenus);
//        return ResponseMessage.sendOK(null);
//    }


}
