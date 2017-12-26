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
}
