package repository;

import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Project> findAll() {
        List<Project> list = mongoTemplate.findAll(Project.class);
        return list;
    }

    public Project findById(String id) {
        Query search = new Query(Criteria.where("_id").is(id));
        Project project = mongoTemplate.findOne(search, Project.class);
        return project;
    }

    public void insert(Project project) {
        mongoTemplate.save(project);
    }

    public void update(Project project, String id) {
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(search, Update.update("projectName", project.getProjectName()), Project.class);
    }

    public void delete(String id) {
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(search, Project.class);
    }
}
