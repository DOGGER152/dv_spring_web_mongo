package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.dto.ProjectDTOGet;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTOSend;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public List<ProjectDTOGet> getAllProjects(@RequestParam(required = false) String filterProjectName,
                                              @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer pageNumber) {
        return projectService.getAllProjects(filterProjectName, pageSize, pageNumber);
    }

    @GetMapping("/{id}")
    public ProjectDTOGet findProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @PostMapping()
    public void addNewProject(@Valid @RequestBody ProjectDTOSend projectDTOsend) {
        projectService.addNewProject(projectDTOsend);
    }

    @PutMapping("/{id}")
    public void changeProjectName(@Valid @RequestBody ProjectDTOSend projectDTOSend, @PathVariable String id) {
        projectService.changeProjectName(projectDTOSend, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }
}
