package com.ourway.erpcustoms.api;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpcustoms.service.ErpShipBlacklistService;
import com.ourway.erpcustoms.model.ErpShipBlacklist;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*<p>方法 ErpShipBlacklistController : <p>
*<p>说明:船舶黑名单controller</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-08 13:57
</pre>
*/
@Controller
@RequestMapping("shipBlacklistApi")
public class ErpShipBlacklistController{

    @Autowired
    private ErpShipBlacklistService erpShipBlacklistService;
    @Autowired
    FilesService filesService;

    /*<p>方法: 船舶黑名单grid</p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return 船舶黑名单PageInfo对象 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-08 14:01  </li>
    *</ul>
    */
    @RequestMapping("listErpShipBlacklist")
    @ResponseBody
    public ResponseMessage listErpShipBlacklist(HttpServletRequest request, PublicDataVO data){
        List<FilterModel> filterModels = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        for (FilterModel filterModel:filterModels) {
            if ("state".equals(filterModel.getKey())) {
                List<Object> _list = new ArrayList<Object>();
                for (Object o:filterModel.getDatas()) {
                    _list.add(Integer.parseInt((String) o));
                }
                filterModel.setDatas(_list);
            }
        }
        PageInfo<ErpShipBlacklist> pageInfo = erpShipBlacklistService.listErpShipBlacklist(filterModels,data.getPageNo(),data.getPageSize(),"eb.createtime desc");
        return ResponseMessage.sendOK(pageInfo);
    }

    /*<p>方法: 船舶黑名单详情 </p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-08 14:03  </li>
    *</ul>
    */
    @RequestMapping("detailErpShipBlacklist")
    @ResponseBody
    public ResponseMessage detailErpShipBlacklist(HttpServletRequest request, PublicDataVO data){
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        Map<String,Object> newDataMap = new HashMap<String,Object>();
        //所有搜索条件都是主表字段
        for (String key : dataMap.keySet()) {
            //
            if ("owid".equals(key)) {
                newDataMap.put("eb."+key, dataMap.get(key));
                break;
            }
        }
        return ResponseMessage.sendOK(erpShipBlacklistService.detailErpShipBlacklist(newDataMap));
    }

    @RequestMapping("historyErpShipBlacklist")
    @ResponseBody
    public ResponseMessage historyErpShipBlacklist(HttpServletRequest request, PublicDataVO data){
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK(erpShipBlacklistService.historyErpShipBlacklist(dataMap));
    }

    /*<p>方法: 船舶黑名单保存 </p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-08 14:05  </li>
    *</ul>
    */
    @RequestMapping("saveErpShipBlacklist")
    @ResponseBody
    public ResponseMessage saveErpShipBlacklist(HttpServletRequest request, PublicDataVO data){
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        Map<String, String> validateKeyMap = new HashMap<String, String>();
        //多语言相关
        validateKeyMap.put(I18nUtils.getLanguageContent("UI.LBL.ErpShipBlacklist.shipOwid.01",data.getCurrLanguage()), "erpshipRefOwid");
        //非空校验
        ValidateMsg validateMsg = ValidateUtils.isEmpty2(dataMap, validateKeyMap);
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }

        if (!TextUtils.isEmpty(dataMap.get("erpshipRefOwid"))) {
            if (dataMap.get("erpshipRefOwid") instanceof Map) {
                //新增时
                dataMap.put("erpshipRefOwid",((Map)dataMap.get("erpshipRefOwid")).get("owid"));
            }
            if (dataMap.get("erpshipRefOwid") instanceof String) {
                //修改时
                if (!TextUtils.isEmpty(dataMap.get("erpShip"))) {
                    //修改时会将erpShip对象带过来
                    if (dataMap.get("erpShip") instanceof Map) {
                        dataMap.put("erpshipRefOwid",((Map)dataMap.get("erpShip")).get("owid"));
                        ErpShip erpShip = JsonUtil.map2Bean((Map)dataMap.get("erpShip"),ErpShip.class);
                        dataMap.put("erpShip",erpShip);
                    }
                }
            }
        }
        ErpShipBlacklist erpShipBlacklist = JsonUtil.map2Bean(dataMap, ErpShipBlacklist.class);
        if (!TextUtils.isEmpty(dataMap.get("addToBlacklistFlag")) && (Boolean) dataMap.get("addToBlacklistFlag")) {
            //如果点击过“加入黑名单”后保存，应该insert数据。区别于“新增”、“有效”状态后修改保存，此时应将owid设为null
            erpShipBlacklist.setState(0);
            erpShipBlacklist.setOwid(null);
        }
        erpShipBlacklistService.saveErpShipBlacklist(erpShipBlacklist);

        if(!TextUtils.isEmpty(dataMap.get("fileExtId"))&&erpShipBlacklist.getOwid().equalsIgnoreCase(dataMap.get("fileExtId").toString()))
            filesService.saveOrUpdate(erpShipBlacklist.getOwid(),dataMap.get("fileExtId").toString());
        return ResponseMessage.sendOK(erpShipBlacklist);
    }

    /*<p>方法: 船舶黑名单删除 </p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-11 14:10  </li>
    *</ul>
    */
    @RequestMapping("removeErpShipBlacklist")
    @ResponseBody
    public ResponseMessage removeErpShipBlacklist(HttpServletRequest request, PublicDataVO data){
        //json转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //根据owId获取对象
        Map<String, Object> _mapData = new HashMap<String, Object>();
        _mapData.put("eb.owid", mapData.get("owid"));
        Map<String,Object> erpShipBlacklist = erpShipBlacklistService.detailErpShipBlacklist(_mapData);
        erpShipBlacklistService.removeErpShipBlacklist(erpShipBlacklist);

        return ResponseMessage.sendOK(null);
    }

    /*<p>方法: 员工数据分页列表 </p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return 员工数据PageInfo对象 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-11 8:49  </li>
    *</ul>
    */
    @RequestMapping("listSysEmploys")
    @ResponseBody
    public ResponseMessage listSysEmploys(HttpServletRequest request, PublicDataVO data){
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        PageInfo<OurwaySysEmploys> sysEmploysPageInfo = erpShipBlacklistService.listSysEmploys(filters,data.getPageNo(),data.getPageSize(),"");
        return ResponseMessage.sendOK(sysEmploysPageInfo);
    }

}
