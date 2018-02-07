package com.ourway.common.utils;

import com.ourway.common.dto.Gps;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by D.chen.g on 2017/6/29.
 */
public class GpsUtil {
    public static double getDistanceFromXtoY(double lat_a, double lng_a, double lat_b, double lng_b)
    {
        double pk = (double)(180 / 3.14169);
        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6366000 * tt;
    }


    public static String getAddress(String jd, String wd) {
        String result = "";
        Map map = getMap(jd, wd);
        if (null != map) {
            if (null != map.get("province")) {
                result = (String) map.get("province") + map.get("city") + map.get("addr");
            } else {
                result = "";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // lat 39.97646
        // log 116.3039

        // Gps gpswx = new Gps(30.223602, 120.02547);
//		System.out.println(getAddress("30.21387", "120.12524"));
        Map map = getMap("30.272333", "120.11976");
//        Map map = getMap("0","0");

        // gpswx = PositionUtil.gps84_To_Gcj02(gpswx.getWgLat(),
        // gpswx.getWgLon());
        // String add = getAdd(gpswx.getWgLon()+"", gpswx.getWgLat()+"");
        // JSONObject jsonObject = JSONObject.fromObject(add);
        // JSONArray jsonArray = JSONArray.fromObject(jsonObject
        // .getString("addrList"));
        // JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        // String allAdd = j_2.getString("admName");
        // String arr[] = allAdd.split(",");
        // System.out.println(arr[1] + arr[2]+j_2.getString("name"));
    }

    /**
     * 获取微信地址接口对应的地区
     *
     * @param cuurLat
     * @param cuurLog
     * @return
     */
    public static Map getMap(String cuurLat, String cuurLog) {
        String add = getTencentAddr(cuurLog, cuurLat);
        Map map = new HashMap();
        try {
//            System.out.println(add);
            if (!add.startsWith("{"))
                add = "{" + add;
            JSONObject jsonObject = JSONObject.fromObject(add);
            JSONObject resultObj = JSONObject.fromObject(jsonObject
                    .getString("result"));

            map.put("dz", resultObj.getString("address"));
            if (resultObj.has("formatted_addresses")) {
                JSONObject addr2 = JSONObject.fromObject(resultObj.getString("formatted_addresses"));
                map.put("addr", addr2.getString("recommend"));
            }
            if (resultObj.has("formatted_addresses")) {
                JSONObject cityAddr = JSONObject.fromObject(resultObj.getString("address_component"));
                map.put("name", "");
                map.put("nation", cityAddr.getString("nation"));
                map.put("province", cityAddr.getString("province"));
                map.put("city", cityAddr.getString("city"));
                map.put("district", cityAddr.getString("district"));
                map.put("street_number", cityAddr.getString("street_number"));
            }
        } catch (Exception e) {

        }
        return map;
    }

    /**
     * 获取腾讯地图坐标
     *
     * @param log
     * @param lat
     * @return
     */
    public static String getTencentAddr(String log, String lat) {
        String urlString = "http://apis.map.qq.com/ws/geocoder/v1/?location="
                + lat + "," + log
                + "&key=HAWBZ-JFTAK-Y6LJP-ACOUG-3GHJK-ENBHK&get_poi=1";
        System.out.println(urlString);
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url
                    .openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream(),
                            "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
        return res;

    }

    public static String getAddress(double lat, double log) {
        Gps gpswx = new Gps(lat, log);
        gpswx = PositionUtil.gps84_To_Gcj02(gpswx.getWgLat(), gpswx.getWgLon());
        String add = getAdd(gpswx.getWgLon() + "", gpswx.getWgLat() + "");
        JSONObject jsonObject = JSONObject.fromObject(add);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject
                .getString("addrList"));
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String arr[] = allAdd.split(",");
        return (arr[1] + arr[2] + j_2.getString("name"));
    }

    public static String getAdd(String log, String lat) {
        // lat 小 log 大
        // 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat
                + "," + log + "&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url
                    .openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream(),
                            "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
//		System.out.println(res);
        return res;
    }

}
