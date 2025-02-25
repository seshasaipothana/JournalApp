package com.seshasayee.jApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            T t = objectMapper.readValue(o.toString(), entityClass);
            return t;
        } catch (Exception e) {
            log.error("Error retrieving key {} from Redis", key, e);
            return null;
        }

    }
    /*public void set(String key, Object value,long timeOut,TimeUnit timeUnit){
    redisTemplate.opsForValue().set(key,value,timeOut,timeUnit.SECONDS);
    }
     */
    public void set(String key, Object value,long timeOut){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key,jsonValue,timeOut,TimeUnit.SECONDS);
        }
        catch (Exception e){
            log.error("Error",e);

        }
    }
}
