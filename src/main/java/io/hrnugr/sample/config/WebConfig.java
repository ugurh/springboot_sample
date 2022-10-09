package io.hrnugr.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author harun ugur
 */
@Configuration
public class WebConfig {

    // TODO Setting CORS Configurations
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://localhost:8080")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }
}
