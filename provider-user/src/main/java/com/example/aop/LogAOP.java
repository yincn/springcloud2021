package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Yincn
 * @Date 2022/3/18
 */
@Aspect
@Component
public class LogAOP {

    Logger log = LoggerFactory.getLogger(LogAOP.class);

    @Pointcut("execution(* com.example.controller.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info(String.valueOf(new Date()));
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return obj;
    }
}
