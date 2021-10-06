package com.springweb.dv_spring_web_mongo.dto;

import com.springweb.dv_spring_web_mongo.model.Role;
import com.springweb.dv_spring_web_mongo.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserCreateOrUpdateDTO {
    @NotNull
    @NonNull
    private String username;

    @NotNull
    @NonNull
    private String password;

    private Set<Role> roleSet;

    public User convertToUser() {
        return new User(username, password, roleSet);
    }
}
