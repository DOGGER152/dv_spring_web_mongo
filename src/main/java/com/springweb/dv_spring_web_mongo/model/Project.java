package com.springweb.dv_spring_web_mongo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springweb.dv_spring_web_mongo.DTO.ProjectDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.processing.Generated;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "LearningMongo")
public class Project {

    @Id
    private String id;

    private String projectName;


}
