package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.DTO.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepositoryCustom projectRepositoryCustom;

    public List<Project> getAllProjects(){
        return projectRepositoryCustom.getAllProjects();
    }

    public Project getProjectById(String id){
        return projectRepositoryCustom.getProjectById(id);
    }

    public void addNewProject(ProjectDTO projectDTO){
        projectRepositoryCustom.addNewProject(projectDTO);
    }

    public void changeProjectName(Project project,String id){
        projectRepositoryCustom.changeProjectName(project,id);
    }

    public void deleteProject(String id){
        projectRepositoryCustom.deleteProject(id);
    }
}
