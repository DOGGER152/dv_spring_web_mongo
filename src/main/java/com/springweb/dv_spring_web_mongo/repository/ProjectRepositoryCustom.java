package com.springweb.dv_spring_web_mongo.repository;

import com.springweb.dv_spring_web_mongo.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @SuppressWarnings("unused")
    public List<Project> findAll() {
        return mongoTemplate.findAll(Project.class);
    }

    @SuppressWarnings("unused")
    public Project findById(String id) {
        Query search = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(search, Project.class);
    }

    @SuppressWarnings("unused")
    public void insert(Project project) {
        mongoTemplate.save(project);
    }

    @SuppressWarnings("unused")
    public void update(Project project, String id) {
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(search, Update.update("projectName", project.getProjectName()), Project.class);
    }

    @SuppressWarnings("unused")
    public void delete(String id) {
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(search, Project.class);
    }
}
