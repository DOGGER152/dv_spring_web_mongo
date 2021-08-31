package com.springweb.dv_spring_web_mongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    private String id;

    @NonNull
    private String userName;

    @NonNull
    private String password;

    @NonNull
    private Set<Role> roleSet;

}
