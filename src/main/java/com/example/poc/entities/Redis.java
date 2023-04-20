package com.example.poc.entities;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Repository
public class Redis {

    private JedisPool jedisPool;

    public Redis() {
        try{
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            jedisPool = new JedisPool(poolConfig, "localhost", 6666);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void set(String key, String value, int expireTime) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (expireTime > 0) {
                jedis.setex(key, expireTime, value);
            } else {
                jedis.set(key, value);
            }
            jedisPool.close();
        }
    }

    public void set(String key, String value) {
        set(key, value, 0);
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get(key);
            jedisPool.close();
            return value != null ? value : "";
        }
    }

}
