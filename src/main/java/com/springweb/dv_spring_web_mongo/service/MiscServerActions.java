package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MiscServerActions {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public MiscServerActions(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createDefaultUsers() {
        User user = new User("user"
                , passwordEncoder.encode("user")
                , Collections.singleton(Role.ROLE_USER));

        User admin = new User("admin"
                , passwordEncoder.encode("admin")
                , Collections.singleton(Role.ROLE_ADMIN));

        Optional<User> optionalUser = userRepository.findOne(Example.of(user));
        if (optionalUser.isEmpty()) {
            userRepository.save(user);
        }

        Optional<User> optionalAdmin = userRepository.findOne(
                Example.of(admin));
        if (optionalAdmin.isEmpty()) {
            userRepository.save(admin);
        }
    }
}
