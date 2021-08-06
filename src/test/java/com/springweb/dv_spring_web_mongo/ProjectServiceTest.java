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
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    private String testName = "Test project";

    private String testId = "12345";

    private Project testProject = new Project(testId, testName);

    @Test
    public void getAllProjectsTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        String secondProjectName = "Another test project";
        String secondProjectId = "54321";
        projectRepository.save(new Project(secondProjectId, secondProjectName));
        //when
        List<ProjectDTO> list1 = projectService.getAllProjects(null);
        ProjectDTO project = list1.get(0);
        //then
        Assertions.assertTrue(list1.size() == 2);
        Assertions.assertFalse(project == null);
        Assertions.assertEquals(testName, project.getProjectName());
        //when
        List<ProjectDTO> list2 = projectService.getAllProjects("another");
        ProjectDTO project2 = list2.get(0);
        //then
        Assertions.assertTrue(list2.size() == 1);
        Assertions.assertFalse(project2 == null);
        Assertions.assertEquals(secondProjectName, project2.getProjectName());
    }

    @Test
    public void getProjectByIdTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        ProjectDTO actual = projectService.getProjectById(testProject.getId());
        //then
        Assertions.assertEquals(testProject.getProjectName(), actual.getProjectName());
    }

    @Test
    public void addNewProjectTest() {
        //given
        projectRepository.deleteAll();
        projectService.addNewProject(testProject.convertToDTO());
        //when
        Project actual = projectRepository.findProjectByProjectNameIs(testName);
        //then
        Assertions.assertEquals(testProject.getProjectName(), actual.getProjectName());
    }

    @Test
    public void changeProjectNameTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        String expectedResult = "Updated Project";
        ProjectDTO entityWithUpdatedString = new ProjectDTO(expectedResult);
        projectService.changeProjectName(entityWithUpdatedString, testId);
        Project actual = projectRepository.findById(testId).get();
        //then
        Assertions.assertEquals(expectedResult, actual.getProjectName());
        Assertions.assertEquals(testId, actual.getId());
    }

    @Test
    public void deleteProjectTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        projectService.deleteProject(testProject.getId());
        Optional optional = projectRepository.findById(testProject.getId());
        //then
        Assertions.assertTrue(optional.isEmpty());
    }
}
