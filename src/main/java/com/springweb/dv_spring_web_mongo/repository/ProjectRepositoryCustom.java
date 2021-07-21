package com.springweb.dv_spring_web_mongo.repository;

import com.springweb.dv_spring_web_mongo.projectDto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ProjectDTO> getAllProjects(){
        List<Project> list = mongoTemplate.findAll(Project.class);
        List<ProjectDTO> list2 = new ArrayList<>();
        for(Project project : list) list2.add(project.convertToDTO());
        return list2;
    }

    public ProjectDTO getProjectById(String id){
        Query search = new Query(Criteria.where("_id").is(id)) ;
        Project project  = mongoTemplate.findOne(search, Project.class);
        return project.convertToDTO();
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
