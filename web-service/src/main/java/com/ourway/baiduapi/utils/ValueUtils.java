package com.ourway.baiduapi.utils;

import com.ourway.baiduapi.dto.ValueDTO;
import net.sf.ezmorph.bean.MorphDynaBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public static List<ValueDTO> getIdCardValue(Map<String, MorphDynaBean> params) throws Exception{
        if (null == params) {
            return null;
        }
        Set<String> keys = params.keySet();
        List<ValueDTO> valueDTOList = new ArrayList<>(keys.size());
        ValueDTO valueDTO = null;
        MorphDynaBean map = null;
        for (String key : keys) {
            map = params.get(key);
            if (null!=map) {
                valueDTO = new ValueDTO();
                valueDTO.setKey(key);
                valueDTO.setWords(map.get(WORDS).toString());
                valueDTOList.add(valueDTO);
            }
        }
        return valueDTOList;
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
    public static List<ValueDTO> getDriverCardValue(Map<String, Object> params) throws Exception{
        if (null == params) {
            return null;
        }
        Set<String> keys = params.keySet();
        List<ValueDTO> valueDTOList = new ArrayList<>(keys.size());
        ValueDTO valueDTO = null;
        Map<String,Object> map = null;
        for (String key : keys) {
            Object object = params.get(key);
            if (null!=object) {
                map=(Map<String, Object>)object;
                valueDTO = new ValueDTO();
                valueDTO.setKey(key);
                valueDTO.setWords(map.get(WORDS).toString());
                valueDTOList.add(valueDTO);
            }
        }
        return valueDTOList;
    }
}
