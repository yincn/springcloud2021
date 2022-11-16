package com.example.controller;

import com.example.res.Response;
import com.example.util.ResponseUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yincn
 * @Date 2022/3/15
 */
@DefaultProperties(defaultFallback = "fallback")
@RestController
public class ProductController {

    Logger log = LoggerFactory.getLogger(ProductController.class);

    @HystrixCommand
    @GetMapping("/product/{id}")
    public Response<Object> getProduct(HttpServletRequest request, @PathVariable("id") Long id) {
        log.info("getProduct is calling, id:{}", id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        return ResponseUtil.genResp(data);
    }

    @RequestMapping("/product/name")
    public String productName(HttpServletRequest request) {
        log.info("productName is calling...");
        return String.valueOf(new Date());
    }

    public Response<Object> fallback() {
        log.warn("hystrix fallback");
        Map<String, Object> data = new HashMap<>();
        data.put("reason", "fallback");
        return ResponseUtil.genResp(data);
    }
}
