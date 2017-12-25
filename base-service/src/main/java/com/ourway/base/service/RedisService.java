package com.ourway.base.service;

import java.util.List;
import java.util.Set;

/**
 * <p>接口 RedisService.java : <p>
 * <p>说明：处理系统缓存redis类接口</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/11 18:19
 * </pre>
 */
public interface RedisService {

    /**
     * <p>方法:del 删除指定key的redis的值 </p>
     * <ul>
     * <li> @param keys </li>
     * <li>@return long  返回删除的个数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 18:19  </li>
     * </ul>
     */
    public abstract long removeKeys(String... keys);

    /**
     * <p>方法:removeKeys 删除key的集合 </p>
     * <ul>
     * <li> @param keys 集合</li>
     * <li>@return long  返回受影响的个数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 18:21  </li>
     * </ul>
     */
    public abstract long removeKeys(List<String> keys);

    /**
     * <p>方法:removeKeyPattern 根据正则表达式删除 </p>
     * <ul>
     * <li> @param keyPattern 正则表达式</li>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 22:14  </li>
     * </ul>
     */
    public abstract long removeKeyPattern(String keyPattern);




    /**
     * <p>方法:set 添加key value 并且设置存活时间 </p>
     * <ul>
     * <li> @param key String 类型</li>
     * <li> @param value json格式的字符串</li>
     * <li> @param liveTime 存活的时间</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 19:57  </li>
     * </ul>
     */
    public abstract void set(String key, String value, long liveTime);


    /**
     * <p>方法:set 添加key value </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param value json格式的内容</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 19:59  </li>
     * </ul>
     */
    public abstract void set(String key, String value);



    /**
     * <p>方法:get 获取redis value (String) </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li>@return java.lang.String 返回json格式的字符串 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 20:00  </li>
     * </ul>
     */
    public abstract String get(String key);


    /**
     * <p>方法:listKeys 通过正则匹配keys </p>
     * <ul>
     * <li> @param pattern 正则表达式</li>
     * <li>@return java.util.Set 返回所有符合正则表达式的关键字 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 20:06  </li>
     * </ul>
     */
    public abstract Set listKeys(String pattern);


    /**
     * <p>方法:exists 检查key是否已经存在 </p>
     * <ul>
     * <li> @param key TODO</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 20:07  </li>
     * </ul>
     */
    public abstract boolean exists(String key);


    /**
     * <p>方法:flushDB 清空redis 所有数据 </p>
     * <ul>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 20:07  </li>
     * </ul>
     */
    public abstract void flushDB();


    /**
     * <p>方法:dbSize 查看redis里有多少数据 </p>
     * <ul>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 20:09  </li>
     * </ul>
     */
    public abstract long dbSize();


    /**
     * <p>方法:ping 检查是否连接成功 </p>
     * <ul>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 20:09  </li>
     * </ul>
     */
    public abstract String ping();
}
