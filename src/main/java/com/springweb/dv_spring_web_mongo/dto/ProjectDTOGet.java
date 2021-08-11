package com.springweb.dv_spring_web_mongo.dto;

import com.springweb.dv_spring_web_mongo.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTOGet {

    @Id
    private String id;

    @NotNull
    private String projectName;

    public Project convertToProject() {
        return new Project(this.id, this.projectName);
    }
}
