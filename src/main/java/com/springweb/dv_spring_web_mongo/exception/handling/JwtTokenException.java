package com.springweb.dv_spring_web_mongo.exception.handling;

public class JwtTokenException extends RuntimeException {
    public JwtTokenException(String message) {
        super(message);
    }
}
