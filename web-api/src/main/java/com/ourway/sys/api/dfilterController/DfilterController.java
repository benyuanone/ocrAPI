package com.ourway.sys.api.dfilterController;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDfilter;
import com.ourway.sys.service.DfilterService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>DfilterController</dt>
 * <dd>Description:  </dd>
 * <dd>Copyright: Copyright (C) 2017</dd>
 * <dd>Company:步长科技有限公司</dd>
 * <dd>CreateDate: 2017/5/31 0031</dd>
 * </dl>
 *
 * @author xby
 */
@RequestMapping(value = "/apiDfilter")
@Controller
public class DfilterController {

    @Autowired
    DfilterService dfilterService;


    /*
    *<p>功能描述：saveOrUpdateDfilter  新增、修改</p >
    *<ul>
    *<li>@param [request, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 下午 7:47</li>
    *</ul>
    */
    @RequestMapping(value = "/saveOrUpdateDfilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdateDfilter(HttpServletRequest request, PublicDataVO dataVO) {
        //json to map
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());

        //判断
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageCa");

        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        /*map to bean*/
        OurwaySysDfilter ourwaySysDfilter = JsonUtil.map2Bean(dataMap, OurwaySysDfilter.class);
        /*存入数据库*/
        dfilterService.saveOrUpdate(ourwaySysDfilter);

        return ResponseMessage.sendOK(ourwaySysDfilter);

    }


    /*
    *<p>功能描述：listDfilterForPage  分页显示</p >
    *<ul>
    *<li>@param [request, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 下午 7:52</li>
    *</ul>
    */
    @RequestMapping(value = "/listDfilterForPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listDfilterForPage(HttpServletRequest request, PublicDataVO dataVO) {

        List<FilterModel> flist = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);

        return ResponseMessage.sendOK(dfilterService.listDfilterForPage(flist, dataVO.getPageNo(), dataVO.getPageSize(), ""));
    }


    @RequestMapping(value = "/detailOneDfilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailOneDfilter(HttpServletRequest request, PublicDataVO dataVO) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());

        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        /*根据owid 查询记录*/
        return ResponseMessage.sendOK(dfilterService.detailOneDfilter(dataMap.get("owid").toString()));
    }


    /*
    *<p>功能描述：removeDifilter  根据owid 删除数据过滤</p >
    *<ul>
    *<li>@param [request, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 下午 7:56</li>
    *</ul>
    */
    @RequestMapping(value = "/removeDifilter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeDifilter(HttpServletRequest request, PublicDataVO dataVO) {

        //判断data 是否为空
        if (TextUtils.isEmpty(dataVO.getData())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, dataVO.getCurrLanguage()));
        }

        JSONArray jsonArray = JSONArray.fromObject(dataVO.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> lists = dfilterService.removeItems(owids);

        return ResponseMessage.sendOK(lists);
    }


}
