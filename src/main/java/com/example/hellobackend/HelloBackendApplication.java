package com.example.hellobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity

public class HelloBackendApplication {

    public static void main(String[] args) {
        System.out.println("spring.datasource.url = " + System.getenv("SPRING_DATASOURCE_URL"));
        SpringApplication.run(HelloBackendApplication.class, args);
    }
}
