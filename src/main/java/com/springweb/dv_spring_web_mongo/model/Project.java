package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.projectDto.ProjectDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LearningMongo")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Project {

    @Id
     private String id;

     private String projectName;

    public ProjectDTO convertToDTO(){
        return new ProjectDTO(this.id,this.projectName);
    }
}
