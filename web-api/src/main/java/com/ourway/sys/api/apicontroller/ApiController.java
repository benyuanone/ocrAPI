package com.ourway.sys.api.apicontroller;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.service.ApiDetailService;
import com.ourway.sys.service.ApiService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
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
 * <dl>
 * <dt>ApiController 系统接Api controller</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2017/4/24 0024</dd>
 * </dl>
 *
 * @author xby
 */

@Controller
@RequestMapping("sysApiManage")
public class ApiController {

    @Autowired
    ApiService apiService;

    @Autowired
    ApiDetailService apiDetailService;

    /*
    *<p>功能描述：saveOrUpdateApi 保存系统api接口信息主表</p >
    *<ul>
    *<li>@param []</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 1:14</li>
    *</ul>
    */

    @RequestMapping(value = "saveApi", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveApi(HttpServletRequest request, HttpServletResponse response,
                                   PublicDataVO data) {
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "intUrl", "intFunc", "intName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        //解析 api 和子表信息
        OurwaySysApi ourwaySysApi = JsonUtil.map2Bean(mapData, OurwaySysApi.class);

        List<OurwaySysApiDetail> inDetail = null;
        if (null != mapData.get("dataList1")) {
            List<Map<String, Object>> temp = (List<Map<String, Object>>) mapData.get("dataList1");
            inDetail = JsonUtil.map2List(temp, OurwaySysApiDetail.class);
        }
        List<OurwaySysApiDetail> outDetail = null;
        if (null != mapData.get("dataList2")) {
            List<Map<String, Object>> temp = (List<Map<String, Object>>) mapData.get("dataList2");
            outDetail = JsonUtil.map2List(temp, OurwaySysApiDetail.class);
        }

        //判断iintUrl是否唯一
        if (!apiService.doCheckUniqueUrl(ourwaySysApi)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "接口url必须唯一");
        }

        //保存或者修改
        ourwaySysApi = apiService.saveOrUpdateApi(ourwaySysApi, inDetail, outDetail);

        /*？*/
        return ResponseMessage.sendOK(ourwaySysApi);
    }

    /*
    *<p>功能描述：detailApi  不分页显示Api（连带查询 从表信息）</p >
    *<ul>
    *<li>@param [request, response, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 3:49</li>
    *</ul>
    */
    @RequestMapping(value = "detailApi", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailApi(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(apiService.detailApi(mapData.get("owid").toString()));
    }


    /*
    *<p>功能描述：listApi  分页显示 api</p >
    *<ul>
    *<li>@param [response, request, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 5:21</li>
    *</ul>
    */
    @RequestMapping(value = "listApi", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listApi(HttpServletResponse response, HttpServletRequest request,
                                   PublicDataVO dataVO) {
        List<FilterModel> filterModelList = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        return ResponseMessage.sendOK(apiService.listApiForPage(filterModelList, dataVO.getPageNo(), dataVO.getPageSize()));

    }

    @RequestMapping(value = "listApiParams/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listApiParams(@PathVariable("type") Integer type, HttpServletResponse response, HttpServletRequest request,
                                         PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(apiDetailService.listAllApiDetailByOwid(mapData.get("owid").toString(), new Byte(type + "")));
    }


    /*
    *<p>功能描述： 删除 api（连带从表信息也删除）</p >
    *<ul>
    *<li>@param [request, response, dataVo]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 3:00</li>
    *</ul>
    */
    @RequestMapping(value = "removeApi", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeApi(HttpServletRequest request, HttpServletResponse response, PublicDataVO dataVo) {
        if (TextUtils.isEmpty(dataVo.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,dataVo.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> data = apiService.removeApiByIds(owids);

        return ResponseMessage.sendOK(data);
    }

    /*
    *<p>功能描述：saveOrUpdateApiDetail  保存或者更新 apidetail</p >
    *<ul>
    *<li>@param [request, response, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 7:26</li>
    *</ul>
    */
    @RequestMapping(value = "saveApiDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveApiDetail(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO dataVO) {
        //json to map
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());

        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "apiRefOwid");
        //判断 apiRefOwid 是否为空
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        // map to enity
        OurwaySysApiDetail ourwaySysApiDetail = JsonUtil.map2Bean(dataMap, OurwaySysApiDetail.class);

        //保存到数据库
        apiDetailService.saveOrUpdateApiDetail(ourwaySysApiDetail);

        return ResponseMessage.sendOK(ourwaySysApiDetail);

    }


    /*
    *<p>功能描述：removeApiDetail  删除从表信息</p >
    *<ul>
    *<li>@param [request, response, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/25 0025 上午 9:14</li>
    *</ul>
    */
    @RequestMapping(value = "removeApiDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeApiDetail(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO dataVO) {
        //json数组  to list
        List<FilterModel> filterModelList = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        HqlStatement hqlStatement = new HqlStatement("from OurwaySysApiDetail", filterModelList);
        apiDetailService.removeApiDetail(hqlStatement);
        return ResponseMessage.sendOK(null);
    }

    //树形列表
    @RequestMapping(value = "listTreeApi", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTreeApi(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(apiService.listApiTree(filters));
    }
}
