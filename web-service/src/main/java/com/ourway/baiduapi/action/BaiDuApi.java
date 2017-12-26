package com.ourway.baiduapi.action;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.IdcardDTO;
import com.ourway.baiduapi.dto.ValueDTO;
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
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 15:06  </li>
    *</ul>
    */
    public static void getToKen(){
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"
                +"&client_id="+ BaiDuApiInfo.API_KEY +"&client_secret="+BaiDuApiInfo.SECRET_KEY;

        CloseableHttpResponse response =  HttpClientUtils.doHttpsGet(access_token_url,null);
        String result=HttpClientUtils.toString(response);
        Map<String,Object> tokenMap=JsonUtil.jsonToMap(result);
        if(null!=tokenMap&& !TextUtils.isEmpty(tokenMap.get(ACCESS_TOKEN))){
            BaiDuApiInfo.TOKEN=tokenMap.get(ACCESS_TOKEN).toString();
            System.out.println("获取到百度apiToken："+BaiDuApiInfo.TOKEN);
        }
    }


    /**
    *<p>方法:IdCardDiscriminate TODO 识别身份证</p>
    *<ul>
     *<li> @param filePath TODO图片文件路径</li>
    *<li>@return java.util.List<com.ourway.baiduapi.dto.ValueDTO>  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 13:57  </li>
    *</ul>
    */
    public static List<ValueDTO> idCardDiscriminate(String filePath){
        String image = Base64ImageUtils.GetImageStrFromPath(filePath);
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
                List<ValueDTO> vat= ValueUtils.getIdCardValue(idcardDTO.getWords_result());
                return vat;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     *<p>方法:generalDiscriminate TODO 通用文字识别</p>
     *<ul>
     *<li> @param filePath TODO图片文件路径</li>
     *<li>@return java.util.List  </li>
     *<li>@author D.cehn.g </li>
     *<li>@date 2017/12/26 13:57  </li>
     *</ul>
     */
    public static List generalDiscriminate(String filePath){
        String image = Base64ImageUtils.GetImageStrFromPath(filePath);
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
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                List vat= (List)idcardDTO.get(ValueUtils.WORDS_RESULT);
                return vat;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
    *<p>方法:driverDiscriminate TODO 驾驶证识别</p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return java.util.List<com.ourway.baiduapi.dto.ValueDTO>  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 14:48  </li>
    *</ul>
    */
    public static List<ValueDTO> driverDiscriminate(String filePath){
        String image = Base64ImageUtils.GetImageStrFromPath(filePath);
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
            if(!TextUtils.isEmpty(result)){
                Map idcardDTO=JsonUtil.jsonToMap(result);
                Map<String,Object> value= (Map<String,Object>)idcardDTO.get(ValueUtils.WORDS_RESULT);
                List<ValueDTO> valueDTOS=ValueUtils.getDriverCardValue(value);
                return valueDTOS;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    *<p>方法:vechiceDiscriminate TODO 行驶证识别</p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return java.util.List<com.ourway.baiduapi.dto.ValueDTO>  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 14:51  </li>
    *</ul>
    */
    public static List<ValueDTO> vechiceDiscriminate(String filePath){
        String image = Base64ImageUtils.GetImageStrFromPath(filePath);
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
                List<ValueDTO> valueDTOS=ValueUtils.getDriverCardValue(value);
                return valueDTOS;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        List list=vechiceDiscriminate("D:/xinfeng/5.jpg");//("D:/xinfeng/1.png");
        for(Object o:list){
            ValueDTO v=(ValueDTO)o;
            System.out.println(v.getKey()+":"+v.getWords());
        }
       // driverDiscriminate("D:/xinfeng/4.png");
//       getToKen();//先获取token，再替换掉现有写的
    }

}
