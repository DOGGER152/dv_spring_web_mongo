package com.springweb.dv_spring_web_mongo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        File mentor = new File("src/main/resources/mentor_application.properties");
        if (mentor.exists()) {
            SpringApplication.run(Application.class,
                    new String[]{"--spring.config.location=classpath:/mentor_application.properties"});
        } else {
            SpringApplication.run(Application.class);
        }

    }
}

