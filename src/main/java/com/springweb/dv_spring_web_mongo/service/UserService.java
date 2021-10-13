package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void registerNewUser(UserCreateOrUpdateDTO userCreateOrUpdateDTO);

    void updatePassword(UserCreateOrUpdateDTO userCreateOrUpdateDTO);
}
