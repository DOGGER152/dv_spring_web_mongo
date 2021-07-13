package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class ProjectModel {

    public ProjectModel(String projectKey, String projectName) {
        this.projectName = projectName;
        this.projectKey = projectKey;
    }

    @Id
    private int id;

    private String projectKey;

    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    @Override
    public String toString() {
        return "projectModel{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
