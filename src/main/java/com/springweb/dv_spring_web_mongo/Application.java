package com.springweb.dv_spring_web_mongo;


import com.springweb.dv_spring_web_mongo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public Application(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
//        Application application = new Application(null, null);
//        application.createDefaultUsers();
    }

//    private void createDefaultUsers() {
//
//        Optional<User> optionalUser = userRepository.findOne(
//                Example.of(new User("", "user", "", null)));
//        if (optionalUser.isEmpty()) {
//            userRepository.save(new User("", "user", "user", null));
//        }
//
//        Optional<User> optionalAdmin = userRepository.findOne(
//                Example.of(new User("", "admin", "", null)));
//        if (optionalAdmin.isEmpty()) {
//            userRepository.save(new User("", "admin", "admin", null));
//        }
//    }
}

