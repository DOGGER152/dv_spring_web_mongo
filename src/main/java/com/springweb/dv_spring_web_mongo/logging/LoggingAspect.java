package com.springweb.dv_spring_web_mongo.logging;

import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void anyRequestInProjectService() {
    }

    @Before("anyRequestInProjectService()")
    public void beforeServiceMethod() {
        
        logger.info("Incoming HTTP request type:" + null);
    }

    @After("anyRequestInProjectService()")
    public void afterServiceMethod() {
    }

}
