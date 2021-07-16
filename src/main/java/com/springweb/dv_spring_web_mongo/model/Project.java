package com.springweb.dv_spring_web_mongo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.processing.Generated;

@Document(collection = "LearningMongo")
public class Project {

    public Project() {}

    @Id
    private String id;

    private String projectName;

    public void setId(String id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    @Override
    public String toString() {
        return "project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
