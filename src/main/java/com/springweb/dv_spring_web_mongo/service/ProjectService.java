package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;

import java.util.List;

public interface ProjectService {

    Project getById(String projectId);

    List<ProjectDTO> getAllProjects(String filterProjectName, Integer pageSize, Integer pageNumber);

    void addNewProject(ProjectCreateOrUpdateDTO projectCreateOrUpdateDTO, String id);

    void changeProjectName(ProjectCreateOrUpdateDTO projectCreateOrUpdateDTO, String id);

    void deleteProject(String id);

    ProjectDTO getProjectById(String id);
}
