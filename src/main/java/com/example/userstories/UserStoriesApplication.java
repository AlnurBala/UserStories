package com.example.userstories;

import static com.example.userstories.enumeration.Role.ADMIN;

import com.example.userstories.config.MailProperties;
import com.example.userstories.dto.request.RegisterRequest;
import com.example.userstories.service.impl.AuthenticationServiceImpl;
import java.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(MailProperties.class)
public class UserStoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserStoriesApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationServiceImpl service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .username("Admin")
                    .emailAddress("admin@mail.com")
                    .password("password")
                    .roles(Collections.singleton(ADMIN))
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());
        };
    }
}
