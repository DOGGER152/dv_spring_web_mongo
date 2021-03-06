package com.springweb.dv_spring_web_mongo.service;

import com.springweb.dv_spring_web_mongo.dto.ProjectCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.exception.BadRequestException;
import com.springweb.dv_spring_web_mongo.exception.ProjectNotFoundException;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects(String filterProjectName, Integer pageSize, Integer pageNumber) {
        List<Project> list = null;
        boolean paging = pageNumber != null;
        boolean filteringByProjectName = !(filterProjectName == null);
        if ((pageNumber == null && pageSize != null) || (pageNumber != null && pageSize == null)) {
            throw new BadRequestException("All parameters of page must be specified, or none");
        }
        if (pageNumber != null) {
            if ((pageNumber > 0 && pageSize < 1) || (pageNumber < 1 && pageSize > 0)) {
                throw new BadRequestException("All parameters of page must be specified, or none");
            }
        }
        if (!filteringByProjectName && !paging) {
            list = projectRepository.findAll();
        } else if (filteringByProjectName && !paging) {
            list = projectRepository.findAllByProjectNameMatchesRegex("(?i)" + filterProjectName);
        } else if (filteringByProjectName && paging) {
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
            list = projectRepository.findAllByProjectNameMatchesRegex(filterProjectName, pageable);
        } else if (paging) {
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
            Page<Project> page = projectRepository.findAll(pageable);
            list = page.toList();
        }
        return list.stream().map(Project::convertToDTO).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(String id) {
        return getById(id).convertToDTO();
    }

    public void addNewProject(ProjectCreateOrUpdateDTO projectCreateOrUpdateDTO, String ownerId) {
        Project project = projectCreateOrUpdateDTO.convertToProject();
        project.setOwnerId(ownerId);
        projectRepository.save(project);
    }

    public void changeProjectName(ProjectCreateOrUpdateDTO projectCreateOrUpdateDTO, String id) {
        Project project = getById(id);
        project.setProjectName(projectCreateOrUpdateDTO.getProjectName());
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
