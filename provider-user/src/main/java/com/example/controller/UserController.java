package com.example.controller;

import com.example.req.UserLoginReq;
import com.example.res.Response;
import com.example.service.ProductService;
import com.example.util.ResponseUtil;
import com.example.util.TokenEncryptUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yincn
 * @Date 2022/3/15
 */
@DefaultProperties(defaultFallback = "fallback")
@RestController
public class UserController {

    @Resource
    ProductService productService;

    Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/login")
    public Response<Object> userLogin(@Validated @RequestBody UserLoginReq req) {
        log.info("userLogin is calling, req:{}", req);
        if (!StringUtils.isEmpty(req.getUsername()) && !StringUtils.isEmpty(req.getPassword())) {
            Map<String, Object> data = new HashMap<>();
            String token = TokenEncryptUtil.encrypt(req);
            data.put("token", token);
            return ResponseUtil.genResp(data);
        }

        return null;
    }

    @HystrixCommand
    @GetMapping("/user/{id}")
    public Response<Object> getUser(HttpServletRequest request, @PathVariable("id") Long id) {
        log.info("getUser is calling, id:{}", id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        return ResponseUtil.genResp(data);
    }

    @RequestMapping("/product/name")
    public String productName() {
        log.info("productName is calling");
        String name = productService.productName();
        log.info("name:{}", name);
        return name;
    }

    public Response<Object> fallback() {
        log.warn("hystrix fallback");
        Map<String, Object> data = new HashMap<>();
        data.put("reason", "fallback");
        return ResponseUtil.genResp(data);
    }
}
