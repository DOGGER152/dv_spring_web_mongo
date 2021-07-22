package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "LearningMongo")
public class Project {

    @Id
    private String id;

    private String projectName;

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }
}
