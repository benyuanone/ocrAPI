package com.ourway.baiduapi.action;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.IdcardDTO;
import com.ourway.baiduapi.dto.InfoDTO;
import com.ourway.baiduapi.utils.Base64ImageUtils;
import com.ourway.baiduapi.utils.HttpClientUtils;
import com.ourway.baiduapi.utils.ValueUtils;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by D.chen.g on 2017/12/25.
 */
public class BaiDuApi {
    private static final String ACCESS_TOKEN = "access_token";

    /**
    *<p>方法:getToKen TODO 获取token</p>
    *<ul>
    *<li>@return java.lang.String  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 15:06  </li>
    *</ul>
    */
    public static String getToKen(){
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"
                +"&client_id="+ BaiDuApiInfo.API_KEY +"&client_secret="+BaiDuApiInfo.SECRET_KEY;
        CloseableHttpResponse response =  HttpClientUtils.doHttpsGet(access_token_url,null);
        String result=HttpClientUtils.toString(response);
        Map<String,Object> tokenMap=JsonUtil.jsonToMap(result);
        if(null!=tokenMap&& !TextUtils.isEmpty(tokenMap.get(ACCESS_TOKEN))){
            BaiDuApiInfo.TOKEN=tokenMap.get(ACCESS_TOKEN).toString();
            return BaiDuApiInfo.TOKEN;
        }else{
            return null;
        }
    }


    /**
    *<p>方法:idCardDiscriminate TODO识别身份证</p>
    *<ul>
     *<li> @param filePath TODO图片在服务器或本地电脑的绝对路径</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:55  </li>
    *</ul>
    */
    public static InfoDTO idCardDiscriminate(String filePath){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token="+BaiDuApiInfo.TOKEN;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("detect_direction", "false");
        bodys.put("id_card_side", "front");
        bodys.put("detect_risk", "true");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            //
            if(!TextUtils.isEmpty(result)){
                IdcardDTO idcardDTO=JsonUtil.fromJson(result,IdcardDTO.class);
                Map<String,String> vat= ValueUtils.getIdCardValue(idcardDTO.getWords_result());
                return InfoDTO.success(vat);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }

    }


    /**
    *<p>方法:generalDiscriminate TODO通用文字识别 </p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:56  </li>
    *</ul>
    */
    public static InfoDTO generalDiscriminate(String filePath){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token="+BaiDuApiInfo.TOKEN;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("language_type", "CHN_ENG");
        bodys.put("detect_language", "true");
        bodys.put("probability", "false");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            System.out.println(result);
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                List vat= (List)idcardDTO.get(ValueUtils.WORDS_RESULT);
                return InfoDTO.success(vat);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }
    }


    /**
    *<p>方法:driverDiscriminate TODO驾驶证识别 </p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:58  </li>
    *</ul>
    */
    public static InfoDTO driverDiscriminate(String filePath){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license?access_token="+BaiDuApiInfo.TOKEN;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("detect_direction", "true");
        bodys.put("unified_valid_period", "true");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            System.out.println(result);
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                Map<String,Object> value= (Map<String,Object>)idcardDTO.get(ValueUtils.WORDS_RESULT);
                Map<String,String> valueDTOS=ValueUtils.getDriverCardValue(value);
                return InfoDTO.success(valueDTOS);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }
    }

    /**
    *<p>方法:vechiceDiscriminate TODO行驶证识别 </p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:58  </li>
    *</ul>
    */
    public static InfoDTO vechiceDiscriminate(String filePath){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vehicle_license?access_token="+BaiDuApiInfo.TOKEN;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("detect_direction", "true");
        bodys.put("accuracy", "normal");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                Map<String,Object> value= (Map<String,Object>)idcardDTO.get(ValueUtils.WORDS_RESULT);
                Map<String,String> valueDTOS=ValueUtils.getDriverCardValue(value);
                return InfoDTO.success(valueDTOS);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }
    }

    /**
    *<p>方法:allDiscriminate TODO通用识别，返回json字符串自己解析 </p>
    *<ul>
     *<li> @param filePath TODO</li>
     *<li> @param url TODO</li>
     *<li> @param bodys TODO</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:58  </li>
    *</ul>
    */
    public static InfoDTO allDiscriminate(String filePath,String url,Map<String, String> bodys ){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            if(!TextUtils.isEmpty(result)){
                return InfoDTO.success(result);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }
    }


    /**
    *<p>方法:businessDiscriminate TODO企业营业执照 </p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:58  </li>
    *</ul>
    */
    public static InfoDTO businessDiscriminate(String filePath){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license?access_token="+BaiDuApiInfo.TOKEN;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("detect_direction", "true");
        bodys.put("accuracy", "normal");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            System.out.println(result);
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                Map<String,Object> value= (Map<String,Object>)idcardDTO.get(ValueUtils.WORDS_RESULT);
                Map<String,String> valueDTOS=ValueUtils.getDriverCardValue(value);
                return InfoDTO.success(valueDTOS);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }
    }



    /**
    *<p>方法:generalHighDiscriminate TODO高精度文字识别 </p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return com.ourway.baiduapi.dto.InfoDTO  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/16 10:58  </li>
    *</ul>
    */
    public static InfoDTO generalHighDiscriminate(String filePath){
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(filePath);
        String  Ssss = Base64ImageUtils.getImageStrFromPath(filePath,"11");
        if(resultBack.getCode()==BaiDuApiInfo.FAILED_CODE){
            return resultBack;
        }
        String image=(String)resultBack.getValue();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate?access_token="+BaiDuApiInfo.TOKEN;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<>();
        bodys.put("image", image);
        bodys.put("recognize_granularity", "false");
        bodys.put("detect_direction", "false");
        bodys.put("vertexes_location", "false");
        bodys.put("probability", "false");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String result=HttpClientUtils.toString(response);
            System.out.println(result);
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                List vat= (List)idcardDTO.get(ValueUtils.WORDS_RESULT);
                return InfoDTO.success(vat);
            }else {
                return InfoDTO.error(BaiDuApiInfo.NODATA_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return InfoDTO.error(BaiDuApiInfo.DEAL_EXCEPTION);
        }
    }

}
