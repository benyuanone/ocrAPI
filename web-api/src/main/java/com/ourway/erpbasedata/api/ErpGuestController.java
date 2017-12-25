package com.ourway.erpbasedata.api;

import com.ourway.base.model.BaseTree;
import com.ourway.ERPTipsConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.TreeUtils;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.model.ErpGuestLinklist;
import com.ourway.erpbasedata.service.ErpGuestDetailService;
import com.ourway.erpbasedata.service.ErpGuestService;
import com.ourway.sys.model.OurwaySysFiles;
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
import java.util.HashMap;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*p>方法 ErpGuestController : <p>
*<p>说明:客户资料</p>
*<pre>
*@author Kevin
*@date 2017-11-10 14:18
</pre>
*/
@Controller
@RequestMapping("erpGuestApi")
public class ErpGuestController {
    @Autowired
    ErpGuestService erpGuestService;
    @Autowired
    ErpGuestDetailService erpGuestDetailService;
    @Autowired
    FilesService filesService;
   /**
    *<p>方法: TODO展示客户资料 </p>
    *<ul>
     *<li> @param null TODO</li>
    *<li>@return   </li>
    *<li>@author Kevin </li>
    *<li>@date 2017-11-10 14:36  </li>
    *</ul>
    */
   @RequestMapping(value = "listGuest",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listGuest(HttpServletRequest request, HttpServletResponse response, PublicDataVO dataVO){
       List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(),FilterModel.class);
       return ResponseMessage.sendOK(erpGuestService.listHqlForPage(filters, dataVO.getPageNo(), dataVO.getPageSize(), ""));
   }
    /**
    *<p>方法: TODO 删除客户数据</p>
    *<ul>
     *<li> @param null TODO</li>
    *<li>@return   </li>
    *<li>@author Kevin </li>
    *<li>@date 2017-11-15 15:54  </li>
    *</ul>
    */
    @RequestMapping(value = "removeGuest",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeGuest (HttpServletRequest request,HttpServletResponse response,PublicDataVO dataVO){

        return ResponseMessage.sendOK(dataVO);
    }
   /**
    *<p>方法: TODO 展示客户资料详情</p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author Kevin </li>
    *<li>@date 2017-11-15 16:58  </li>
    *</ul>
    */
    @RequestMapping(value = "listGuestDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listGuestDetail(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpGuestService.detailErpGuest(mapData.get("owid").toString()));
    }

    /**
    *<p>方法: TODO 展示客户系表信息，客户联系人/负责商品</p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author Kevin </li>
    *<li>@date 2017-11-21 10:53  </li>
    *</ul>
    */
    @RequestMapping(value = "listGuestDetailList",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseMessage listGuestDetailList(HttpServletRequest request,HttpServletResponse response,PublicDataVO dataVO){
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpGuestDetailService.listAllGuestListById(mapData.get("owid").toString()));
    }

   /**
    *<p>方法: TODO 保存客户资料</p>
    *<ul>
     *<li> @param null TODO</li>
    *<li>@return   </li>
    *<li>@author Kevin </li>
    *<li>@date 2017-11-21 13:55  </li>
    *</ul>
    */
    @RequestMapping(value = "saveAllGuest",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseMessage saveAllGuest(HttpServletRequest request,HttpServletResponse response,PublicDataVO dataVO){
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断必填字段
//        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "linkman");
//        if (!msg.getSuccess()) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
//        }
        if (!TextUtils.isEmpty(mapData.get("dataList1")) && ((List)mapData.get("dataList1")).size()>0) {
            for(Map<String,Object> _map:(List<Map<String,Object>>) mapData.get("dataList1")){
                _map.put("erpgoodsRefOwid",((Map<String,Object>)_map.get("erpgoodsRefOwid")).get("owid"));
            }
        }

        //解析 api 和子表信息
        ErpGuest erpShip = JsonUtil.map2Bean(mapData, ErpGuest.class);

        List<ErpGuestLinklist> erpShipListList = null;

        if (null != mapData.get("dataList1")) {
            List<Map<String, Object>> temp = (List<Map<String, Object>>) mapData.get("dataList1");
            erpShipListList = JsonUtil.map2List(temp, ErpGuestLinklist.class);
        }
        //TODO 判断客户负责商品，首选的选项;
        erpShip.getOwid();
        if (erpGuestService.doCheckFirstChoiceUnique(erpShip.getOwid())) {

        }
//        //判断iintUrl是否唯一
//        if (!erpShipService.doCheckUniqueUrl(erpShip)) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(ERPTipsConstants.TANKID_UNIQUE,data.getCurrLanguage()));
//        }

        //保存或者修改
        erpShip = erpGuestService.saveOrUpdateErpShip(erpShip, erpShipListList);
        if(!TextUtils.isEmpty(mapData.get("fileExtId"))&&erpShip.getOwid().equalsIgnoreCase(mapData.get("fileExtId").toString()))
            filesService.saveOrUpdate(erpShip.getOwid(),mapData.get("fileExtId").toString());
        return ResponseMessage.sendOK(erpShip);
    }

    /**
    *<p>方法: TODO 删除客户资料</p>
    *<ul>
     *<li> @param null TODO</li>
    *<li>@return   </li>
    *<li>@author Kevin </li>
    *<li>@date 2017-11-22 15:39  </li>
    *</ul>
    */
    @RequestMapping(value = "removeErpGuest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeErpGuest(HttpServletRequest request, HttpServletResponse response, PublicDataVO dataVo) {
        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> data = erpGuestService.removeErpShipByIds(owids);

        return ResponseMessage.sendOK(null);
    }

    /**<p>方法: 客户资料树形展示 </p>
    *<ul>
     *<li> @param data 固定传值</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-11-23 15:26  </li>
    *</ul>
    */
    @RequestMapping(value = "treeGuest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage treeGuest(HttpServletRequest request, PublicDataVO data) {
        List<ErpGuest> erpGuestList = erpGuestService.listAllGuest();
        List<BaseTree> baseTrees = new ArrayList<BaseTree>(erpGuestList.size());
        int index = 0;
        for (ErpGuest erpGuest : erpGuestList) {
            erpGuest.setGuestIdRefId(erpGuest.getGuestIdRefId()+index);
            BaseTree _baseTree = TreeUtils.convert(erpGuest, new String[]{"guestIdRefId-owid", "guestIdRefFId-fid", "guestName-name", "owid-path"});
            //添加不重复树数据
            List<String> nameList = new ArrayList<String>();
            for (BaseTree baseTree : baseTrees) {
                nameList.add(baseTree.getName());
            }
            if (nameList.size() == 0 || !nameList.contains(_baseTree.getName())) {
                baseTrees.add(_baseTree);
            }
            index ++;
        }
        return ResponseMessage.sendOK(baseTrees);
    }

    /**<p>方法: 展示对应客户下所有样张文件 </p>
    *<ul>
     *<li> @param data 固定传值</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-11-23 15:25  </li>
    *</ul>
    */
    @RequestMapping(value = "showAllFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage showAllFiles(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());

        List<Map<String,Object>> responseList = new ArrayList<Map<String,Object>>();
        List<OurwaySysFiles> filesList = filesService.listAllFilesByOwid(dataMap.get("owid").toString());
        for (OurwaySysFiles ourwaySysFiles:filesList) {
            Map<String,Object> _map = new HashMap<String,Object>();
            BeanUtil.obj2Map(ourwaySysFiles,_map);
            String showImage = _map.get("filePath").toString();
            boolean ifImage = true;
            if (!_map.get("fileRandon").toString().contains("image")) {
                ifImage = false;
                if (_map.get("fileRandon").toString().contains("text")) {
                    showImage = "txt.jpg";
                }
                if (_map.get("fileRandon").toString().contains("zip")) {
                    showImage = "zip.png";
                }
                //TODO 其他类型图片判断
            }
            _map.put("showImage",showImage);
            _map.put("ifImage",ifImage);
            responseList.add(_map);
        }

        return ResponseMessage.sendOK(responseList);
    }

    /**<p>方法: 保存客户样张图片数据 </p>
    *<ul>
     *<li> @param data 固定传值</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-11-23 15:23  </li>
    *</ul>
    */
    @RequestMapping(value = "saveGuestV", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveGuestV(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        //获取对象
        if (!TextUtils.isEmpty(dataMap.get("addBtn")) && dataMap.get("addBtn") instanceof Map) {
            Map<String,Object> filePpt = (Map<String,Object>)dataMap.get("addBtn");
            filePpt.put("filePath", filePpt.get("filePath").toString());
            filePpt.put("fileClassId", dataMap.get("owid").toString());
            filePpt.put("fileClass", "erpGuest");
            //map to enity
            OurwaySysFiles ourwaySysFiles = JsonUtil.map2Bean(filePpt, OurwaySysFiles.class);
            filesService.saveOrUpdate(ourwaySysFiles);
        }
        return ResponseMessage.sendOK(null);
    }

    /**<p>方法: 删除客户样张单个图片 </p>
    *<ul>
     *<li> @param data 固定传值</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-11-23 15:23  </li>
    *</ul>
    */
    @RequestMapping(value = "removeGuestV", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeErpGuest(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        //获取对象
        List<Object> owidList = new ArrayList<Object>();
        owidList.add(dataMap.get("owid"));
        filesService.removeFile(owidList);
        return ResponseMessage.sendOK(null);
    }
}
