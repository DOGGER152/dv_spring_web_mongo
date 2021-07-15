package com.springweb.dv_spring_web_mongo.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ComponentScan(basePackages = "com.springweb.dv_spring_web_mongo")
public class Config {

    @Bean
    public MongoClient mongoClient(){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://alex:Alex_coder152@cluster0.wdxsl.mongodb.net/myFirstDatabase?retryWrites=true\"");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(),"dv_spring_web_mongo");
    }
}
