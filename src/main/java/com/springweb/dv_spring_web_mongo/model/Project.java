package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "Projects")

public class Project {

    @Id
    private String id;

    private String projectName;

    private String ownerId;

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }

}
