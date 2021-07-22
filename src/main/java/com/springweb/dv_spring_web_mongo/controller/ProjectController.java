package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping()
    public List<ProjectDTO> findAll() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @PostMapping()
    public void insert(@RequestBody ProjectDTO projectDTO) {
        projectService.addNewProject(projectDTO);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ProjectDTO projectDTO, @PathVariable String id) {
        projectService.changeProjectName(projectDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        projectService.deleteProject(id);
    }
}
