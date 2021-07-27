package com.springweb.dv_spring_web_mongo;

import com.springweb.dv_spring_web_mongo.configuration.Config;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Config.class)
@AutoConfigureDataMongo
public class ProjectServiceTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    Project testProject = new Project("12345", "Test project One");

    @Test
    public void getAllProjectsTest() {
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        List<ProjectDTO> list = projectService.getAllProjects();
        Assertions.assertTrue(list.size() == 1);
    }

    @Test
    public void getProjectByIdTest() {
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        Project actual = projectService.getProjectById(testProject.getId()).convertToProject();
        Assertions.assertEquals(testProject, actual);
    }

    @Test
    public void addNewProjectTest() {
        projectRepository.deleteAll();
        projectService.addNewProject(testProject.convertToDTO());
        Project actual = projectRepository.findById(testProject.getId()).get();
        Assertions.assertEquals(testProject.getId(), actual.getId());
        Assertions.assertEquals(testProject.getProjectName(), actual.getProjectName());
    }

    @Test
    public void changeProjectNameTest() {
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        String expectedResult = "Updated Project";
        Project entityWithUpdatedString = new Project("", expectedResult);
        projectService.changeProjectName(entityWithUpdatedString.convertToDTO(), testProject.getId());
        Project actual = projectRepository.findById(testProject.getId()).get();
        Assertions.assertEquals(expectedResult, actual.getProjectName());
    }

    @Test
    public void deleteProjectTest() {
        Project project = new Project("123", "test project");
        projectRepository.save(project);
        projectService.deleteProject(project.getId());
        Optional optional = projectRepository.findById(project.getId());
        Assertions.assertTrue(optional.isEmpty());
    }
}
