package com.springweb.dv_spring_web_mongo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    private String roles;

    @Override
    public String getAuthority() {
        return roles;
    }
}
