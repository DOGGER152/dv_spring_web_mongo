package com.springweb.dv_spring_web_mongo.model;

import com.springweb.dv_spring_web_mongo.Role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Document(collection = "Users")
@Data
@NoArgsConstructor
public class User {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User(String id, String userName, String password, Set<Role> roleCollection) {
        this.id = id;
        this.userName = userName;
        this.password = passwordEncoder.encode(password);
        this.roleCollection = roleCollection;
    }

    @Id
    private String id;

    private String userName;

    private String password;

    private Set<Role> roleCollection;

}
