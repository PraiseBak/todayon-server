package com.kkpae.todayon.AOP;

import com.kkpae.todayon.exception.TodayOnException;
import com.praiseutil.timelog.utility.LogTrace;
import com.praiseutil.timelog.utility.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Log
public class TimeLogAOP {

    private final LogTrace logTrace;

    @Pointcut("execution(* com.kkpae.todayon.service..*(..))")
    public void allService() {}

    @Pointcut("execution(* com.kkpae.todayon.controller..*(..))")
    public void allController() {}

    @Around("allController() || allService()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus traceStatus = logTrace.begin(joinPoint.getSignature().toShortString());
        try {
            Object result = joinPoint.proceed();
            logTrace.end(traceStatus);
            return result;
        } catch (TodayOnException e) {
            logTrace.begin(e.getResponse().msg());
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}