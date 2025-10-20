package com.tradeport.dbwriterservice.config; // Create a config package for this

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Configure Authorization for all incoming requests
                .authorizeHttpRequests((requests) -> requests
                        // Permit unauthenticated access to the health and readiness probes
                        .requestMatchers("/health", "/ready").permitAll()

                        // Require authentication for all other endpoints
                        .anyRequest().authenticated()
                )

                // 2. Disable default generated security
                // If you still need basic login for other endpoints, keep this.
                // If you rely on OAuth2/JWT for other services, you might disable formLogin().
                // For now, let's keep the default behavior for other endpoints if they exist.

                // 3. Essential: Disable CSRF protection for API services
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }
}