package com.springweb.dv_spring_web_mongo.repository;

import com.springweb.dv_spring_web_mongo.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {

    List<Project> findAllByProjectNameMatchesRegexOrderById(String regex);

    List<Project> findAllByProjectNameMatchesRegexOrderById(String regex, Pageable pageable);

}
