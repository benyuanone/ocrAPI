package com.ourway.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by D.chen.g on 2018/2/7.
 */
public class TimeUtil {
    public static long dateDiff(String startTime, String endTime
    ) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
//        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long diff;
        long min = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            min = diff/nm ;// 计算差多少分钟
            return Math.abs(min);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Math.abs(min);
    }


    public static long dateDiff(Date startTime, Date endTime) {
        // 按照传入的格式生成一个simpledateformate对象
//        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
//        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long diff;
        long min = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff =endTime.getTime() - startTime.getTime();
            min = diff/nm ;// 计算差多少分钟
            return Math.abs(min);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Math.abs(min);
    }

    public static long dateDiffnoAbs(String startTime, String endTime
    ) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
//        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long diff;
        long min = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            min = diff/nm ;// 计算差多少分钟
            return min;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return min;
    }
}
