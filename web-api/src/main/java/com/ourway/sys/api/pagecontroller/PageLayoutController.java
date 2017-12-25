package com.ourway.sys.api.pagecontroller;

import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;
import com.ourway.sys.service.LayoutComponentService;
import com.ourway.sys.service.LayoutService;
import com.ourway.sys.service.PageComponentService;
import com.ourway.sys.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 布局控件页面 </p>
 * <ul>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/8 23:08  </li>
 * </ul>
 */
@Controller
@RequestMapping("sysPageLayoutApi")
public class PageLayoutController {
    @Autowired
    LayoutService layoutService;


    @RequestMapping(value = "detailPageControl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailPageControl(HttpServletRequest request, HttpServletResponse response,
                                             PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "componentId","pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysLayout layout = layoutService.queryOneByPageCaAndControlId(mapData.get("pageCa").toString(),mapData.get("componentId").toString());
        return ResponseMessage.sendOK(layout);
    }



    @RequestMapping(value = "detailTabPageControl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailTabPageControl(HttpServletRequest request, HttpServletResponse response,
                                             PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        List<OurwaySysLayout> layouts = layoutService.queryAllTabPanelLayouts(mapData.get("pageCa").toString());
        return ResponseMessage.sendOK(layouts);
    }
    @RequestMapping(value = "detailDataListPageControl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailDataListPageControl(HttpServletRequest request, HttpServletResponse response,
                                             PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "componentId","pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        List<OurwaySysLayout> layouts = layoutService.queryAllDataListLayouts(mapData.get("pageCa").toString(),mapData.get("componentId").toString());
        return ResponseMessage.sendOK(layouts);
    }


    @RequestMapping(value = "detailPageControlWithoutComponent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailPageControlWithoutComponent(HttpServletRequest request, HttpServletResponse response,
                                             PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "componentId","pageCa");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysLayout layout = layoutService.queryOneByPageCaAndControlId(mapData.get("pageCa").toString(),mapData.get("componentId").toString());
        return ResponseMessage.sendOK(layout);
    }



}
