package com.ourway.base.utils;


import com.ourway.base.service.RedisService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Set;

/**
 * <p>方法 CacheUtil : <p>
 * <p>说明:统一的缓存处理类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/11 20:58
 * </pre>
 */
public class CacheUtil {
    private static Log log = LogFactory.getLog(CacheUtil.class);
    /*模板页面的键值对*/
    public static final String PAGE_TEMPLATE = "sys.pageTemplate.";


    /**
     * <p>方法:getVal 从缓存中读取数据，并返回指定的T对象 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param type T类型</li>
     * <li>@return T  如果没有则为null，如果存在则返回</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:26  </li>
     * </ul>
     */
    public static <T> T getVal(String key, Class<T> type) {

        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String result = rds.get(key);
        if (TextUtils.isEmpty(result))
            return null;
        return JsonUtil.fromJson(result, type);
    }

    /**
     * <p>方法:getVal 返回字符串 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/12 0:22  </li>
     * </ul>
     */
    public static String getVal(String key) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String result = rds.get(key);
        if (TextUtils.isEmpty(result))
            return "";
        return result;
    }

    /**
     * <p>方法:getVals 获取指定的key，返回指定对象的列表 </p>
     * <ul>
     * <li> @param key 指定的可以</li>
     * <li> @param type 指定的对象</li>
     * <li>@return java.util.List<T>  返回null或者list</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:08  </li>
     * </ul>
     */
    public static <T> List<T> getVals(String key, Class<T> type) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String result = rds.get(key);
        if (TextUtils.isEmpty(result))
            return null;
        return JsonUtil.jsonToList(result, type);
    }

    /**
     * <p>方法:setVal 指定对象存储到缓存中 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param obj 对象</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:32  </li>
     * </ul>
     */
    public static void setVal(String key, Object obj) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String val = JsonUtil.toJson(obj);
        rds.set(key, val);
    }

    /**
     * <p>方法:setVal 存儲字符串到緩存中 </p>
     * <ul>
     * <li> @param key 字符串關鍵字</li>
     * <li> @param obj 字符串</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/15 15:42  </li>
     * </ul>
     */
    public static void setVal(String key, String obj) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        rds.set(key, obj);
    }

    /**
     * <p>方法:setVal 对象中的部分属性存储到 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param obj 对象</li>
     * <li> @param includeProperty 忽略的属性</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:33  </li>
     * </ul>
     */
    public static void setVal(String key, Object obj, String[] includeProperty) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String val = JsonUtil.toJson(obj, includeProperty);
        rds.set(key, val);
    }

    /**
     * <p>方法:setVal 想缓存中保存List对象 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param obj Object列表</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:47  </li>
     * </ul>
     */
    public static void setVal(String key, List obj) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String val = JsonUtil.toJson(obj.toArray());
        rds.set(key, val);
    }

    /**
     * <p>方法:setVal 想缓存中保存List对象 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param obj list对象</li>
     * <li> @param includePropeties 需要包含的属性</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:48  </li>
     * </ul>
     */
    public static void setVal(String key, List obj, String[] includePropeties) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        String val = JsonUtil.toJson(obj.toArray(), includePropeties);
        rds.set(key, val);
    }

    /**
     * <p>方法:listKeys 根据正则表达式，返回符合的关键字列表 </p>
     * <ul>
     * <li> @param keyPattern 正则表达式</li>
     * <li>@return java.util.Set<java.lang.String> 关键字列表 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:10  </li>
     * </ul>
     */
    public static Set<String> listKeys(String keyPattern) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        return rds.listKeys(keyPattern);
    }

    /**
     * <p>方法:clearKey 删除指定key的缓存数据 </p>
     * <ul>
     * <li> @param key 指定key</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:12  </li>
     * </ul>
     */
    public static void clearKey(String key) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        rds.removeKeys(key);
    }

    /**
     * <p>方法:clearKeyPattern 根据正则表达式删除符合的缓存 </p>
     * <ul>
     * <li> @param keyPattern 正则表达式</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:13  </li>
     * </ul>
     */
    public static void clearKeyPattern(String keyPattern) {
        RedisService rds = (RedisService) SpringContextUtil.getBean("redisService");
        rds.removeKeyPattern(keyPattern);
    }
}
