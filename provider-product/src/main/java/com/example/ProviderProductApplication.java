package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class ProviderProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderProductApplication.class, args);
	}
}
