package com.springweb.dv_spring_web_mongo;

import com.springweb.dv_spring_web_mongo.Configuration.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class MongoTools {
    public static MongoOperations createConnection(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MongoOperations mongoOperations = (MongoOperations) context.getBean("mongoTemplate");
        return mongoOperations;
    }
}
