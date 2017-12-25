package com.ourway.erpsystem.api.other;

import com.ourway.base.utils.JsonUtil;
import com.ourway.erpbasedata.model.ErpGoods;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack on 2017/5/4.
 */
public class TestUtils {
    public static void main(String[] args){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("goodsId", "test");
        dataMap.put("name", "测试数据");
        dataMap.put("density", 11.11);
        JsonUtil.map2Bean(dataMap, ErpGoods.class);
    }
}
