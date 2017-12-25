package com.ourway.base.utils;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/2/4.
 */
public class JsonUtil {
    /**
     * 对象转换成JSON字符串
     *
     * @param obj 需要转换的对象
     * @return 对象的string字符
     */
    public static String toJson(Object obj) {
        JSONObject jSONObject = JSONObject.fromObject(obj);
        return jSONObject.toString();
    }

    /**
     * <p>方法:toJson 指定属性转成json对象 </p>
     * <ul>
     * <li> @param obj TODO</li>
     * <li> @param ignorePropeties TODO</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:52  </li>
     * </ul>
     */
    public static String toJson(Object obj, String[] includePropeties) {
        Map<String, Object> map = BeanUtil.obj2Map(obj, includePropeties);
        return toJson(map);
    }

    /**
     * <p>方法:toJson 指定属性转成json对象 </p>
     * <ul>
     * <li> @param obj 待转换对象</li>
     * <li> @param excludePropeties 剔除的属性</li>
     * <li> @param dateFormate Date类型格式化字符串</li>
     * <li>@return java.lang.String  </li>
     * <li>@author CuiLiang </li>
     * <li>@date 2017-07-14 11:30  </li>
     * </ul>
     */
    public static String toJson(Object obj, String[] excludePropeties, String dateFormate) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludePropeties);
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(dateFormate));
        ;
        JSONObject jSONObject = JSONObject.fromObject(obj, jsonConfig);
        return jSONObject.toString();
    }

    /**
     * <p>方法:toJson 把数组对象转成json字符串 </p>
     * <ul>
     * <li> @param obj 数组对象</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:47  </li>
     * </ul>
     */
    public static String toJson(Object[] obj) {
        JSONArray jSONObject = JSONArray.fromObject(obj);
        return jSONObject.toString();
    }

    /**
     * <p>方法:toJson 把列表中的指定的属性转成json字符串 </p>
     * <ul>
     * <li> @param obj 对象数组</li>
     * <li> @param includePropeties 包含的属性</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:57  </li>
     * </ul>
     */
    public static String toJson(Object[] obj, String[] includePropeties) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (Object o : obj) {
            Map<String, Object> _map = new HashMap<String, Object>();
            for (String s : includePropeties) {
                _map.put(s, BeanUtil.getProperty(o, s));
            }
            objs.add(_map);
        }
        JSONArray jSONObject = JSONArray.fromObject(objs.toArray());
        return jSONObject.toString();
    }

    /**
     * <p>方法:fromJson JSON字符串转换成对象 </p>
     * <ul>
     * <li> @param jsonString 需要转换的字符串</li>
     * <li> @param type 需要转换的对象类型</li>
     * <li>@return T  对象</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:06  </li>
     * </ul>
     */
    public static <T> T fromJson(String jsonString, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return (T) JSONObject.toBean(jsonObject, type);
    }

    /**
     * <p>方法:jsonToList JSON字符串转换成对象列表 </p>
     * <ul>
     * <li> @param jsonStr 需要转换的字符串</li>
     * <li> @param type 需要转换的对象类型</li>
     * <li>@return java.util.List<T>  对象列表</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:07  </li>
     * </ul>
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> type) {
        List<T> list = new ArrayList<T>();
        if (TextUtils.isEmpty(jsonStr))
            return list;
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        for (Object obj : jsonArr) {
            if (obj instanceof JSONObject) {
                JSONObject _obj = (JSONObject) obj;
                list.add((T) JSONObject.toBean(_obj, type));
            }
        }
        return list;
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * <p>方法:jsonStr2List 把字符串转为列表 </p>
     * <ul>
     * <li> @param jsonStr json格式字符串</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 14:25  </li>
     * </ul>
     */
    public static List<Map<String, Object>> jsonStr2List(String jsonStr) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            JSONArray jsonArray = JSONArray.fromObject(jsonStr);
            List<Object> objs = jsonToList(jsonArray);
            for (Object obj : objs) {
                result.add((Map<String, Object>) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
   *<p>功能描述：jsonToList    解析 传递从表中的数据 返回List<T></p >
   *<ul>
   *<li>@param [obj, clzz]</li>
   *<li>@return java.util.List<org.apache.poi.ss.formula.functions.T></li>
   *<li>@throws </li>
   *<li>@author xuby</li>
   *<li>@date 2017/4/26 0026 下午 2:15</li>
   *</ul>
   */
    public static <T> List<T> jsonToList(List<Map<String, Object>> listM, Class<T> type) {
        List<T> list = new ArrayList<T>();
        if (listM != null && listM.size() > 0) {
            for (Map<String, Object> map : listM) {
                T obj = map2Bean(map, type);
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {

        if (TextUtils.isEmpty(json))
            return new HashMap<String, Object>();
        JSONObject obj = JSONObject.fromObject(json);
        return jsonToMap(obj);
    }


    public static Map<String, Object> jsonToMap(String json, String... name) {
        if (TextUtils.isEmpty(json))
            return new HashMap<String, Object>();
        JSONObject obj = JSONObject.fromObject(json);
        return jsonToMap(obj, name);
    }

    /**
     * 将JSONObject转换成map对象
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject obj, String... dates) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }
        }
        for (String date : dates) {
            if (!(map.get(date) instanceof JSONNull)) {
                Date time = DateUtil.getDate((String) map.get(date), "yyyy-MM-dd HH:mm:ss");
                map.put(date, time);
            }
        }
        return map;
    }

    /**
     * <p>方法:obj2Map 对象转成map </p>
     * <ul>
     * <li> @param obj json对象</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/12 19:58  </li>
     * </ul>
     */
    public static Map<String, Object> obj2Map(Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (obj instanceof MorphDynaBean) {
            MorphDynaBean bean = (MorphDynaBean) obj;
            DynaClass claz = bean.getDynaClass();
            DynaProperty[] args = claz.getDynaProperties();
            for (DynaProperty dynaProperty : args) {
                if (bean.get(dynaProperty.getName()) instanceof MorphDynaBean) {
                    result.put(dynaProperty.getName(),
                            obj2Map(bean.get(dynaProperty.getName())));
                } else
                    result.put(dynaProperty.getName(),
                            bean.get(dynaProperty.getName()));
            }
            return result;
        }
        return null;
    }


    /**
     * <p>方法:map2Bean 把Map转成具体的对象 </p>
     * <ul>
     * <li> @param dataMap 传递过来的Map对象数据</li>
     * <li> @param claz 需要转换的实例类</li>
     * <li> @param includePropeties 需要转换的关键字，单项要求 key1:key2,key1是map中的关键字，key2为需要转换对象中的属性，两者属性必须一致</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/12 20:16  </li>
     * </ul>
     */
    public static <T> T map2Bean(Map<String, Object> dataMap, Class<T> claz, String[] includePropeties) {
        T obj = null;
        try {
            obj = claz.newInstance();
        } catch (Exception e) {
            System.out.println("实例化错误");
            e.printStackTrace();
        }
        for (String key : includePropeties) {
//            System.out.println(BeanUtil.getReturnType(obj, key));
            if (dataMap.get(key) instanceof JSONNull)
                BeanUtil.setProperty(obj, key, null);
            else
                BeanUtil.setProperty(obj, key, dataMap.get(key));
        }
        return obj;
    }

    /**
     * <p>接口 JsonUtil.java : <p>
     * <p>说明：map2bean</p>
     * <pre>
     * @author cc
     * @date 2017/4/18 10:14
     * </pre>
     */
    public static <T> T map2Bean(Map<String, Object> dataMap, Class<T> claz) {
        T obj = null;
        try {
            obj = claz.newInstance();
        } catch (Exception e) {
            System.out.println("实例化错误");
            e.printStackTrace();
        }
        //存在格式转换的问题。
        Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz);
        if (obj != null) {
            Set<String> set = typeMap.keySet();
            for (String key : set) {
                Object _obj = convert(typeMap.get(key), dataMap.get(key));
                if (null != _obj)
                    BeanUtil.setProperty(obj, key, _obj);
            }
        }
        return obj;
    }

    //根据不同的类型进行转换
    public static Object convert(Class clz, Object obj) {
        if (null == obj || obj instanceof JSONNull)
            return null;
        if (obj instanceof Map)
            return null;
//        System.out.println(clz.getName());
        if ("java.lang.Integer".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return Integer.valueOf(obj.toString());
        }
        if ("java.lang.Double".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return Double.valueOf(obj.toString());
        }
        if ("java.lang.Short".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return Short.valueOf(obj.toString());
        }
        if ("java.lang.String".equals(clz.getName())) {
            if (obj instanceof ArrayList) {
                return JsonUtil.toJson(((List) obj).toArray());
            }
            return obj.toString();
        }
        if ("java.lang.Byte".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return new Byte(obj.toString());
        }
        if ("java.lang.BigDecimal".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return new BigDecimal(obj.toString());
        }
        if ("java.math.BigDecimal".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return new BigDecimal(obj.toString());
        }
        if ("java.util.Date".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return DateUtil.getDate(obj.toString(), "yyyy-MM-dd HH:mm:ss");
        }

        return obj;
    }

    /**
     * <p>方法:map2List 把Map转换为具体的对象列表 </p>
     * <ul>
     * <li> @param dataMaps map的列表</li>
     * <li> @param claz 需要转换的对象</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/19 8:58  </li>
     * </ul>
     */
    public static <T> List<T> map2List(List<Map<String, Object>> dataMaps, Class<T> claz) {
        if (null == dataMaps || dataMaps.size() <= 0)
            return null;
        List<T> datas = new ArrayList<T>(dataMaps.size());
        //存在格式转换的问题。
        Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz);
        Set<String> keys = typeMap.keySet();
        for (Map<String, Object> map : dataMaps) {
            try {
                T obj = claz.newInstance();
                for (String key : keys) {
                    if (map.get(key) instanceof JSONNull || null == map.get(key)) {
                        try {
                            BeanUtil.setProperty(obj, key, null);
                        } catch (Exception e) {
                            continue;
                        }
                    } else {
//                        System.out.println(key);
                        Object _obj = convert2Object(map.get(key), typeMap.get(key));
                        if (null != _obj) {
                            try {
                                BeanUtil.setProperty(obj, key, _obj);
                            } catch (Exception e) {
                                continue;
                            }
                        }

                    }
                }
                datas.add(obj);
            } catch (Exception e) {
                System.out.println("实例化错误");
                e.printStackTrace();
            }
        }
        return datas;
    }

    private static Object convert2Object(Object obj, Class z) {
        if (z.getName().equalsIgnoreCase("java.lang.String")) {
            return BeanUtil.convert(z, obj);
        }
        if (obj instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) obj;
            try {
                return map2Bean(dataMap, z);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else if (obj instanceof List) {
            return null;
        } else
            return BeanUtil.convert(z, obj);
    }


    public static void main(String[] args) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("goodsId", "test");
        dataMap.put("name", "测试数据");
        dataMap.put("density", 11.11);


    }

    /**
     * 获取对象属性，返回一个字符串数组
     *
     * @param
     * @return String[] 字符串数组
     */

    public static String[] getFiledName(Object o) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return null;

    }

    /**
     * <p>方法:removeNullAttribute 去除對象中是空的數據 </p>
     * <ul>
     * <li> @param o TODO</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author JackZho </li>
     * <li>@date 2017/12/23 15:46  </li>
     * </ul>
     */
    public static Object removeNullAttribute(Object o) {
        if (o instanceof Map) {
            Map<String, Object> objectMap = (Map<String, Object>) o;
            Iterator iterator = objectMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (TextUtils.isEmpty(objectMap.get(key))) {
                    iterator.remove();
                    objectMap.remove(key);
                }
            }
            return objectMap;
        }
        return null;
    }
}
