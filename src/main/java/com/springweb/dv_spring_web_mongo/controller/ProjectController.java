package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.dto.ProjectCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getAllProjects(@RequestParam(required = false) String filterProjectName,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) Integer pageNumber) {
        return projectService.getAllProjects(filterProjectName, pageSize, pageNumber);
    }

    @GetMapping("/{id}")
    public ProjectDTO findProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @PostMapping()
    public void addNewProject(@Valid @RequestBody ProjectCreateOrUpdateDTO projectCreateOrUpdateDTO) {
        projectService.addNewProject(projectCreateOrUpdateDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void changeProjectName(@Valid @RequestBody ProjectCreateOrUpdateDTO projectCreateOrUpdateDTO, @PathVariable String id) {
        projectService.changeProjectName(projectCreateOrUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }
}
