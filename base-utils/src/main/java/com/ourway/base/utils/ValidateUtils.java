package com.ourway.base.utils;

import net.sf.json.JSONNull;

import java.util.Map;

/**
 * <p>方法 ValidateUtils : <p>
 * <p>说明:t通用校验类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/12 20:00
 * </pre>
 */
public class ValidateUtils {

    /**
     * <p>方法:isEmpty 判断是否为空 </p>
     * <ul>
     * <li> @param s 字符串</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/12 20:01  </li>
     * </ul>
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    /**
     * <p>方法:isEmpty 判断是否为空 </p>
     * <ul>
     * <li> @param o 对象</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/12 20:01  </li>
     * </ul>
     */
    public static boolean isEmpty(Object o) {
        if (null == o)
            return true;
        if (o instanceof JSONNull)
            return true;
        if ("null".equals(o))
            return true;
        if ("".equals(o))
            return true;
        return false;
    }

    /**
     * <p>方法: 传入map，判断非空 </p>
     * <ul>
     * <li> @param data 数据对象 </li>
     * <li> @param map 判断非空的多语言map集 </li>
     * <li>@return   boolean</li>
     * <li>@author zhou_xtian </li>
     * <li>@date 2017-07-25 17:25  </li>
     * </ul>
     */
    public static ValidateMsg isEmpty2(Map<String, Object> data, Map<String, String> map) {
        boolean flag = true;
        ValidateMsg msg = ValidateMsg.instance(ValidateMsg.EMPTY_FAIL);
        if (isEmpty(data)) {
            msg.setSuccess(false);
            msg.setKeys(new StringBuilder("data"));
            return msg;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (isEmpty(data.get(entry.getValue()))) {
                flag = false;
                msg.getKeys().append(entry.getKey() + "、");
            }
        }
        msg.setSuccess(flag);
        return msg;
    }

    /**
     * <p>方法:isEmpty 传入多个key，用来判断是否非空 </p>
     * <ul>
     * <li> @param data 数据对象</li>
     * <li> @param keys 判断非空的关键字</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/24 23:35  </li>
     * </ul>
     */
    public static ValidateMsg isEmpty(Map<String, Object> data, String... keys) {
        boolean flag = true;
        ValidateMsg msg = ValidateMsg.instance(ValidateMsg.EMPTY_FAIL);
        if (isEmpty(data)) {
            msg.setSuccess(false);
            msg.setKeys(new StringBuilder("data"));
            return msg;
        }
        for (String key : keys) {
            if (isEmpty(data.get(key))) {
                flag = false;
                msg.getKeys().append(key + " ");
            }
        }
        msg.setSuccess(flag);
        return msg;
    }

    public static ValidateMsg isInt(Map<String, Object> data, String... keys) {
        boolean flag = true;
        ValidateMsg msg = ValidateMsg.instance(ValidateMsg.EMPTY_FAIL);
        if (isInt(data)) {
            msg.setSuccess(false);
            msg.setKeys(new StringBuilder("data"));
            return msg;
        }
        for (String key : keys) {
            if (isInt(data.get(key))) {
                flag = false;
                msg.getKeys().append(key + " ");
            }
        }
        msg.setSuccess(flag);
        return msg;
    }

    public static boolean isInt(Object obj) {
        return false;
    }


    public static void main(String[] args) {

    }
}
