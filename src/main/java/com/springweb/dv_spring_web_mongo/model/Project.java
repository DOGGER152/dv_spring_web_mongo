package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.dto.ProjectCreateOrUpdateDTO;
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

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }

    public ProjectCreateOrUpdateDTO convertToDTOSend() {
        return new ProjectCreateOrUpdateDTO(this.id, this.projectName);
    }
}
