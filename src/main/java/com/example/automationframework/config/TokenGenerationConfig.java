package com.example.automationframework.config;

import com.example.automationframework.annotations.LazyConfiguration;
import com.example.automationframework.apiclients.core.TokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class TokenGenerationConfig {

    @Bean
    public TokenGenerator tokenGenerator(
            @Value("${application.url}") String baseAuthUrl,
            @Value("${application.admin.user}") String username,
            @Value("${application.admin.password}") String password
    ) {
        return new TokenGenerator(baseAuthUrl, username, password);
    }
}
