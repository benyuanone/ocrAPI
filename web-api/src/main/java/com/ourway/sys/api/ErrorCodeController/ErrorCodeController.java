package com.ourway.sys.api.ErrorCodeController;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysErrorcode;
import com.ourway.sys.service.ErrorcodeService;
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


/**
 * <dl>
 * <dt>ErrorCodeController</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2017/4/24 0024</dd>
 * </dl>
 *
 * @author xby
 */
@Controller
@RequestMapping("errorCodeApi")
public class ErrorCodeController {

    @Autowired
    ErrorcodeService errorCodeService;

    //新增数据  针对单条数据
    @RequestMapping(value = "saveErrorCode",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveErrorCode(HttpServletRequest request,PublicDataVO data){

        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());

        //不确定哪几个字段需要判断  先判断 ERROR_CHN_NAME

        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"errorChnName");

        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }

        OurwaySysErrorcode ourwaySysErrorcode = JsonUtil.map2Bean
                (dataMap,OurwaySysErrorcode.class);
        //生成code
        ourwaySysErrorcode.setErrorCode(TextUtils.getUUID());

        //判断 errorcode是否唯一
        if(!errorCodeService.doCheckUniqueCode(ourwaySysErrorcode)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"errorCode已经存在！");
        }

        //存入数据库
        errorCodeService.saveOrUpdateErrorCode(ourwaySysErrorcode);

        //返回操作成功信息
        return  ResponseMessage.sendOK(ourwaySysErrorcode);
    }

    /*
    *<p>功能描述：listErrorCode 分页显示异常信息</p >
    *<ul>
    *<li>@param [request, data]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/20 0020 下午 1:50</li>
    *</ul>
    */
    @RequestMapping(value = "listErrorCode",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErrorCode(HttpServletRequest request, PublicDataVO data){

        List<FilterModel> filterModels = JsonUtil.jsonToList(data.getData(),FilterModel.class);

        //还有问题 分页 参数设置
        return ResponseMessage.sendOK(errorCodeService.listHqlForPage(filterModels,data.getPageNo(),data.getPageSize()));
    }


    /*
    *<p>功能描述：removeError </p >
    *<ul>
    *<li>@param [request, data]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/20 0020 下午 4:08</li>
    *</ul>
    */
    @RequestMapping(value = "removeError",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeError(HttpServletRequest request, HttpServletResponse response,
                                                  PublicDataVO data){
        //判断data 是否为空
        if(TextUtils.isEmpty(data.getData())){
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }

        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String,Object>> datas = errorCodeService.removeItems(owids);

        return ResponseMessage.sendOK(datas);
    }


    /*
    *<p>功能描述：detailOneErrorCode  根据owid 查询单条记录</p >
    *<ul>
    *<li>@param [response, request, dataVO]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/29 0029 下午 3:20</li>
    *</ul>
    */
    @RequestMapping("/detailOneErrorCode")
    @ResponseBody
    public ResponseMessage detailOneErrorCode(HttpServletResponse response,HttpServletRequest request,
                                                PublicDataVO dataVO){

        Map<String,Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());

        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"owid");
        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }

        OurwaySysErrorcode errorcode = errorCodeService.detailOneErrorCode(dataMap.get("owid").toString());
        return ResponseMessage.sendOK(errorcode);
    }


}
