package com.example.poc.entities;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import org.springframework.stereotype.Repository;

@Repository
public class Redis {

    private RedisConnection<String, String> connection;
    private RedisClient redisClient;

    public Redis() {
        try{
            redisClient = new RedisClient(RedisURI.create("redis://localhost:6666"));
            connection = redisClient.connect();
            System.out.println("Connected to Redis");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void set(String key, String value, int expireTime) {
        if (expireTime > 0) {
            connection.setex(key, expireTime, value);
        } else {
            connection.set(key, value);
        }
    }

    public void set(String key, String value) {
        set(key, value, 0);
    }

    public String get(String key) {
        String value = connection.get(key);
        return value != null ? value : "";
    }

    public void Close() {
        connection.close();
        redisClient.shutdown();
    }

}
