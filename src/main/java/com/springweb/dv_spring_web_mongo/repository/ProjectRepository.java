package com.springweb.dv_spring_web_mongo.repository;

import com.springweb.dv_spring_web_mongo.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProjectRepository extends MongoRepository<Project, String> {

}
