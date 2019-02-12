package com.ifood.test.playlistbyweather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@Transactional
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(RedisService.class);


    public Optional getFromCache(String key){
        log.info(String.format("Searching from cache %s",key));
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public void setInCache(String key,Object value, Integer expire){
        log.info(String.format("Settinh in cache %s:%s",key,value));
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,expire, TimeUnit.HOURS);
    }

    public void remove(String key){
        log.info(String.format("Cleaning cache %s",key));
        redisTemplate.delete(key);
    }
}
