package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserRegisterDTO;
import com.springweb.dv_spring_web_mongo.exception.UserAlreadyExistException;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(UserRegisterDTO userRegisterDTO) {
        checkIfUserExists(userRegisterDTO.getUserName());
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(userRegisterDTO.convertToUser());
    }

    public void userLogin() {

    }

    private void checkIfUserExists(String name) {
        Optional<User> optionalUser = userRepository.findUserByUserName(name);
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }
    }

}
