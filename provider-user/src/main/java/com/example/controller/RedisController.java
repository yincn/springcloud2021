package com.example.controller;

import com.example.req.UserLoginReq;
import com.example.res.Response;
import com.example.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yincn
 * @Date 2022/3/23
 */
@Slf4j
@RestController
public class RedisController {

    @Autowired(required = false)
    RedisTemplate<String, Object> redisTemplate;
    
    @Autowired(required = false)
    RedissonClient redissonClient;

    @PutMapping("/redis")
    public Response<Object> add(HttpServletRequest request, @RequestBody UserLoginReq req) {
        log.info("redis add is calling, name:{}", req.getUsername());
        redisTemplate.opsForHash().put("user", "name", req.getUsername());
        Map<String, Object> data = new HashMap<>();
        data.put("id", req.getUsername());
        return ResponseUtil.genResp(data);
    }

    @GetMapping("/redisson/lock")
    public Response<Object> lock(HttpServletRequest request) {
        String username = request.getParameter("username");
        log.info("redis lock is calling, name:{}", username);

        RLock lock = redissonClient.getLock(username);
        lock.lock();
        try {
            Thread.sleep(40000);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("id", username);
        return ResponseUtil.genResp(data);
    }
}
