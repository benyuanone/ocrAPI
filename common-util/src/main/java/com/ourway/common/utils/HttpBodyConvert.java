package com.ourway.common.utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by D.chen.g on 2018/1/31.
 * 原生body获取数据
 */
public class HttpBodyConvert {

    /**
     * <p>方法:convertBody TODO解析body </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li>@return java.lang.String  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/2/1 13:13  </li>
     * </ul>
     */
    public static String convertBody(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer("");
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }

    /**
    *<p>方法:responseReturn TODOjson数据返回</p>
    *<ul>
     *<li> @param response TODO</li>
     *<li> @param data TODO</li>
    *<li>@return void  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/2/1 13:21  </li>
    *</ul>
    */
    public static void responseReturn(HttpServletResponse response, Object data) {
        JSONObject responseJSONObject = JSONObject.fromObject(data);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /***
    *<p>方法:toGBK TODO中文转GBK内码</p>
    *<ul>
     *<li> @param source TODO</li>
    *<li>@return java.lang.String  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2018/2/6 15:45  </li>
    *</ul>
    */
    public static String toGBK(String source) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = source.getBytes("GBK");
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xff)).toUpperCase());
        }

        return sb.toString();
    }

    public static String GBK2Chinese(String GBKStr) throws Exception {
        byte[] b = HexString2Bytes(GBKStr);
        String chineseStr = new String(b, "gbk");//输入参数为字节数组
        return chineseStr;
    }

    //把16进制字符串转换成字节数组
    public static byte[] HexString2Bytes(String hexStr) {
        byte[] b = new byte[hexStr.length() / 2];
        for (int i = 0; i < b.length; i++)
            b[i] = (byte) Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
        return b;
    }

}
