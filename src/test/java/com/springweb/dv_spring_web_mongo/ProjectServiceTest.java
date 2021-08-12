package com.springweb.dv_spring_web_mongo;

import com.springweb.dv_spring_web_mongo.configuration.Config;
import com.springweb.dv_spring_web_mongo.dto.ProjectDTO;
import com.springweb.dv_spring_web_mongo.dto.ProjectCreateOrUpdateDTO;
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
        List<ProjectDTO> list1 = projectService.getAllProjects(null, null, null);
        ProjectDTO project = list1.get(0);
        //then
        Assertions.assertTrue(list1.size() == 2);
        Assertions.assertFalse(project == null);
        Assertions.assertEquals(testId, project.getId());
        Assertions.assertEquals(testName, project.getProjectName());
        //when
        List<ProjectDTO> list2 = projectService.getAllProjects("another", null, null);
        ProjectDTO project2 = list2.get(0);
        //then
        Assertions.assertTrue(list2.size() == 1);
        Assertions.assertFalse(project2 == null);
        Assertions.assertEquals(secondProjectId, project2.getId());
        Assertions.assertEquals(secondProjectName, project2.getProjectName());
    }

    @Test
    public void getAllProjectsPaginationAndFilteringTest() {
        //given
        projectRepository.deleteAll();
        Project testProject1 = new Project("", "Project one");
        Project testProject2 = new Project("", "Project two");
        Project testProject3 = new Project("", "Project three");
        projectRepository.save(testProject1);
        projectRepository.save(testProject1);
        projectRepository.save(testProject1);
        //when
        List list1 = projectService.getAllProjects(null, 1, 1);
        List list2 = projectService.getAllProjects(null, 2, 1);
        List list3 = projectService.getAllProjects(null, 3, 1);
        List list4 = projectService.getAllProjects(null, 2, 2);
        List list5 = projectService.getAllProjects("nothing", null, null);
        //then
        Assertions.assertTrue(list1.size() == 1);
        Assertions.assertTrue(list2.size() == 2);
        Assertions.assertTrue(list3.size() == 3);
        Assertions.assertTrue(list4.size() == 1);
        Assertions.assertTrue(list5.isEmpty());
        Assertions.assertFalse(list1.stream().anyMatch(e -> (e.equals(null))));
        Assertions.assertFalse(list2.stream().anyMatch(e -> (e.equals(null))));
        Assertions.assertFalse(list3.stream().anyMatch(e -> (e.equals(null))));
        Assertions.assertFalse(list4.stream().anyMatch(e -> (e.equals(null))));
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects(null, null, 2);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects(null, 1, null);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects(null, 0, 4);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects(null, 7, 0);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects("none", null, 2);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects("none", 5, null);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects("none", 0, 8);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            projectService.getAllProjects("none", 6, 0);
        });
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
        Assertions.assertThrows(ProjectNotFoundException.class, () -> {
            projectService.getProjectById("000");
        });
    }

    @Test
    public void addNewProjectTest() {
        //given
        projectRepository.deleteAll();
        projectService.addNewProject(new ProjectCreateOrUpdateDTO(testProject.getProjectName()));
        //when
        Project actual = projectRepository.findProjectByProjectName(testProject.getProjectName());
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
        ProjectCreateOrUpdateDTO entityWithUpdatedString = new ProjectCreateOrUpdateDTO(expectedResult);
        projectService.changeProjectName(entityWithUpdatedString, testId);
        Project actual = projectRepository.findById(testId).get();
        //then
        Assertions.assertEquals(expectedResult, actual.getProjectName());
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertThrows(ProjectNotFoundException.class, () -> {
            projectService.changeProjectName(null, "000");
        });
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
        Assertions.assertThrows(ProjectNotFoundException.class, () -> {
            projectService.deleteProject("000");
        });
    }
}
