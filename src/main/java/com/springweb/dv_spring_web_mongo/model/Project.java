package com.springweb.dv_spring_web_mongo.model;


import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "LearningMongo")

public class Project {

    @Id
    @NonNull
    private String id;

    @NonNull
    private String projectName;

    private String ownerId;

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }

}
