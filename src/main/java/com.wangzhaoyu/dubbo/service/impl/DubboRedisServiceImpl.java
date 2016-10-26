package com.wangzhaoyu.dubbo.service.impl;

import com.wangzhaoyu.dubbo.service.DubboRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author:wanghzoayu
 * @Description:
 * @Date:Create in 2016/10/18 16:41
 * @Modified By:
 */
@Service("dubboRedisService")
public class DubboRedisServiceImpl implements DubboRedisService{

    @Autowired
    private RedisTemplate<Serializable,Serializable> redisTemplate;
    @Override
    public void setObj(String key, Object obj) {
        this.setObject(key, obj,0);
    }

    @Override
    public <T> T getObj(final String key){
    T result = redisTemplate.execute(new RedisCallback<T>() {
        public T doInRedis(RedisConnection connection)
                throws DataAccessException {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] value = connection.get(byteKey);
            if (value == null) {
                return null;
            }
            return (T)serializer.deserialize(value);
        }
    });
    return result;
}

    public void setObject(final String key,final Object obj ,final long liveTime){

        try{
            redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    RedisSerializer serializer = redisTemplate.getStringSerializer();
                    byte[] byteKey = serializer.serialize(key);
                    byte[] name = serializer.serialize(obj);
                    return redisConnection.setNX(byteKey, name);
                }
            });
        }catch (Exception e){

        }
    }
}