package com.example.config;

import com.example.filter.TranceIdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yincn
 * @Date 2022/3/19
 */
@Configuration
public class FilterConfig {

    @Autowired
    TranceIdFilter tranceIdFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(tranceIdFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
