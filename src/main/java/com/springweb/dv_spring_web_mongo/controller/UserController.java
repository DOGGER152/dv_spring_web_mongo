package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/register")
    public void registerNewUser() {
        userService.registerNewUser();
    }

    @RequestMapping("/login")
    public void userLogin() {
        userService.userLogin();
    }


    @RequestMapping("/logout")
    public void userLogout() {
        userService.userLogout();
    }

}
