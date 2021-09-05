package com.springweb.dv_spring_web_mongo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
//    final UserRepository userRepository;
//    final PasswordEncoder passwordEncoder;
//
//    public Application(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
//        Application application = new Application(null, null);
//        application.createDefaultUsers();
    }

//    private void createDefaultUsers() {
//
//        User user = new User("user"
//                ,passwordEncoder.encode("user")
//                ,Collections.singleton(new Role("ROLE_USER")));
//
//        User admin = new User("admin"
//                ,passwordEncoder.encode("admin")
//                ,Collections.singleton(new Role("ROLE_ADMIN")));
//
//        Optional<User> optionalUser = userRepository.findOne(Example.of(user));
//        if (optionalUser.isEmpty()) {
//            userRepository.save(user);
//        }
//
//        Optional<User> optionalAdmin = userRepository.findOne(
//                Example.of(admin));
//        if (optionalAdmin.isEmpty()) {
//            userRepository.save(admin);
//        }
//    }
}

