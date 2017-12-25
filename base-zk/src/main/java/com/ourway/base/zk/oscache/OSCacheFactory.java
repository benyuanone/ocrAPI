package com.ourway.base.zk.oscache;

import com.opensymphony.oscache.base.Cache;

/**
 * Created by Administrator on 2017/5/25.
 */
public class OSCacheFactory {
    private static ZKBaseCache instance;
    private static Object lock = new Object();

    private OSCacheFactory() {
    }

    public static ZKBaseCache getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ZKBaseCache("oscache", 3600 * 24 * 365);
                }
            }
        }
        return instance;
    }

}
