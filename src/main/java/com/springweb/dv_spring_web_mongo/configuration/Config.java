package com.springweb.dv_spring_web_mongo.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
@PropertySource("classpath:application.properties")
@Configuration
public class Config {

@Value("${mongo.connectionString}")
private String connectionString;

@Value("${mongo.DBName}")
private String dbName;

    @Bean
    public MongoClient mongoClient(){
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(),this.dbName);
    }
}
