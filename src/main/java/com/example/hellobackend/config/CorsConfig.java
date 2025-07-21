package com.example.hellobackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@org.springframework.lang.NonNull CorsRegistry registry) {
                registry.addMapping("/**") // allow all endpoints
                        .allowedOrigins(
                            "http://localhost:4200", 
                            "https://thenatas-projects.vercel.app",
                            "https://personal-finance-thenatas-projects.vercel.app",
                            "https://personal-finance-git-master-thenatas-projects.vercel.app",
                            "https://personal-finance-hwi9dv0cp-thenatas-projects.vercel.app",
                            "https://personal-finance-thenatas-projects.vercel.app/",
                            "https://personal-finance-ten-rho.vercel.app"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
