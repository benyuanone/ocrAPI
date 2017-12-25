package com.ourway.erpbasedata.api;

import com.ourway.ERPTipsConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.erpbasedata.model.ErpSetChargeway;
import com.ourway.erpbasedata.model.ErpSetChargewayList;
import com.ourway.erpbasedata.service.ErpSetChargewayListService;
import com.ourway.erpbasedata.service.ErpSetChargewayService;
import com.ourway.sys.Constants;
import com.ourway.sys.service.FilesService;
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

/***
 *<p>方法: TODO </p>
 *<ul>
 *<li> @param null TODO</li>
 *<li>@return   </li>
 *<li>@author zhangxinyi </li>
 *<li>@date 2017-09-27 8:55  </li>
 *</ul>
 */
@Controller
@RequestMapping("setChargewayApi")
public class ErpSetChargewayController {


    @Autowired
    ErpSetChargewayService erpSetChargewayService;

    @Autowired
    ErpSetChargewayListService erpSetChargewayListService;
    @Autowired
    FilesService filesService;


    /***<p>方法 ErpSetChargewayController : <p>
     *<p>说明:TODO</p>
     *<pre>
     *@author zhangxinyi
     *@date 2017-09-29 0:31
    </pre>
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveApi(HttpServletRequest request, HttpServletResponse response,
                                   PublicDataVO data) {
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "chargewayId", "chargewayName", "chargeType","generateTime");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        if (!TextUtils.isEmpty(mapData.get("generateTime")) && mapData.get("generateTime") instanceof Map) {
            mapData.put("generateTime",Integer.parseInt(((Map)(mapData.get("generateTime"))).get("path").toString()));
        }
        if (!TextUtils.isEmpty(mapData.get("chargetypeName")) && mapData.get("chargetypeName") instanceof Map) {
            mapData.put("chargetypeName",Integer.parseInt(((Map)(mapData.get("chargetypeName"))).get("owid").toString()));
        }
        if (!TextUtils.isEmpty(mapData.get("chargeType"))) {
            mapData.put("chargeType",Integer.parseInt(mapData.get("chargeType").toString()));
        }
        if (!TextUtils.isEmpty(mapData.get("dataList")) && ((List)mapData.get("dataList")).size()>0) {
            for(Map<String,Object> _map:(List<Map<String,Object>>) mapData.get("dataList")){
                _map.put("chargewayListId",((Map<String,Object>)_map.get("chargewayListId")).get("owid"));
            }
        }
        //解析 api 和子表信息
        ErpSetChargeway erpSetChargeway = JsonUtil.map2Bean(mapData, ErpSetChargeway.class);

        List<ErpSetChargewayList> erpSetChargewayLists = null;

        if (null != mapData.get("dataList")) {
            List<Map<String, Object>> temp = (List<Map<String, Object>>) mapData.get("dataList");
            erpSetChargewayLists = JsonUtil.map2List(temp, ErpSetChargewayList.class);
        }

        //判断iintUrl是否唯一
        if (!erpSetChargewayService.doCheckUniqueUrl(erpSetChargeway)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(ERPTipsConstants.TANKID_UNIQUE,data.getCurrLanguage()));
        }

        //保存或者修改
        erpSetChargeway = erpSetChargewayService.saveOrUpdateErpSetChargeway(erpSetChargeway,erpSetChargewayLists);
        if(!TextUtils.isEmpty(mapData.get("fileExtId"))&&erpSetChargeway.getOwid().equalsIgnoreCase(mapData.get("fileExtId").toString()))
            filesService.saveOrUpdate(erpSetChargeway.getOwid(),mapData.get("fileExtId").toString());
        return ResponseMessage.sendOK(erpSetChargeway);
    }


    /***
     *<p>方法:listErpSetChargeway TODO </p>
     *<ul>
     *<li> @param request TODO</li>
     *<li> @param data TODO</li>
     *<li>@return com.ourway.base.model.ResponseMessage  </li>
     *<li>@author zhangxinyi </li>
     *<li>@date 2017-09-28 21:10  </li>
     *</ul>
     */
    @RequestMapping(value = "listErpSetChargeway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpSetChargeway(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(erpSetChargewayService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }


    /***<p>方法 ErpSetChargewayController : <p>
     *<p>说明:detailEetChargeway</p>
     *<pre>
     *@author zhangxinyi
     *@date 2017-09-28 16:24
    </pre>
     */
    @RequestMapping(value = "detailEetChargeway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailEetChargeway(HttpServletRequest request, HttpServletResponse response,
                                              PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpSetChargewayService.detailErpSetChargeway(mapData.get("owid").toString()));
    }


    /***
     *<p>方法:removeEetChargeway TODO </p>
     *<ul>
     *<li> @param request TODO</li>
     *<li> @param response TODO</li>
     *<li> @param dataVo TODO</li>
     *<li>@return com.ourway.base.model.ResponseMessage  </li>
     *<li>@author zhangxinyi </li>
     *<li>@date 2017-09-28 16:46  </li>
     *</ul>
     */
    @RequestMapping(value = "removeEetChargeway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeEetChargeway(HttpServletRequest request, HttpServletResponse response, PublicDataVO dataVo) {

        if (TextUtils.isEmpty(dataVo.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,dataVo.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> data = erpSetChargewayService.removeErpSetChargewayByIds(owids);

        return ResponseMessage.sendOK(data);
    }

    @RequestMapping(value = "listErpShipList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpTankList(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpSetChargewayListService.listAllSetChargewayListByChargewayId(mapData.get("owid").toString()));
    }


}