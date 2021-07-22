package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Project {

    public Project(String id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    @Id
    private String id;

    private String projectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }
}
