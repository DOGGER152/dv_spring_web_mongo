package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.exception_handling.ProjectEmptyFieldsException;
import com.springweb.dv_spring_web_mongo.exception_handling.ProjectNotFoundException;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        List<Project> list = projectRepository.findAll();
        List<ProjectDTO> listWithDto = new LinkedList<>();
        for (Project project : list) {
            listWithDto.add(project.convertToDTO());
        }
        if (listWithDto.isEmpty()) throw new ProjectNotFoundException("Database is empty");
        else return listWithDto;
    }

    public ProjectDTO getProjectById(String id) {
        Optional<Project> optional = projectRepository.findById(id);
        if (optional.isEmpty()) throw new ProjectNotFoundException("Project with id '" + id + "' not found");
        else return optional.get().convertToDTO();
    }

    public void addNewProject(ProjectDTO projectDTO) {
        if (projectDTO.getProjectName() == null || projectDTO.getProjectName().isEmpty())
            throw new ProjectEmptyFieldsException("The property 'projectName' is not defined");
        else projectRepository.save(projectDTO.convertToProject());
    }

    public void changeProjectName(ProjectDTO projectDTO, String id) {
        Optional<Project> optional = projectRepository.findById(id);
        if (optional.isEmpty()) throw new ProjectNotFoundException("Project with id '" + id + "' not found");
        if (optional.get().getProjectName() == null || optional.get().getProjectName().isEmpty())
            throw new ProjectEmptyFieldsException("The property 'projectName' is not defined");
        else {
            Project project = optional.get();
            project.setProjectName(projectDTO.getProjectName());
            projectRepository.save(project);
        }
    }

    public void deleteProject(String id) {
        Optional<Project> optional = projectRepository.findById(id);
        if (optional.isEmpty()) throw new ProjectNotFoundException("Project with id '" + id + "' not found");
        else projectRepository.deleteById(id);
    }
}
