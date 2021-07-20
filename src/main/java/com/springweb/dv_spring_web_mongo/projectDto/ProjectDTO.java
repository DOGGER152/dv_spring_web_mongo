package com.springweb.dv_spring_web_mongo.projectDto;

import com.springweb.dv_spring_web_mongo.model.Project;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LearningMongo")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProjectDTO {

    @Id
        private String id;

        private String projectName;

        public Project convertToProject(){
            return new Project(this.id,this.projectName);
        }
}
