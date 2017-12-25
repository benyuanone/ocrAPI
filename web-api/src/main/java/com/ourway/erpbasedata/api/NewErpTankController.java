package com.ourway.erpbasedata.api;

import com.ourway.ERPTipsConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.erpbasedata.service.ErpTankListService;
import com.ourway.erpbasedata.service.ErpTankService;
import com.ourway.sys.Constants;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
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
 * <p>接口 TankController.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-05-31 11:34
 * </pre>
 */

@Controller
@RequestMapping("ertTankApi")
public class NewErpTankController {
    @Autowired
    ErpTankService erpTankService;

    @Autowired
    ErpTankListService erpTankListService;
    @Autowired
    FilesService filesService;

    /*显示erptank的列表*/
    @RequestMapping(value = "listTanks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listPages(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(erpTankService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }

    @RequestMapping(value = "listTankList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTankList(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        for (FilterModel filterModel:filterModels) {
            dataMap.put(filterModel.getKey(),filterModel.getDatas());
        }
        List<ErpTank> erpTankList = erpTankService.listTankList(dataMap
                ,dataMap.get("sortStr")==null?"":dataMap.get("sortStr").toString());
        return ResponseMessage.sendOK(erpTankList);
    }

    /**
     * <p>方法:saveApi TODO </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param response TODO</li>
     * <li> @param data TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author zhangxinyi </li>
     * <li>@date 2017-06-05 17:19  </li>
     * </ul>
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveApi(HttpServletRequest request, HttpServletResponse response,
                                   PublicDataVO data) {
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "tankId", "safeCapacity", "safeHeight");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        if (!TextUtils.isEmpty(mapData.get("dataList")) && ((List)mapData.get("dataList")).size()>0) {
            for(Map<String,Object> _map:(List<Map<String,Object>>) mapData.get("dataList")){
                _map.put("erpgoodsRefOwid",((Map<String,Object>)_map.get("erpgoodsRefOwid")).get("owid"));
            }
        }

        //解析 api 和子表信息
        ErpTank erpTank = JsonUtil.map2Bean(mapData, ErpTank.class);

        List<ErpTankList> erpTankListList = null;

        if (null != mapData.get("dataList")) {
            List<Map<String, Object>> temp = (List<Map<String, Object>>) mapData.get("dataList");
            erpTankListList = JsonUtil.map2List(temp, ErpTankList.class);
        }

        //判断iintUrl是否唯一
        if (!erpTankService.doCheckUniqueUrl(erpTank)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(ERPTipsConstants.TANKID_UNIQUE,data.getCurrLanguage()));
        }

        //保存或者修改
        erpTank = erpTankService.saveOrUpdateErpTank(erpTank, erpTankListList);
        if(!TextUtils.isEmpty(mapData.get("fileExtId"))&&erpTank.getOwid().equalsIgnoreCase(mapData.get("fileExtId").toString()))
            filesService.saveOrUpdate(erpTank.getOwid(),mapData.get("fileExtId").toString());
        return ResponseMessage.sendOK(erpTank);
    }


    /**
     * <p>方法:detailErpTank 显示从表信息 </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param response TODO</li>
     * <li> @param dataVO TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author zhangxinyi </li>
     * <li>@date 2017-06-01 14:03  </li>
     * </ul>
     */
    @RequestMapping(value = "detailErpTank", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailErpTank(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpTankService.detailErpTank(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "listErtTankList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpTankList(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpTankListService.listAllTankListByTankId(mapData.get("owid").toString()));
    }


    /**
     * <p>方法:removeErpTank 删除 </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param response TODO</li>
     * <li> @param dataVo TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author zhangxinyi </li>
     * <li>@date 2017-06-01 14:10  </li>
     * </ul>
     */
    @RequestMapping(value = "removeErpTank", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeErpTank(HttpServletRequest request, HttpServletResponse response, PublicDataVO dataVo) {

        if (TextUtils.isEmpty(dataVo.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,dataVo.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> data = erpTankService.removeErpTankByIds(owids);

        return ResponseMessage.sendOK(data);
    }


    /**
     * <p>方法:saveErpTankList 保存|更新 </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param response TODO</li>
     * <li> @param dataVO TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author zhangxinyi </li>
     * <li>@date 2017-06-01 14:11  </li>
     * </ul>
     */
    @RequestMapping(value = "saveErpTankList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveErpTankList(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO dataVO) {
        //json to map
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());

        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "erptankRefOwid");
        //判断 apiRefOwid 是否为空
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        // map to enity
        ErpTankList erpTankList = JsonUtil.map2Bean(dataMap, ErpTankList.class);

        //保存到数据库
        erpTankListService.saveOrUpdateErpTankList(erpTankList);

        return ResponseMessage.sendOK(erpTankList);

    }


    /**
     * <p>方法:removeErpTankList 删除从表 </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param response TODO</li>
     * <li> @param dataVO TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author zhangxinyi </li>
     * <li>@date 2017-06-01 14:14  </li>
     * </ul>
     */
    @RequestMapping(value = "removeErpTankList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeErpTankList(HttpServletRequest request, HttpServletResponse response,
                                             PublicDataVO dataVO) {
        //json数组  to list
        List<FilterModel> filterModelList = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);

        HqlStatement hqlStatement = new HqlStatement("from ErpTankList", filterModelList);
        erpTankListService.removeErpTankList(hqlStatement);
        return ResponseMessage.sendOK(null);

    }

    @RequestMapping(value = "listErpTankByLibraryArea", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpTankByLibraryArea(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        List<Object> objs = erpTankService.listErpTankByLibraryArea(filters);
        for(Object obj:objs){
           Map<String,Object> map = new HashMap(3);
           map.put("label",obj.toString());
           map.put("value",obj.toString());
           map.put("type",2);
           result.add(map);
        }
        return ResponseMessage.sendOK(result);
    }

}
