package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "LearningMongo")
public class Project {

    @Id
    private String id;

    private String projectName;

    private String ownerId;

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }

}
