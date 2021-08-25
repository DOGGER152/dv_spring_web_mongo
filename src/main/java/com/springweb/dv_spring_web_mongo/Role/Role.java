package com.springweb.dv_spring_web_mongo.Role;

public enum Role {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
