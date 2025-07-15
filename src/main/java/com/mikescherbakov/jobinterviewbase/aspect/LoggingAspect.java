package com.mikescherbakov.jobinterviewbase.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

  @Pointcut("within(@org.springframework.stereotype.Service *) && execution(public * *(..))")
  public void serviceMethods() {}

  @Around("serviceMethods()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long executionTime = System.currentTimeMillis() - startTime;
    log.info("Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
    return result;
  }
}
