package com.ourway.aliyunapi.action;

import com.ourway.baiduapi.utils.HttpUtils;
import com.ourway.baiduapi.utils.Img2Base64Util;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by D.chen.g on 2018/1/6.
 */
public class OcrApi {

    /**
    *<p>方法:test TODO阿里云上的api调用示例</p>
    *<ul>
     *<li> @param filePath TODO</li>
    *<li>@return java.lang.String  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/1/6 16:52  </li>
    *</ul>
    */
    public static String test(String filePath){
        String image = Img2Base64Util.getImgStr(filePath);
        String host = "http://table.aliapi.hanvon.com";
        String path = "/rt/ws/v1/ocr/table/json/recg";
        String method = "POST";
        String appcode = "f723641a66dc4049812e8fd70118101d";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Content-Type", "application/octet-stream");
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("code", "95738911-8f66-49af-813d-75e9fe4b1ec2");
        String bodys = "{\"uid\":\"183.156.1.166\",\"image\":\""+image+"\"}";


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            Object v=response.getEntity();
//            System.out.println();
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        test("D:/xinfeng/26.jpg");
    }
}
