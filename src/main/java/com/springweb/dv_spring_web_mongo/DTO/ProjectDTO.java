package com.springweb.dv_spring_web_mongo.DTO;

import com.springweb.dv_spring_web_mongo.model.Project;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LearningMongo")
public class ProjectDTO {

    public ProjectDTO() {}

    public ProjectDTO(String id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

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

        public Project convertToProject(){
            return new Project(this.id,this.projectName);
        }
}
