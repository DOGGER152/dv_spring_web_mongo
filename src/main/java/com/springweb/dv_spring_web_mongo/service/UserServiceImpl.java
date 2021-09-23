package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerNewUser(UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        checkIfUserNotExists(userCreateOrUpdateDTO);
        userCreateOrUpdateDTO.setPassword(passwordEncoder.encode(userCreateOrUpdateDTO.getPassword()));
        userCreateOrUpdateDTO.setRoleSet(Collections.singleton(Role.ROLE_USER));
        userRepository.save(userCreateOrUpdateDTO.convertToUser());
    }

    public void updatePassword(UserCreateOrUpdateDTO userCreateOrUpdateDTO) {
        User userToUpdate = userCreateOrUpdateDTO.convertToUser();
        userToUpdate.setPassword(passwordEncoder.encode(userCreateOrUpdateDTO.getPassword()));
        userRepository.save(userToUpdate);
    }

    private void checkIfUserNotExists(UserCreateOrUpdateDTO dto) {
        Optional<User> optionalUser = userRepository.findUserByUsername(dto.getUserName());
        if (optionalUser.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with name '%s' already exists", dto.getUserName()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with name '%s' not found", username));
        }
        return optionalUser.get();
    }

    @PostConstruct
    private void createDefaultUsers() {
        createUserIfNotExists("user", "user", Role.ROLE_USER);
        createUserIfNotExists("admin", "admin", Role.ROLE_ADMIN);
    }

    private void createUserIfNotExists(String username, String password, Role role) {
        Optional<User> optional = userRepository.findUserByUsername(username);
        if (optional.isEmpty()) {
            User user = new User(username, passwordEncoder.encode(password), Collections.singleton(role));
            userRepository.save(user);
        }
    }
}
