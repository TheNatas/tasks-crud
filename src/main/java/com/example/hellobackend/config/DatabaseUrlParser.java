package com.example.hellobackend.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.URI;

@Configuration
public class DatabaseUrlParser {

    @Value("${custom.database-url:}")
    private String databaseUrl;

    @PostConstruct
    public void init() {
        System.out.println("DATABASE_URL from env: " + databaseUrl);
        if (!StringUtils.hasText(databaseUrl)) {
            System.err.println("DATABASE_URL is not set!");
            return;
        }

        try {
            URI uri = new URI(databaseUrl);

            String username = uri.getUserInfo().split(":")[0];
            String password = uri.getUserInfo().split(":")[1];
            String url = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();

            System.setProperty("spring.datasource.url", url);
            System.setProperty("spring.datasource.username", username);
            System.setProperty("spring.datasource.password", password);

            System.out.println("✅ JDBC URL set to: " + url);
        } catch (Exception e) {
            System.err.println("Failed to parse DATABASE_URL: " + e.getMessage());
        }
    }
}
