package com.springweb.dv_spring_web_mongo.security;

import com.springweb.dv_spring_web_mongo.security.jwt.JwtFilter;
import com.springweb.dv_spring_web_mongo.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtProvider jwtProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
