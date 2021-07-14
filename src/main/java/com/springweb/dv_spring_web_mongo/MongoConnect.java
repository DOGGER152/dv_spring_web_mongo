package com.springweb.dv_spring_web_mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.springweb.dv_spring_web_mongo.configuration.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoConnect {

    public static MongoOperations createConnection(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MongoOperations mongoOperations = (MongoOperations) context.getBean("mongoTemplate");
        return mongoOperations;
    }
}
