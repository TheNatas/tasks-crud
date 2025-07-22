package com.example.hellobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.net.URI;

@SpringBootApplication
@EnableMethodSecurity
public class HelloBackendApplication {

    public static void main(String[] args) {
        String databaseUrl = System.getenv("DATABASE_URL");
        System.out.println("DATABASE_URL = " + databaseUrl);

        if (databaseUrl != null) {
            try {
                URI dbUri = new URI(databaseUrl);
                String[] userInfo = dbUri.getUserInfo().split(":");
                String username = userInfo[0];
                String password = userInfo[1];
                String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

                System.setProperty("spring.datasource.url", jdbcUrl);
                System.setProperty("spring.datasource.username", username);
                System.setProperty("spring.datasource.password", password);

                System.out.println("✅ JDBC URL configured: " + jdbcUrl);
            } catch (Exception e) {
                System.err.println("❌ Failed to parse DATABASE_URL: " + e.getMessage());
            }
        } else {
            System.err.println("❌ DATABASE_URL is not set");
        }

        SpringApplication.run(HelloBackendApplication.class, args);
    }
}
