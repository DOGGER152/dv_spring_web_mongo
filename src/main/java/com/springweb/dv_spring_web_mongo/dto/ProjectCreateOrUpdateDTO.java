package com.springweb.dv_spring_web_mongo.dto;

import com.springweb.dv_spring_web_mongo.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateOrUpdateDTO {

    @NotNull
    String projectName;

    public Project convertToProject() {
        return new Project(null, this.projectName);
    }
}
