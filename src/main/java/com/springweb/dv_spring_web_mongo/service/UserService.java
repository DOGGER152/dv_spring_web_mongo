package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;

public interface UserService {

    void registerNewUser(UserCreateOrUpdateDTO userCreateOrUpdateDTO);

    void updatePassword(UserCreateOrUpdateDTO userCreateOrUpdateDTO);
}
