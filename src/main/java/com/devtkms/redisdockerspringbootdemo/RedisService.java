package com.devtkms.redisdockerspringbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void save(String key, String value) {
        System.out.println("Saving key: " + key + ", value: " + value);
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        String value = redisTemplate.opsForValue().get(key);
        System.out.println("Retrieving key: " + key + ", value: " + value);
        return value;
    }
}
