package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.exception.ProjectNotFoundException;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects(String filterProjectName, int pageSize, int pageNumber) {
        List<Project> list = null;
        boolean paging = false;
        boolean filteringByProjectName = true;

        //condition switching
        if (filterProjectName == null) {
            filteringByProjectName = false;
        }
        if (pageNumber > 0 && pageSize > 0) {
            paging = true;
        }

        if (!filteringByProjectName && !paging) {
            list = projectRepository.findAll();
        } else if (filteringByProjectName && !paging) {
            list = projectRepository.findAllByProjectNameMatchesRegex("(?i)" + filterProjectName);
        } else if (filteringByProjectName && paging) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Project> page = projectRepository.findAllByProjectNameMatchesRegex(filterProjectName, pageable);
            list = page.toList();
        } else if (!filteringByProjectName && paging) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Project> page = projectRepository.findAll(pageable);
            list = page.toList();
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
