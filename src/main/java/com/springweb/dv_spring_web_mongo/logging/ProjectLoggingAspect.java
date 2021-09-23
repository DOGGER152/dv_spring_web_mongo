package com.springweb.dv_spring_web_mongo.logging;

import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@RequiredArgsConstructor
public class ProjectLoggingAspect {

    private final HttpServletRequest request;
    private Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void anyRequestInProjectService() {
    }

    @Around("anyRequestInProjectService()")
    public void afterServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("----------INCOMING REQUEST----------");
        logger.info("HTTP request type: " + request.getMethod());
        logger.info("Request URI: " + request.getRequestURI());
        logger.info("Client IP: " + request.getRemoteAddr());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            logger.info("User: " + user.getUsername());
        }
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            joinPoint.proceed();
        } finally {
            stopWatch.stop();
            logger.info(String.format("Request processing time: %s millis", stopWatch.getTotalTimeMillis()));
        }

    }

}
