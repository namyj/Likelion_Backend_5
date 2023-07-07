package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    // 실행 정보 출력 기능 Advice
    // @Before("this(com.example.aop.controller.AopController)")
    // @Before("@annotation(com.example.aop.aspect.LogArguments)")
    @After("@annotation(com.example.aop.aspect.LogArguments)")
    public void logParameters(JoinPoint joinPoint) {

        log.info("LoggingAspect.logParameters 실행");
        Signature signature = joinPoint.getSignature();

        log.info(signature.getName());

        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            log.info("no args");
        }

        for (Object arg : args) {
            log.info("argument : [{}]", arg); // 괄호안에 argument의 값이 알아서 들어감
        }
    }

    // 실행 시간 계산 기능 Advice
    @Around("@annotation(com.example.aop.aspect.LogExecutionTime)")
    public Object logExecutionTime(
            ProceedingJoinPoint joinPoint // Advice 내에서 JointPoint가 실행되도록 할 수 있다.
    ) throws Throwable {
        log.info("start log execution time");
        long startTime = System.currentTimeMillis();
        
        // JointPoint.proceed() : JointPoint에 해당하는 메소드를 실행, throws Throwable 예외 처리 필요
        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info("{} executed in : {}ms", joinPoint.getSignature(), endTime - startTime);

        return proceed;
    }

}
