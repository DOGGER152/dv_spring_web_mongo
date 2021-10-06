package com.springweb.dv_spring_web_mongo.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@PropertySources({@PropertySource(value = "classpath:application1.properties")
        , @PropertySource(value = "classpath:custom.application.properties", ignoreResourceNotFound = true)
})
@EnableAspectJAutoProxy
@Configuration
@EnableMongoRepositories(basePackages = "com.springweb.dv_spring_web_mongo.repository")
public class Config {

    @Value("${mongo.connectionString}")
    private String connectionString;

    @Value("${mongo.DBName}")
    private String dbName;

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), this.dbName);
    }

}
