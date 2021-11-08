package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    void registerNewUser(UserCreateOrUpdateDTO userCreateOrUpdateDTO);

    void updatePassword(UserCreateOrUpdateDTO userCreateOrUpdateDTO);

    ResponseEntity<Map<Object, Object>> loginWithToken(UserCreateOrUpdateDTO userCreateOrUpdateDTO);
}
