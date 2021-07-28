package com.springweb.dv_spring_web_mongo.exception.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ProjectIncorrectData> handleException(ProjectNotFoundException ex) {
        ProjectIncorrectData projectIncorrectData = new ProjectIncorrectData();
        projectIncorrectData.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(projectIncorrectData, HttpStatus.NOT_FOUND);
    }

}
