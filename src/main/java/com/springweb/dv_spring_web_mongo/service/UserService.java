package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.exception.UserAlreadyExistException;
import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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
        userCreateOrUpdateDTO.setRoleSet(Collections.singleton(Role.ROLE_USER));
        userRepository.save(userCreateOrUpdateDTO.convertToUser());
    }

    public void userLogin() {
        
    }

    public void updatePassword(UserCreateOrUpdateDTO userCreateOrUpdateDTO) {

        if (!checkIfUserExists(userCreateOrUpdateDTO.getUserName())) {
            throw new UsernameNotFoundException("User not found");
        }

        User userToUpdate = userCreateOrUpdateDTO.convertToUser();
        userToUpdate.setPassword(passwordEncoder.encode(userCreateOrUpdateDTO.getPassword()));
        userRepository.save(userToUpdate);
    }

    private boolean checkIfUserExists(String name) {
        Optional<User> optionalUser = userRepository.findUserByUserName(name);
        return optionalUser.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUserName(s);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User name with username '" + s + " not found");
        }
        return optionalUser.get();
    }
}
