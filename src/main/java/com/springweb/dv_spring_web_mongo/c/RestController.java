package com.springweb.dv_spring_web_mongo.c;

import com.springweb.dv_spring_web_mongo.Model.Project;
import com.springweb.dv_spring_web_mongo.MongoConnect;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        MongoOperations mongoOperations = MongoConnect.createConnection();
        List<Project> list = mongoOperations.findAll(Project.class);
        return list;
    }

    @GetMapping("projects/{id}")
    public Project getProjectById(@PathVariable int id){
        MongoOperations mongoOperations = MongoConnect.createConnection();
        Query search = new Query(Criteria.where("id").is(id)) ;
        Project project  = mongoOperations.findOne(search, Project.class);
        return project;
    }

    @PostMapping("/projects")
    public void addNewProject(@RequestBody Project project){
        MongoOperations mongoOperations = MongoConnect.createConnection();
        mongoOperations.save(project);
    }
    @PutMapping("/projects")
    public String updateProject(@RequestBody Project project){
        MongoOperations mongoOperations = MongoConnect.createConnection();
        Query search = new Query(Criteria.where("id").is(project.getId()));
        mongoOperations.updateFirst(search, Update.update("name",project.getProjectName()),Project.class);
        return new String("Updated!");
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable int id){
        MongoOperations mongoOperations = MongoConnect.createConnection();
        Query search = new Query(Criteria.where("id").is(id));
        mongoOperations.remove(search,Project.class);
    }
}
