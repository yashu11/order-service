package com.knowledge.practice.orderservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {

    @Before("execution(* com.knowledge.practice.orderservice.controller.*.*(..))")
    public void logRequest(JoinPoint joinPoint) {
        log.info("Incoming request {} to Order Controller {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }
}
