package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.exception.ProjectNotFoundException;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects(String filterProjectName) {
        List<Project> list;
        if (filterProjectName == null) {
            list = projectRepository.findAll();
        } else {
            list = projectRepository.findAllByProjectNameMatchesRegexOrderById("(?i)" + filterProjectName);
        }
        return list.stream().map(Project::convertToDTO).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(String id) {
        return getById(id).convertToDTO();
    }

    public void addNewProject(ProjectDTO projectDTO) {
        projectRepository.save(projectDTO.convertToProject());
    }

    public void changeProjectName(ProjectDTO projectDTO, String id) {
        Project project = getById(id);
        project.setProjectName(projectDTO.getProjectName());
        projectRepository.save(project);
    }

    public void deleteProject(String id) {
        getById(id);
        projectRepository.deleteById(id);
    }

    public Project getById(String id) {
        Optional<Project> optional = projectRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ProjectNotFoundException("Project with id '" + id + "' not found");
        }
        return optional.get();
    }

}
