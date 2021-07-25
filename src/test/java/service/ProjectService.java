package service;

import dto.ProjectDTO;
import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.ProjectRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        List<Project> list = projectRepository.findAll();
        List<ProjectDTO> listWithDto = new LinkedList<>();
        for (Project project : list) {
            listWithDto.add(project.convertToDTO());
        }
        return listWithDto;
    }

    public ProjectDTO getProjectById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.get().convertToDTO();
    }

    public void addNewProject(ProjectDTO projectDTO) {
        projectRepository.save(projectDTO.convertToProject());
    }

    public void changeProjectName(ProjectDTO projectDTO, String id) {
        Project project = projectRepository.findById(id).get();
        project.setProjectName(projectDTO.getProjectName());
        projectRepository.save(project);
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
