package com.springweb.dv_spring_web_mongo.model;

import com.springweb.dv_spring_web_mongo.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class User {

    private String userName;

    private String password;

    private UserRole userRole;

}
