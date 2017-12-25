package com.ourway.erpbasedata.api;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.model.ErpGoodsList;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.service.ErpGoodsListService;
import com.ourway.erpbasedata.service.ErpGoodsService;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
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

/***<p>方法 erpGoodsController : <p>
 *<p>说明:TODO</p>
 *<pre>
 *@author CuiLiang
 *@date 2017-05-08 15:14
</pre>
 */
@Controller
@RequestMapping("erpGoodsApi")
public class ErpGoodsController {

    @Autowired
    ErpGoodsService erpGoodsService;

    @Autowired
    ErpGoodsListService erpGoodsListService;

    @Autowired
    FilesService filesService;

    @RequestMapping(value = "saveErpGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveErpGoods(HttpServletRequest request, PublicDataVO dataVO) {
        //获取参数
        Map<String, Object> map = JsonUtil.jsonToMap(dataVO.getData());
        //提示性校验合法性
        ValidateMsg msg = erpGoodsService.Validate(dataVO,map,"goodsId");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("goodsId",dataVO) + erpGoodsService.getDbMsg("0001",dataVO));
        }
        msg = ValidateUtils.isEmpty(map, "name");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("name",dataVO) + erpGoodsService.getDbMsg("0001",dataVO));
        }
        ErpGoods erpGoods = JsonUtil.map2Bean(map, ErpGoods.class);

        List<ErpGoodsList> erpGoodsList = new ArrayList<ErpGoodsList>();
        ErpGoodsList    erpGoodsList1 = new ErpGoodsList();
        ErpGuest erpGuest = new ErpGuest();
        List<Map<String, Object>> tmpLists =(List<Map<String, Object>>) map.get("dataList");
        for(Map<String, Object> tmplist :tmpLists){
            erpGoodsList1 = JsonUtil.map2Bean((Map<String, Object>) tmplist.get("erpgoodslist"),ErpGoodsList.class);
            erpGuest = JsonUtil.map2Bean((Map<String, Object>) ((Map<String, Object>) tmplist.get("erpgoodslist")).get("erpGuest"),ErpGuest.class);
//            erpGoodsList1.setErpguestRefOwid(tmplist.get("erpguestRefOwid").toString());
            erpGoodsList1.setUpdateFlag(Integer.valueOf(tmplist.get("updateFlag").toString()));
            erpGoodsList1.setOwid(tmplist.get("owid").toString()=="null"?null:tmplist.get("owid").toString());
            erpGoodsList1.setIndexno(Integer.valueOf(tmplist.get("indexno").toString()));
            erpGoodsList.add(erpGoodsList1);
        }
//        if (null != tmpLists) {
//            erpGoodsList = JsonUtil.map2List(tmpLists.get(""), ErpGoodsList.class);
//        }

        List<OurwaySysDicValue> sysDicValues = new ArrayList<OurwaySysDicValue>();
        tmpLists =(List<Map<String, Object>>) JsonUtil.jsonStr2List(erpGoods.getGoodsProperty());
        String tmpMulStr="";
        if ((null != erpGoods.getGoodsProperty())&&(tmpLists.size()>0)){
            sysDicValues = JsonUtil.map2List(tmpLists,OurwaySysDicValue.class);
            for (OurwaySysDicValue sysDicValue : sysDicValues) {
                tmpMulStr += sysDicValue.getOwid()+",";
            }
        }
        if (!tmpMulStr.equals("")) {
            erpGoods.setGoodsProperty(tmpMulStr.substring(0, tmpMulStr.length() - 1));
        }

        tmpLists =(List<Map<String, Object>>) JsonUtil.jsonStr2List(erpGoods.getCredentials());
        tmpMulStr="";
        if ((null != erpGoods.getCredentials())&&(tmpLists.size()>0)){
            sysDicValues = JsonUtil.map2List(tmpLists,OurwaySysDicValue.class);
            for (OurwaySysDicValue sysDicValue : sysDicValues) {
                tmpMulStr += sysDicValue.getOwid()+",";
            }
        }
        if (!tmpMulStr.equals("")) {
            erpGoods.setCredentials(tmpMulStr.substring(0, tmpMulStr.length() - 1));
        }

        erpGoods.setDataList(erpGoodsList);
        //检查goodsId的唯一性
        if (!erpGoodsService.doCheckUniqueByGoodsId(erpGoods)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("erpgoodsId",dataVO) + erpGoodsService.getDbMsg("0002",dataVO));
        }
        //数据规整校验
        msg = ValidateUtils.isEmpty(map, "goodsProperty");
        if (!msg.getSuccess()){
            //判断产品性质是否包含危险品
            if (!erpGoods.getGoodsProperty().contains("TP.ErpGoods.goodsProperty.01")) {
                erpGoods.setDangerLevel("");
            }
        }
        //保存
        erpGoodsService.saveOrUpdate(erpGoods);
        if(!TextUtils.isEmpty(map.get("fileExtId"))&&erpGoods.getOwid().equalsIgnoreCase(map.get("fileExtId").toString()))
            filesService.saveOrUpdate(erpGoods.getOwid(),map.get("fileExtId").toString());
        return ResponseMessage.sendOK(erpGoods);
    }

    @RequestMapping(value = "removeErpGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeErpGoods(HttpServletRequest request, PublicDataVO dataVO){
        if (TextUtils.isEmpty(dataVO.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,dataVO.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(dataVO.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> tmpParms = new ArrayList<>();
        for (Object obj : list) {
            tmpParms.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String,Object>> data = erpGoodsService.removeErpGoodsByIds(tmpParms);
        return ResponseMessage.sendOK(data);
    }

    @RequestMapping(value = "listErpGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoods(HttpServletRequest request,PublicDataVO dataVO){
        List<FilterModel> filterModels  = JsonUtil.jsonToList(dataVO.getData(),FilterModel.class);
        PageInfo<ErpGoods> erpGoodss = erpGoodsService.listHqlForPage(filterModels,dataVO.getPageNo(),dataVO.getPageSize(),"");
        if (null == erpGoodss){
            return ResponseMessage.sendError(ResponseMessage.FAIL,erpGoodsService.getDbMsg("0004",dataVO));
        }
        return ResponseMessage.sendOK(erpGoodss);
    }

    @RequestMapping(value = "listErpGoodsList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoodsList(HttpServletRequest request,PublicDataVO dataVO){
        Map<String, Object> map = JsonUtil.jsonToMap(dataVO.getData());
        List<ErpGoodsList>  erpGoodsLists= erpGoodsListService.listAllByRefId(map.get("owid").toString());
        if (null == erpGoodsLists){
            return ResponseMessage.sendError(ResponseMessage.FAIL,erpGoodsService.getDbMsg("0004",dataVO));
        }
        return ResponseMessage.sendOK(erpGoodsLists);
    }

    @RequestMapping(value = "detailErpGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailErpGoods(HttpServletRequest request,PublicDataVO dataVO){
        Map<String, Object> map = JsonUtil.jsonToMap(dataVO.getData());
        ErpGoods  erpGoods = erpGoodsService.detailErpGoods(map.get("owid").toString());
        if (null == erpGoods){
            return ResponseMessage.sendError(ResponseMessage.FAIL,erpGoodsService.getDbMsg("0004",dataVO));
        }
        return ResponseMessage.sendOK(erpGoods);

    }



}
