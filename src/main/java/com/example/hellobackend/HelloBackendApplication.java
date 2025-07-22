package com.example.hellobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableMethodSecurity

public class HelloBackendApplication {

    public static void main(String[] args) {
        System.out.println("SPRING_DATASOURCE_URL = " + System.getenv("SPRING_DATASOURCE_URL"));
        SpringApplication.run(HelloBackendApplication.class, args);
    }

    @PostConstruct
    public void debug() {
        System.out.println("Resolved JDBC URL: " + System.getenv("SPRING_DATASOURCE_URL"));
    }

}
