package com.ourway.sys.api.pagecontroller;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.*;
import com.ourway.sys.service.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("sysPageApi")
public class PageController {
    @Autowired
    PageService pageService;
    @Autowired
    PageComponentService pageComponentService;
    @Autowired
    LayoutService layoutService;
    @Autowired
    LayoutComponentService layoutComponentService;
    @Autowired
    TemplateService templateService;


    @RequestMapping(value = "savePage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage savePage(HttpServletRequest request, HttpServletResponse response,
                                    PublicDataVO data) {
        // json 转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "pageNo", "pageCa", "pageName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //map to enity
        OurwaySysPage ourwaySysPage = JsonUtil.map2Bean(mapData, OurwaySysPage.class);
        List<OurwaySysPageComponent> ourwaySysPageComponentList = null;
        //专业页面下面的控件
        if (null != mapData.get("component")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) mapData.get("component");
            ourwaySysPageComponentList = JsonUtil.map2List(components, OurwaySysPageComponent.class);
        }
        //装换布局控件
        List<OurwaySysLayout> ourwaySysLayoutList = null;
        if (null != mapData.get("layout")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) mapData.get("layout");
            if (null != components && components.size() > 0) {
                ourwaySysLayoutList = new ArrayList<OurwaySysLayout>(components.size());
                for (Map<String, Object> component : components) {
                    if (null != component.get("TabDatas")) {
                        List<Map<String, Object>> _layoutList = (List<Map<String, Object>>) component.get("TabDatas");
                        for (Map<String, Object> map : _layoutList) {
                            OurwaySysLayout component1 = JsonUtil.map2Bean(map, OurwaySysLayout.class);
                            ourwaySysLayoutList.add(component1);
                        }
                    } else {
                        OurwaySysLayout component1 = JsonUtil.map2Bean(component, OurwaySysLayout.class);
                        //处理每个layout内的控件布局
                        if (null != component.get("dataList")) {
                            List<Map<String, Object>> layoutComponents = (List<Map<String, Object>>) component.get("dataList");
                            if (layoutComponents.size() <= 0) {
                                ourwaySysLayoutList.add(component1);
                                continue;
                            }
                            List<OurwaySysLayoutComponent> components1 = JsonUtil.map2List(layoutComponents, OurwaySysLayoutComponent.class);
                            if (null != components1 && components1.size() > 0)
                                component1.setComponents(components1);
                        }
                        ourwaySysLayoutList.add(component1);
                    }
                }
            }
        }
        //判断iintUrl是否唯一
        if (!pageService.doCheckUniquePageCA(ourwaySysPage)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "pageCA必须唯一");
        }

