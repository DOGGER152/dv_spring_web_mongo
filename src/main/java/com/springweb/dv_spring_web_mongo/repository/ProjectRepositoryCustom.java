package com.springweb.dv_spring_web_mongo.repository;

import com.springweb.dv_spring_web_mongo.projectDto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Project> getAllProjects(){
        List<Project> list = mongoTemplate.findAll(Project.class);
        return list;
    }

    public Project getProjectById(String id){
        Query search = new Query(Criteria.where("_id").is(id)) ;
        ProjectDTO projectDTO  = mongoTemplate.findOne(search, ProjectDTO.class);
        return projectDTO.convertToProject();
    }

    public void addNewProject(ProjectDTO projectDTO){
        mongoTemplate.save(projectDTO.convertToProject());
    }

    public void changeProjectName(Project project, String id){
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(search, Update.update("projectName",project.getProjectName()),Project.class);
    }


    public void deleteProject( String id){
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(search,Project.class);
    }
}
