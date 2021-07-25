package controller;

import dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping()
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO findProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @PostMapping()
    public void addNewProject(@RequestBody ProjectDTO projectDTO) {
        projectService.addNewProject(projectDTO);
    }

    @PutMapping("/{id}")
    public void changeProjectName(@RequestBody ProjectDTO projectDTO, @PathVariable String id) {
        projectService.changeProjectName(projectDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }
}
