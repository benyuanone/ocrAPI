package com.ourway.base.zk.utils;


import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PublicData;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * <p>方法 HttpUtils : <p>
 * <p>说明:Http 操作的客户端，作为前台页面同后台的对接入口</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 21:09
 * </pre>
 */
public class HttpUtils {

    /**
     * <p>方法:doPost 调用远程接口 </p>
     * <ul>
     * <li> @param data 数据对象</li>
     * <li> @param charset 编码</li>
     * <li> @param pretty true</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/17 21:35  </li>
     * </ul>
     */
    public static String doPost(PublicData data, String charset, boolean pretty) throws IOException {
        String url = BaseConstants.URL_HOST + data.getMethod() + BaseConstants.INTERCEPT_SUFFIX;
        return doPost(url, JsonUtil.jsonToMap(JsonUtil.toJson(data)), charset, pretty,"");
    }
    //带cookie的调用
    public static String doPost(PublicData data, String charset, boolean pretty,String cookie) throws IOException {
        String url = BaseConstants.URL_HOST + data.getMethod() + BaseConstants.INTERCEPT_SUFFIX;
        return doPost(url, JsonUtil.jsonToMap(JsonUtil.toJson(data)), charset, pretty,cookie);
    }
    /**
     * <p>方法:doPost Post 方式访问HTTP URL </p>
     * <ul>
     * <li> @param url 访问的路径地址</li>
     * <li> @param params 访问的参数</li>
     * <li> @param charset 编码方式</li>
     * <li> @param pretty 是否读一行换行</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/18 22:29  </li>
     * </ul>
     */
    public static String doPost(String url, Map<String, Object> params,
                                String charset, boolean pretty, String cookie) throws IOException {
        System.out.println(url);
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

        post.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=UTF-8"));
        post.setRequestHeader(new Header(
                "User-Agent",
                ZKSessionUtils.getUserAgent()));
        if (!TextUtils.isEmpty(ZKSessionUtils.getCookie()))
            cookie = ZKSessionUtils.getCookie();
        if (!TextUtils.isEmpty(cookie))
            post.setRequestHeader("cookie", cookie);

        // 设置Http Post数据
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                post.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        try {
            client.executeMethod(post);
        } catch (IOException e) {
            throw e;
        }
        makeResponse(response, post, charset, pretty);
        return response.toString();
    }


    public static String doGet(String url, Map<String, Object> params,
                               String charset, boolean pretty) {
//        System.out.println(url);
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        if (sb.length() > 0)
            url = url + "?" + sb.toString().substring(0, sb.toString().length() - 1);
        GetMethod get = new GetMethod(url);
//        get.setRequestHeader(new Header("Content-type",
//                "application/x-www-form-urlencoded;charset=gbk"));
//        get.setRequestHeader(new Header(
//                "User-Agent",
//                "Mozilla/5.0 /Windows; U; Windows NT 4.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.0"));

        try {
            client.executeMethod(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

        makeResponse(response, get, charset, pretty);

        return response.toString();
    }

    private static void makeResponse(StringBuffer response, HttpMethodBase method, String charset, boolean pretty) {
        if (method.getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(method.getResponseBodyAsStream(),
                                charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                method.releaseConnection();
            }
        }
    }

    /**
     * <p>方法:loginPost 用户用户登录的httppost访问，主要用户登录成功后，保持用户的session </p>
     * <ul>
     * <li> @param data 访问数据</li>
     * <li> @param charset 编码</li>
     * <li> @param pretty </li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/8 15:14  </li>
     * </ul>
     */
    public static String loginPost(PublicData data, String charset, boolean pretty) throws IOException {
        String url = BaseConstants.URL_HOST + data.getMethod() + BaseConstants.INTERCEPT_SUFFIX;
        Map<String, Object> params = JsonUtil.jsonToMap(JsonUtil.toJson(data));
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=UTF-8"));
        post.setRequestHeader(new Header(
                "User-Agent",
                ZKSessionUtils.getUserAgent()));
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

        // 设置Http Post数据
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                post.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        try {
            client.executeMethod(post);
            Cookie[] cookies = client.getState().getCookies();
            String tmpcookies = "";
            for (Cookie c : cookies) {
                tmpcookies += c.toString() + ";";
            }
            ZKSessionUtils.setCookie(tmpcookies);
        } catch (IOException e) {
            throw e;
        }
        makeResponse(response, post, charset, pretty);
        return response.toString();
    }


    /**
     * <p>方法:logOutPost 用户用户登出使用，去除session一致 </p>
     * <ul>
     * <li> @param data 登出对象</li>
     * <li> @param charset 编码</li>
     * <li> @param pretty </li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/8 15:14  </li>
     * </ul>
     */
    public static String logOutPost(PublicData data, String charset, boolean pretty) throws IOException {
        String url = BaseConstants.URL_HOST + data.getMethod() + BaseConstants.INTERCEPT_SUFFIX;
        Map<String, Object> params = JsonUtil.jsonToMap(JsonUtil.toJson(data));
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=UTF-8"));
        post.setRequestHeader(new Header(
                "User-Agent",
                ZKSessionUtils.getUserAgent()));
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

        // 设置Http Post数据
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                post.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        try {
            client.executeMethod(post);
            ZKSessionUtils.setCookie("");
        } catch (IOException e) {
            throw e;
        }
        makeResponse(response, post, charset, pretty);
        return response.toString();
    }

}
