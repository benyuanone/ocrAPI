package com.ourway.base.zk.oscache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/25.
 */
public class ZKBaseCache extends GeneralCacheAdministrator {
    /**
     * 缓存刷新的时间
     */
    public int REFRESH_PERIOD = 3600 * 24 * 30;
    private String keyPrefix = "oscache"; //前缀
    private int refreshPeriod; //过期时间


    public ZKBaseCache(String keyPrefix, int refreshPeriod) {
        super();
        this.refreshPeriod = refreshPeriod;
        this.keyPrefix = keyPrefix;
    }

    public void put(String key, Object value) {
        putInCache(this.keyPrefix + "_"+key, value);
    }

    public void remove(String key) {
        flushEntry(this.keyPrefix + "_" +key);
    }

    /**
     * <p>方法:removeAll 删除指定日期所有被缓存的对象 </p>
     * <ul>
     * <li> @param date 指定日期</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 21:56  </li>
     * </ul>
     */
    public void removeAll(Date date) {
        flushAll(date);
    }

    /**
     * <p>方法:removeAll 删除所有被缓存的对象 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 21:56  </li>
     * </ul>
     */
    public void removeAll() {
        flushAll();
    }

    /**
     * <p>方法:get 获取被缓存的对象 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 21:57  </li>
     * </ul>
     */
    public Object get(String key) {
        try {
            return getFromCache(this.keyPrefix + "_" +key);
        } catch (NeedsRefreshException e) {
            this.cancelUpdate(this.keyPrefix + "_" +key);
           // e.printStackTrace();

        }
        return null;
    }

    public String getKeyStr(String key) {
        try {
            Object obj = getFromCache(this.keyPrefix + "_" +key, REFRESH_PERIOD);
            if (null != obj)
                return obj.toString();
        } catch (NeedsRefreshException e) {
            this.cancelUpdate(this.keyPrefix + "_" +key);
          //  e.printStackTrace();
        }
        return "";
    }
}
