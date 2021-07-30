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
        //when
        List<ProjectDTO> list = projectService.getAllProjects(null);
        Assertions.assertTrue(list.size() == 1);
        //then
        ProjectDTO project = list.get(0);
        Assertions.assertFalse(project == null);
        Assertions.assertEquals(testId, project.getId());
        Assertions.assertEquals(testName, project.getProjectName());
    }

    @Test
    public void getProjectByIdTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        ProjectDTO actual = projectService.getProjectById(testProject.getId());
        //then
        Assertions.assertEquals(testProject.getId(), actual.getId());
        Assertions.assertEquals(testProject.getProjectName(), actual.getProjectName());
    }

    @Test
    public void addNewProjectTest() {
        //given
        projectRepository.deleteAll();
        projectService.addNewProject(testProject.convertToDTO());
        //when
        Project actual = projectRepository.findById(testProject.getId()).get();
        //then
        Assertions.assertEquals(testProject.getId(), actual.getId());
        Assertions.assertEquals(testProject.getProjectName(), actual.getProjectName());
    }

    @Test
    public void changeProjectNameTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        String expectedResult = "Updated Project";
        ProjectDTO entityWithUpdatedString = new ProjectDTO("", expectedResult);
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
