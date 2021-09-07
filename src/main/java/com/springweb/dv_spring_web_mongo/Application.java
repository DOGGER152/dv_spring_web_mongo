package com.springweb.dv_spring_web_mongo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        File mentor = new File("src/main/resources/custom.application.properties");
//        if (mentor.exists()) {
//            SpringApplication.run(Application.class,
//                    "--spring.config.location=classpath:/custom.application.properties");
//        } else {
//            SpringApplication.run(Application.class,
//                    "--spring.config.location=classpath:/application.properties");
//        }
        SpringApplication.run(Application.class);
    }
}

