package com.ourway.sys.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.JsonUtil;
import com.ourway.sys.model.OurwaySysLanguage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 */
public class App {

    public static void main(String[] args){
        Map<String, Object> dataMap = new HashMap<String,Object>();
        dataMap.put("labelKey","test");
        dataMap.put("labelName","测试数据");
        dataMap.put("labelType",1);
        OurwaySysLanguage language = new OurwaySysLanguage();
//        language = JsonUtil.map2Bean(dataMap,language.getClass(),new String[]{"labelKey","labelName","labelType"});
        language = JsonUtil.map2Bean(dataMap,language.getClass());
        System.out.println(JsonUtil.toJson(language));

        List<FilterModel> datas = new ArrayList<FilterModel>(5);
        FilterModel model = new FilterModel();
        model.setKey("owid");
        model.setType(FilterModel.MULT_EQUALS);
        List<Object> _objs = new ArrayList<Object>(1);
        _objs.add(1);
        _objs.add(2);
        model.setDatas(_objs);
        datas.add(model);
        System.out.println(JsonUtil.toJson(datas.toArray()));

    }
}
