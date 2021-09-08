package com.springweb.dv_spring_web_mongo.exception.handling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectIncorrectData {
    private String errorMessage;
}
