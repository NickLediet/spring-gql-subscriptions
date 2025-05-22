package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class GraphQlConfig {
    
    /**
     * Configure the GraphQL runtime wiring to ensure our subscription is properly registered
     */
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> {
            log.info("Configuring GraphQL runtime wiring");
            wiringBuilder.type("Subscription", typeWiring -> {
                log.info("Registering subscription field: counter");
                return typeWiring.dataFetcher("counter", env -> {
                    log.info("Subscription data fetcher called for counter");
                    // This is a marker - the actual subscription is handled by the CountSubscriptionResolver
                    // But this ensures it's properly connected in the schema
                    return null;
                });
            });
        };
    }
} 