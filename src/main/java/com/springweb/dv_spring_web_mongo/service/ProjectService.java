package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
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

    public List<ProjectDTO> getAllProjects(String param) {
        List<Project> list;
        if (param == null) {
            list = projectRepository.findAll();
        } else {
            list = projectRepository.findAllByProjectNameMatchesRegexOrderById("(?i)" + param);
        }
        return list.stream().map(Project::convertToDTO).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.get().convertToDTO();
    }

    public void addNewProject(ProjectDTO projectDTO) {
        projectRepository.save(projectDTO.convertToProject());
    }

    public void changeProjectName(ProjectDTO projectDTO, String id) {
        Project project = projectRepository.findById(id).get();
        project.setProjectName(projectDTO.getProjectName());
        projectRepository.save(project);
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
