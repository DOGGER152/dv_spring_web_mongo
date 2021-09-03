package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.dto.UserRegisterDTO;
import com.springweb.dv_spring_web_mongo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerNewUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.registerNewUser(userRegisterDTO);
    }

    @RequestMapping("/login")
    public void userLogin() {
        userService.userLogin();
    }
}
