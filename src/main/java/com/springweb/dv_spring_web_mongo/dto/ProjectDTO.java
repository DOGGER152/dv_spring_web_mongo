package com.springweb.dv_spring_web_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {

    private String id;

    @NotNull
    private String projectName;

}
