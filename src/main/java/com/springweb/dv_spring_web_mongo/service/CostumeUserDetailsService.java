package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostumeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with name '%s' not found", username));
        }
        return optionalUser.get();
    }
}
