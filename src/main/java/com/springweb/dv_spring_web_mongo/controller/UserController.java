package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerNewUser(@Valid @RequestBody UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        userService.registerNewUser(userCreateOrUpdateDTO);
    }

    @PutMapping("/updatePassword")
    public void updatePassword(@Valid @RequestBody UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        userService.updatePassword(userCreateOrUpdateDTO);
    }

    @RequestMapping("/login")
    public void userLogin() {
        userService.userLogin();
    }

}
