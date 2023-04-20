package com.example.poc.controller;

import com.example.poc.entities.Redis;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/redis")
public class RedisController {

        @RequestMapping(value = "/set", method = RequestMethod.POST, produces = "application/json")
        public Map<String, String> set(@RequestBody Map<String, Object> requestBody) {
            String uid = (String) requestBody.get("uid");
            System.out.println("uid: " + uid);
            Redis redis = new Redis();
            if (redis.get("uid").equals(uid)) {
                redis.Close();
                Map<String, String> response = new HashMap<>();
                response.put("message", "UID already exists in Redis");
                return response;
            }
            redis.set("uid", uid, 20);
            redis.Close();
            Map<String, String> response = new HashMap<>();
            response.put("message", "UID " + uid + " saved in Redis");

            return response;
        }

        @GetMapping
        public String get() {
            Redis redis = new Redis();
            String uid = redis.get("uid");
            redis.Close();
            return uid;
        }
}
