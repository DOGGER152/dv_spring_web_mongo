package com.springweb.dv_spring_web_mongo.dto;

import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserRegisterDTO {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String userName;

    private String password;

    private Set<Role> roleSet;

    public UserRegisterDTO(String userName, String password, Set<Role> roleSet) {
        this.userName = userName;
        this.password = passwordEncoder.encode(password);
        this.roleSet = roleSet;
    }

    public User convertToUser() {
        return new User(userName, password, roleSet);
    }
}
