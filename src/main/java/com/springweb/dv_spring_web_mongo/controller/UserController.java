package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void registerNewUser(@Valid @RequestBody UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        userService.registerNewUser(userCreateOrUpdateDTO);
    }

    @PutMapping("/updatePassword")
    public void updatePassword(@Valid @RequestBody UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        userService.updatePassword(userCreateOrUpdateDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody @Valid UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        return userService.loginWithToken(userCreateOrUpdateDTO);
    }
}
