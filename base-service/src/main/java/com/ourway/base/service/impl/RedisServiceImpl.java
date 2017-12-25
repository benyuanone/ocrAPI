package com.ourway.base.service.impl;

import com.ourway.base.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private static String redisCode = "utf-8";

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public long removeKeys(final String... keys) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }

    @Override
    public long removeKeys(final List<String> keys) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (String key : keys) {
                    result = connection.del(key.getBytes());
                }
                return result;
            }
        });
    }

    @Override
    public long removeKeyPattern(String keyPattern) {
        final Set set = listKeys(keyPattern);
        return (Long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (Object obj : set) {
                    result = connection.del(obj.toString().getBytes());
                }
                return result;
            }
        });
    }

    @Override
    public void set(final String key, final String value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(
                        //请注意加前缀表示命名空间  例如student.uid
                        redisTemplate.getStringSerializer().serialize(key),
                        //需要序列化操作
                        redisTemplate.getStringSerializer().serialize(value)
                );
                return null;
            }
        });
    }
    @Override
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }


    @Override
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] keys = redisTemplate.getStringSerializer().serialize(key);
                    if (connection.exists(keys)) {
                        byte[] value = connection.get(keys);
                        return (String) redisTemplate.getStringSerializer().deserialize(value);
                    } else
                        return "";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
    }


    @Override
    public Set listKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }



    @Override
    public boolean exists(final String key) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }


    @Override
    public void flushDB() {
        redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "";
            }
        });
    }


    @Override
    public long dbSize() {
        return (Long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }


    @Override
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.ping();
            }
        });
    }


}
