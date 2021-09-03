package com.springweb.dv_spring_web_mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
