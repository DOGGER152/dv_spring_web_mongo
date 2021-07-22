package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepositoryCustom projectRepositoryCustom;

    public List<ProjectDTO> getAllProjects() {
        List<Project> list = projectRepositoryCustom.getAllProjects();
        List<ProjectDTO> list2 = new ArrayList<>();
        for (Project project : list) list2.add(project.convertToDTO());
        return list2;
    }

    public ProjectDTO getProjectById(String id) {
        return projectRepositoryCustom.getProjectById(id).convertToDTO();
    }

    public void addNewProject(ProjectDTO projectDTO) {
        projectRepositoryCustom.addNewProject(projectDTO.convertToProject());
    }

    public void changeProjectName(ProjectDTO projectDTO, String id) {
        projectRepositoryCustom.changeProjectName(projectDTO.convertToProject(), id);
    }

    public void deleteProject(String id) {
        projectRepositoryCustom.deleteProject(id);
    }
}
