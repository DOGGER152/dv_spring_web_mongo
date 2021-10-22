package com.springweb.dv_spring_web_mongo.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@RequiredArgsConstructor
@Log4j2
public class HttpRequestLoggingAspect {


    private final HttpServletRequest request;

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object wrapControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("----------INCOMING REQUEST----------");
        log.debug("HTTP request type: " + request.getMethod());
        log.debug("Request URI: " + request.getRequestURI());
        log.debug("Client IP: " + request.getRemoteAddr());
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                log.info("User: " + authentication.getName());
            }
        }
        StopWatch stopWatch = new StopWatch();
        Object object = null;
        try {
            stopWatch.start();
            object = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.debug("Request processing time: {} millis", stopWatch.getTotalTimeMillis());
        }
        return object;
    }

}
