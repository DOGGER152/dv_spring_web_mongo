package com.springweb.dv_spring_web_mongo.exception_handling;

public class ProjectEmptyFieldsException extends RuntimeException {

    String errorMessage;

    public ProjectEmptyFieldsException(String errorMessage) {
        super(errorMessage);
    }
}