//        //保存或者修改
        pageService.savePageDetail(ourwaySysPage, ourwaySysPageComponentList, ourwaySysLayoutList);
        return ResponseMessage.sendOK(ourwaySysPage.getOwid());
    }

    @RequestMapping(value = "saveAsPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveAsPage(HttpServletRequest request, HttpServletResponse response,
                                      PublicDataVO data) {
        // json 转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysPage page = pageService.saveAsPage(mapData.get("owid").toString());
        return ResponseMessage.sendOK(page.getOwid());
    }


    /**
     * <p>方法:detailPage 获取当前页面对应的所有属性</p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param response TODO</li>
     * <li> @param data TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 22:48  </li>
     * </ul>
     */
    @RequestMapping(value = "listControls", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailPageControl(HttpServletRequest request, HttpServletResponse response,
                                             PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysPage page = pageService.queryOneByPageCA(mapData.get("pageCa").toString().trim());
        if (null != page && null != page.getPageCustomer() && page.getPageCustomer() == 1) {
            //表示自定义
            return ResponseMessage.sendOK(null);
        }
        if (null == page)
            return ResponseMessage.sendError(ResponseMessage.NOITEM, "");
        List<OurwaySysPageComponent> components = pageComponentService.listComponenetsByPage(page);
        return ResponseMessage.sendOK(components);
    }


    /**
     * <p>方法:detailPage 传入当前页面的owid，获取页面详情情况，跟界面进行绑定 </p>
     * <ul>
     * <li> @param request </li>
     * <li> @param response </li>
     * <li> @param data </li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/22 11:03  </li>
     * </ul>
     */
    @RequestMapping(value = "detailPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailPage(HttpServletRequest request, HttpServletResponse response,
                                      PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        if (null == filters || filters.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        }
        if (!filters.get(0).getKey().equalsIgnoreCase("owid"))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", filters.get(0).getDatas().get(0).toString());
        //获取page信息
        OurwaySysPage page = pageService.queryPage(params, "");
        //获取所有当前页面的component
        List<OurwaySysPageComponent> components = pageComponentService.listComponenetsByPage(page);
        //获取所有的layout信息
        List<OurwaySysLayout> layouts = layoutService.listAllByPage(page);
        List<OurwaySysLayout> _layouts = new ArrayList<OurwaySysLayout>();
        for (OurwaySysLayout layout : layouts) {
            //把是tab或者panel的树形挪出来
            if (null != layout.getControlType() && layout.getControlType().intValue() == -1) {
                if (null == page.getTabList())
                    page.setTabList(new ArrayList<OurwaySysLayout>());
                page.getTabList().add(layout);
                continue;
            } else {
                List<OurwaySysLayoutComponent> layoutComponents = layoutComponentService.listAllByParams(layout);
                layout.setComponents(layoutComponents);
                _layouts.add(layout);
            }
        }
        page.setComponents(components);
        page.setLayoutList(_layouts);
        return ResponseMessage.sendOK(page);
    }

    @RequestMapping(value = "listPages", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listPages(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(pageService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }

    @RequestMapping(value = "listAllPages", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllPages(HttpServletRequest request, HttpServletResponse response,
                                        PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(pageService.listAllPages(filters, " pageName "));
    }

    @RequestMapping(value = "removePage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removePages(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = pageService.removePage(owids);
        return ResponseMessage.sendOK(datas);
    }


    @RequestMapping(value = "detailPageByCa", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailPageByCa(HttpServletRequest request, HttpServletResponse response,
                                          PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        if (null == filters || filters.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        }
        if (!filters.get(0).getKey().equalsIgnoreCase("pageCa"))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("pageCa", filters.get(0).getDatas().get(0).toString());
        //获取page信息
        OurwaySysPage page = pageService.queryPage(params, "");
        //根据page获取指定模板页面的
        if (page.getPageCustomer() == 0) {
            OurwaySysTemplate template = templateService.detailTemplate(page.getPageTemplate());
            page.setPageTemplateLabel(template.getTemplateName());
            page.setPageTemplatePath(template.getTemplatePath());
        }
        return ResponseMessage.sendOK(page);
    }


    @RequestMapping(value = "writeSql", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage writeSql(HttpServletRequest request, HttpServletResponse response,
                                    PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        String fileName = pageService.writeContent(dataMap.get("owid").toString());
        return ResponseMessage.sendOK(fileName);
    }


    @RequestMapping(value = "listLinkByPageCA", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLinkByPageCA(HttpServletRequest request, HttpServletResponse response,
                                            PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysPage sysPage = pageService.queryOneByPageCA(dataMap.get("pageCa").toString());
        if (null==sysPage||null == sysPage.getPageTemplate())
            return ResponseMessage.sendOK(dataMap.get("pageCa").toString());
        OurwaySysTemplate template = templateService.detailTemplate(sysPage.getPageTemplate());
        if (null == template)
            return ResponseMessage.sendOK(template.getTemplatePath());
        return ResponseMessage.sendOK(dataMap.get("pageCa").toString());
    }

    //获取当前pageca下面的多语言标签
    @RequestMapping(value = "listLabelByCa", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLabelByCa(HttpServletRequest request, HttpServletResponse response,
                                            PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysPage sysPage = pageService.queryOneByPageCA(dataMap.get("pageCa").toString());
        if (null==sysPage||null == sysPage.getPageTemplate())
            return ResponseMessage.sendOK(sysPage.getPageCa());
        return ResponseMessage.sendOK(pageComponentService.listComponenetsByPage(sysPage));

    }
}
