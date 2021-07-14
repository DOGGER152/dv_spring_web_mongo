package com.springweb.dv_spring_web_mongo.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {

    public Project(String projectKey, String projectName) {
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
