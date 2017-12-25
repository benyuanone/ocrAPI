package com.ourway.baiduapi.action;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.utils.Base64ImageUtils;
import com.ourway.baiduapi.utils.HttpClientUtils;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by D.chen.g on 2017/12/25.
 */
public class BaiDuApi {
    public final static String API_TOKEN="24.4ef7a1ae0613cc6abccdf21ae744f869.2592000.1516796619.282335-10580248";
    public static final String APP_ID = "10580248";
    public static final String API_KEY = "aK9ba9d59VCIiscWXMaO8SPX";
    public static final String SECRET_KEY = "0p0xZnrZWoEZQwv7NdVh5YBVm41C3FoZ";
    public static void getToKen(){
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"
                +"&client_id="+ BaiDuApiInfo.API_KEY +"&client_secret="+BaiDuApiInfo.SECRET_KEY;

        CloseableHttpResponse response =  HttpClientUtils.doHttpsGet(access_token_url,null);
        String result=HttpClientUtils.toString(response);
        Map<String,Object> tokenMap=JsonUtil.jsonToMap(result);
        if(null!=tokenMap&& !TextUtils.isEmpty(tokenMap.get("access_token"))){
            BaiDuApiInfo.TOKEN=tokenMap.get("access_token").toString();
        }
    }


    //使用token调用API
    public static void IdCardDiscriminate(String filePath){
        String image = Base64ImageUtils.GetImageStrFromPath(filePath);
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token="+API_TOKEN;

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
            //待续
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        faceDetecttest();
       // getToKenTest();//先获取token，再替换掉现有写的
    }

}
