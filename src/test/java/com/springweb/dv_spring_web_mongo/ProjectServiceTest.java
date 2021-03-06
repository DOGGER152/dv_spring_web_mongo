package com.springweb.dv_spring_web_mongo;

import com.springweb.dv_spring_web_mongo.configuration.Config;
import com.springweb.dv_spring_web_mongo.dto.ProjectCreateOrUpdateDTO;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.exception.BadRequestException;
import com.springweb.dv_spring_web_mongo.exception.ProjectNotFoundException;
import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.repository.ProjectRepository;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(classes = Config.class)
@AutoConfigureDataMongo
public class ProjectServiceTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    private final String testName = "Test project";

    private final String testId = "12345";

    private final Project testProject = new Project(testName);

    @Test
    public void getAllProjectsTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        String secondProjectName = "Another test project";
        projectRepository.save(new Project(secondProjectName));
        //when
        List<ProjectDTO> list1 = projectService.getAllProjects(null, null, null);
        ProjectDTO project = list1.get(0);
        //then
        Assertions.assertEquals(2, list1.size());
        Assertions.assertNotNull(project);
        Assertions.assertEquals(testName, project.getProjectName());
        //when
        List<ProjectDTO> list2 = projectService.getAllProjects("another", null, null);
        ProjectDTO project2 = list2.get(0);
        //then
        Assertions.assertEquals(1, list2.size());
        Assertions.assertNotNull(project2);
        Assertions.assertEquals(secondProjectName, project2.getProjectName());
    }

    @Test
    public void getAllProjectsPaginationAndFilteringTest() {
        //given
        projectRepository.deleteAll();
        Project testProject1 = new Project("Project one");
        Project testProject2 = new Project("Project two");
        Project testProject3 = new Project("Project three");
        projectRepository.save(testProject1);
        projectRepository.save(testProject2);
        projectRepository.save(testProject3);
        //when
        List<ProjectDTO> list1 = projectService.getAllProjects(null, 1, 1);
        List<ProjectDTO> list2 = projectService.getAllProjects(null, 2, 1);
        List<ProjectDTO> list3 = projectService.getAllProjects(null, 3, 1);
        List<ProjectDTO> list4 = projectService.getAllProjects(null, 2, 2);
        List<ProjectDTO> list5 = projectService.getAllProjects("nothing", null, null);
        //then
        Assertions.assertEquals(1, list1.size());
        Assertions.assertEquals(2, list2.size());
        Assertions.assertEquals(3, list3.size());
        Assertions.assertEquals(1, list4.size());
        Assertions.assertTrue(list5.isEmpty());
        Assertions.assertFalse(list1.stream().anyMatch(Objects::isNull));
        Assertions.assertFalse(list2.stream().anyMatch(Objects::isNull));
        Assertions.assertFalse(list3.stream().anyMatch(Objects::isNull));
        Assertions.assertFalse(list4.stream().anyMatch(Objects::isNull));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects(null, null, 2));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects(null, 1, null));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects(null, 0, 4));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects(null, 7, 0));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects("none", null, 2));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects("none", 5, null));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects("none", 0, 8));
        Assertions.assertThrows(BadRequestException.class, () ->
                projectService.getAllProjects("none", 6, 0));
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
        Assertions.assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById("000"));
    }

    @Test
    public void addNewProjectTest() {
        //given
        projectRepository.deleteAll();
        String testOwnerId = "TestOwnerId";
        projectService.addNewProject(new ProjectCreateOrUpdateDTO(testName), testOwnerId);
        //when
        Project actual = projectRepository.findProjectByProjectName(testProject.getProjectName());
        //then
        Assertions.assertEquals(testProject.getProjectName(), actual.getProjectName());
        Assertions.assertEquals(testOwnerId, actual.getOwnerId());
    }

    @Test
    public void changeProjectNameTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        String expectedResult = "Updated Project";
        ProjectCreateOrUpdateDTO entityWithUpdatedString = new ProjectCreateOrUpdateDTO(expectedResult);
        projectService.changeProjectName(entityWithUpdatedString
                , projectRepository.findProjectByProjectName(testName).getId());
        Project actual = projectRepository.findProjectByProjectName(expectedResult);
        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedResult, actual.getProjectName());
        Assertions.assertThrows(ProjectNotFoundException.class, () ->
                projectService.changeProjectName(entityWithUpdatedString, "000"));
    }

    @Test
    public void deleteProjectTest() {
        //given
        projectRepository.deleteAll();
        projectRepository.save(testProject);
        //when
        projectService.deleteProject(testProject.getId());
        Optional<Project> optional = projectRepository.findById(testProject.getId());
        //then
        Assertions.assertTrue(optional.isEmpty());
        Assertions.assertThrows(ProjectNotFoundException.class, () ->
                projectService.deleteProject("000"));
    }
}
