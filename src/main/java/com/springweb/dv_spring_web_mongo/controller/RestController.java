package com.springweb.dv_spring_web_mongo.controller;

import com.springweb.dv_spring_web_mongo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        List<Project> list = mongoTemplate.findAll(Project.class);
        return list;
    }

    @GetMapping("projects/{id}")
    public Project getProjectById(@PathVariable String id){
        Query search = new Query(Criteria.where("_id").is(id)) ;
        Project project  = mongoTemplate.findOne(search, Project.class);
        return project;
    }

    @PostMapping("/projects")
    public void addNewProject(@RequestBody Project project){
        mongoTemplate.save(project);
    }
    @PutMapping("/projects/{id}")
    public void changeProjectName(@RequestBody Project project, @PathVariable String id){
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(search, Update.update("projectName",project.getProjectName()),Project.class);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable String id){
        Query search = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(search,Project.class);
    }
}
