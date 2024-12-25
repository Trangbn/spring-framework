package com.tgroup.trangbn.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class LoggerAspect {

    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Around("execution(* com.tgroup.trangbn.servicess.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info(joinPoint.getSignature().getName() + " method execution start");
        Instant start = Instant.now();
        joinPoint.proceed();
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        logger.info("Time took to execute method: " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end");
    }

    @Around("@annotation(com.tgroup.trangbn.interfaces.LogAspect)")
    public void logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info(joinPoint.getSignature().getName() + " method execution start");
        Instant start = Instant.now();
        joinPoint.proceed();
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        logger.info("Time took to execute method: " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end");
    }

    @AfterThrowing(value = "execution(* com.tgroup.trangbn.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.log(Level.SEVERE, joinPoint.getSignature() + " An exception thrown with help of "
                + " @AfterThrowing which happened due to " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* com.tgroup.trangbn.services.*.*(..))", returning = "retVal")
    public void logStatus(JoinPoint joinPoint, Object retVal) {
        logger.log(Level.INFO, joinPoint.getSignature() + " Method successfully processed with the status " + retVal.toString());
    }

}
