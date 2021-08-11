package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTOGet;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTOSend;
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

    public ProjectDTOGet convertToDTOGet() {
        return new ProjectDTOGet(this.id, this.projectName);
    }

    public ProjectDTOSend convertToDTOSend() {
        return new ProjectDTOSend(this.id, this.projectName);
    }
}
