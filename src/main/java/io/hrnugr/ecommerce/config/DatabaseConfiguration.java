package io.hrnugr.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author harun ugur
 */
@Configuration
@EnableJpaAuditing
public class DatabaseConfiguration {

    /**
     * @return a simple username as auditor
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("Administrator");
    }

}