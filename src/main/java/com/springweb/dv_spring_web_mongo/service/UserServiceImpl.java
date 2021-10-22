package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.UserCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import com.springweb.dv_spring_web_mongo.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

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

    public ResponseEntity<Map<Object, Object>> loginWithToken(UserCreateOrUpdateDTO dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        Optional<User> optional = userRepository.findUserByUsername(dto.getUsername());
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", dto.getUsername()));
        }
        Map<Object, Object> map = new HashMap<>();
        String token = jwtProvider.createToken(dto.getUsername(), dto.getRoleSet());
        map.put("username", dto.getUsername());
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

    private void checkIfUserNotExists(UserCreateOrUpdateDTO dto) {
        Optional<User> optionalUser = userRepository.findUserByUsername(dto.getUsername());
        if (optionalUser.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with name '%s' already exists", dto.getUsername()));
        }

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
