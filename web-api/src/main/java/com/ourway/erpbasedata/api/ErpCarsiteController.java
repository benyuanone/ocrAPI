package com.ourway.erpbasedata.api;

import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.erpbasedata.model.ErpCarsite;
import com.ourway.erpbasedata.model.ErpEmployee;
import com.ourway.erpbasedata.service.ErpCarsiteService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSON;
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

/*<p>方法 CarsiteController : <p>
*<p>说明:装车台controller</p>
*<pre>
*@author zhou_xtian
*@date 2017-05-08 10:47
</pre>
*/
@Controller
@RequestMapping("carsiteApi")
public class ErpCarsiteController {

    @Autowired
    private ErpCarsiteService erpCarsiteService;

    /*
    *<p>方法: 保存 </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 19:31  </li>
    *</ul>
    */
    @RequestMapping(value = "saveErpCarsite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveCarsite(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        Map<String, String> validateKeyMap = new HashMap<String, String>();
        //多语言相关
        validateKeyMap.put(I18nUtils.getLanguageContent("UI.LBL.ErpCarsite.carsiteId.01",data.getCurrLanguage()), "carsiteId");
        validateKeyMap.put(I18nUtils.getLanguageContent("UI.LBL.ErpCarsite.carnePlace.01",data.getCurrLanguage()), "carnePlace");
        validateKeyMap.put(I18nUtils.getLanguageContent("UI.LBL.ErpCarsite.tankList.01",data.getCurrLanguage()), "tankList");
        //非空校验
        ValidateMsg validateMsg = ValidateUtils.isEmpty2(dataMap, validateKeyMap);
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        //转化为实体类并保存
        /*
         *这里如果直接用ErpCarsite erpCarsite = JsonUtil.map2List(dataMap, ErpCarsite.class)获取实体类，
         *则会报argument。。错误，得用这个方法反复操作。
         */
        //负责人bandbox转换
        if (!TextUtils.isEmpty(dataMap.get("erpemployeeRefOwid"))) {
            if (dataMap.get("erpemployeeRefOwid") instanceof Map) {
                dataMap.put("erpemployeeRefOwid",((Map)dataMap.get("erpemployeeRefOwid")).get("owid"));
            }
        }
        //罐号bandbox转换
        if (!TextUtils.isEmpty(dataMap.get("tankList")) && ((List)dataMap.get("tankList")).size()>0) {
            StringBuffer tankListStr = new StringBuffer("");
            for (Map<String,Object> _map:(List<Map<String,Object>>)dataMap.get("tankList")) {
                if (tankListStr.length() > 0) {
                    tankListStr.append(",");
                }
                tankListStr.append(_map.get("tankId"));
            }
            dataMap.put("tankList",tankListStr);
        }
        List<Map<String, Object>> temp = new ArrayList<Map<String, Object>>();
        temp.add(dataMap);
        ErpCarsite erpCarsite = JsonUtil.map2List(temp, ErpCarsite.class).get(0);
        erpCarsiteService.saveOrUpdate(erpCarsite);
        return ResponseMessage.sendOK(erpCarsite);
    }

    /*
    *<p>方法: 删除 </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 19:30  </li>
    *</ul>
    */
    @RequestMapping(value = "removeErpCarsite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeCarsite(HttpServletRequest request, PublicDataVO data) {
        //json转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //根据owId获取对象
        Map<String, Object> _mapData = new HashMap<String, Object>();
        _mapData.put("er.owid", mapData.get("owid"));
        ErpCarsite erpCarsite = erpCarsiteService.detailErpCarsite(_mapData);

        //TODO
        // ---------------这里需要判断XXX、XXX、XXX表内是否使用，若使用则不可删除
        if (1 == 2) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "已使用，不可删除");
        }
        erpCarsiteService.removeErpCarsite(erpCarsite);
        return ResponseMessage.sendOK(null);
    }

    /*
    *<p>方法: 查询（列表） </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 19:36  </li>
    *</ul>
    */
    @RequestMapping(value = "listErpCarsite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpCarsite(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(erpCarsiteService.listErpCarsite(filters, data.getPageNo(), data.getPageSize(), ""));
    }


    /*
    *<p>方法: 查询（单条） </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 19:32  </li>
    *</ul>
    */
    @RequestMapping(value = "detailCarsite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailCarsite(HttpServletRequest request, PublicDataVO data) {
        //json转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //获取对象
        Map<String, Object> _map = new HashMap<String, Object>();
        _map.put("er.owid",mapData.get("owid"));
        ErpCarsite erpCarsite = erpCarsiteService.detailErpCarsite(_map);
        return ResponseMessage.sendOK(erpCarsite);
    }

    /*
    *<p>方法: 装车台树查询 </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 15:50  </li>
    *</ul>
    */
    @RequestMapping(value = "treeErpCarsite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage treeErpCarsiteId(HttpServletRequest request, PublicDataVO data) {
        List<ErpCarsite> erpCarsiteList = erpCarsiteService.listAllErpCarsite();
        List<BaseTree> baseTrees = new ArrayList<BaseTree>(erpCarsiteList.size());
        int index = 0;
        for (ErpCarsite erpCarsite : erpCarsiteList) {
            erpCarsite.setCarsiteIdRefId(erpCarsite.getCarsiteIdRefId()+index);
            BaseTree _baseTree = TreeUtils.convert(erpCarsite, new String[]{"carsiteIdRefId-owid", "carsiteIdRefFId-fid", "carsiteId-name", "carsiteId-path"});
            //添加不重复树数据
            List<String> pathList = new ArrayList<String>();
            for (BaseTree baseTree : baseTrees) {
                pathList.add(baseTree.getPath());
            }
            if (pathList.size() == 0 || !pathList.contains(_baseTree.getPath())) {
                baseTrees.add(_baseTree);
            }
            index ++;
        }
        return ResponseMessage.sendOK(baseTrees);
    }

    /*
    *<p>方法: 历史车道查询 </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 19:35  </li>
    *</ul>
    */
    @RequestMapping(value = "listHisRoadName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listHisRoadName(HttpServletRequest request, PublicDataVO data) {
        //定义排序规则
        String[] sortIdArray = {"createtime"};
        //获取排序好的carsite数据
        List<ErpCarsite> erpCarsiteList = erpCarsiteService.listAllErpCarsite(sortIdArray, "desc");
        //取出道路字段
        List<String> roadNameList = new ArrayList<String>();
        for (ErpCarsite erpCarsite : erpCarsiteList) {
            roadNameList.add(erpCarsite.getRoadName());
        }
        return ResponseMessage.sendOK(roadNameList);
    }
}