package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.model.Project;
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
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable String id){
        return projectService.getProjectById(id);
    }

    @PostMapping()
    public void addNewProject(@RequestBody Project project){
        projectService.addNewProject(project);
    }

    @PutMapping("/{id}")
    public void changeProjectName(@RequestBody Project project, @PathVariable String id){
        projectService.changeProjectName(project,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id){
        projectService.deleteProject(id);
    }
}
