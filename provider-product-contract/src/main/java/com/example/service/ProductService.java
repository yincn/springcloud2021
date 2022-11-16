package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-product")
public interface ProductService {

    @GetMapping("/product/name")
    String productName();
}
