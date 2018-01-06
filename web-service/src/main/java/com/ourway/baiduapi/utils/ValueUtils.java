package com.ourway.baiduapi.utils;

import com.ourway.baiduapi.dto.ValueDTO;
import net.sf.ezmorph.bean.MorphDynaBean;

import java.util.*;

/**
 * Created by D.chen.g on 2017/12/26.
 * 解析
 */
public class ValueUtils {
    private final static String WORDS="words";
    public final static String WORDS_RESULT="words_result";
    /**
    *<p>方法:getIdCardValue TODO 处理身份证识别结果</p>
    *<ul>
     *<li> @param params TODO</li>
    *<li>@return java.util.List<com.ourway.baiduapi.dto.ValueDTO>  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 14:48  </li>
    *</ul>
    */
    public static Map<String,String> getIdCardValue(Map<String, MorphDynaBean> params) throws Exception{
        if (null == params) {
            return null;
        }
        Set<String> keys = params.keySet();
        Map<String,String> valueDTOMap = new HashMap<>(keys.size());
        ValueDTO valueDTO = null;
        MorphDynaBean map = null;
        for (String key : keys) {
            map = params.get(key);
            if (null!=map) {
                valueDTOMap.put(key,map.get(WORDS).toString());
            }
        }
        return valueDTOMap;
    }

    /**
    *<p>方法:getDriverCardValue TODO 识别驾驶证</p>
    *<ul>
     *<li> @param params TODO</li>
    *<li>@return java.util.List<com.ourway.baiduapi.dto.ValueDTO>  </li>
    *<li>@author D.cehn.g </li>
    *<li>@date 2017/12/26 14:49  </li>
    *</ul>
    */
    public static Map<String,String>  getDriverCardValue(Map<String, Object> params) throws Exception{
        if (null == params) {
            return null;
        }
        Set<String> keys = params.keySet();
        Map<String,String> valueDTOMap = new HashMap<>(keys.size());
        Map<String,Object> map = null;
        for (String key : keys) {
            Object object = params.get(key);
            if (null!=object) {
                map=(Map<String, Object>)object;
                valueDTOMap.put(key,map.get(WORDS).toString());
            }
        }
        return valueDTOMap;
    }
}
