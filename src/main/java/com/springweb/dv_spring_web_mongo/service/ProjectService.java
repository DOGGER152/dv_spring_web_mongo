package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<ProjectDTO> getAllProjects() {
        List<Project> list = projectRepository.findAll();
        List<ProjectDTO> listWithDto = new LinkedList<>();
        for (Project project : list) {
            listWithDto.add(project.convertToDTO());
        }
        return listWithDto;
    }

    public ProjectDTO getProjectById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.get().convertToDTO();
    }

    public void addNewProject(ProjectDTO projectDTO) {
        projectRepository.save(projectDTO.convertToProject());
    }

    public void changeProjectName(ProjectDTO projectDTO, String id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        Project project = projectOptional.get();
        project.setProjectName(projectDTO.getProjectName());
        projectRepository.save(project);
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
