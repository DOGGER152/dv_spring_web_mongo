package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.exception.UserAlreadyExistException;
import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        if (checkIfUserExists(userCreateOrUpdateDTO.getUserName())) {
            throw new UserAlreadyExistException("User already exists");
        }
        userCreateOrUpdateDTO.setPassword(passwordEncoder.encode(userCreateOrUpdateDTO.getPassword()));
        userCreateOrUpdateDTO.setRoleSet(Collections.singleton(new Role("ROLE_USER")));
        userRepository.save(userCreateOrUpdateDTO.convertToUser());
    }

    public void userLogin() {

    }

    public void updatePassword(UserCreateOrUpdateDTO userCreateOrUpdateDTO) {

        if (!checkIfUserExists(userCreateOrUpdateDTO.getUserName())) {
            throw new UsernameNotFoundException("User not found");
        }

        User userFromDB = userRepository.findUserByUserName(userCreateOrUpdateDTO.getUserName()).get();
        User userToUpdate = userCreateOrUpdateDTO.convertToUser();
        userToUpdate.setPassword(userFromDB.getPassword());
        userRepository.save(userToUpdate);
    }

    private boolean checkIfUserExists(String name) {
        Optional<User> optionalUser = userRepository.findUserByUserName(name);
        if (optionalUser.isPresent()) {
            return true;
        }
        return false;
    }

}
