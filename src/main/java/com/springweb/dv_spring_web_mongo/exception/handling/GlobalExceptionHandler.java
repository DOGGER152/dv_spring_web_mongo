package com.springweb.dv_spring_web_mongo.exception.handling;

import com.springweb.dv_spring_web_mongo.exception.BadRequestException;
import com.springweb.dv_spring_web_mongo.exception.IncorrectData;
import com.springweb.dv_spring_web_mongo.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<IncorrectData> handleException(ProjectNotFoundException ex) {
        return new ResponseEntity<>(new IncorrectData(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<IncorrectData> handleException(BadRequestException ex) {
        return new ResponseEntity<>(new IncorrectData(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<IncorrectData> handleException(MethodArgumentNotValidException ex) {
        IncorrectData projectIncorrectData = new IncorrectData();
        projectIncorrectData.setErrorMessage("Incorrect value: " + Objects.requireNonNull(ex.getFieldError()).getField());
        return new ResponseEntity<>(projectIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    ResponseEntity<IncorrectData> handleException(AccessDeniedException ex) {
        return new ResponseEntity<>(new IncorrectData("Access denied"), HttpStatus.FORBIDDEN);
    }

}
