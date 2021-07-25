package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects(String param) {
        if (param.isEmpty()) {
            List<Project> list = projectRepository.findAll();
            List<ProjectDTO> listWithDto = new LinkedList<>();
            for (Project project : list) {
                listWithDto.add(project.convertToDTO());
            }
            return listWithDto;}

        else {
            ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
            Example<Project> example = Example.of(new Project(null, param), exampleMatcher);
            List<Project> list = projectRepository.findAll(example);
            List<ProjectDTO> listWithDto = new LinkedList<>();
            for (Project project : list) {
                listWithDto.add(project.convertToDTO());
                return listWithDto;
            }
        }
        return null;
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
