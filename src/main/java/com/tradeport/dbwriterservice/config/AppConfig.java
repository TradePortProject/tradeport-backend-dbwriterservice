package com.tradeport.dbwriterservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.security.user.name}")
    private String securityUserName;

    @Value("${spring.security.user.password}")
    private String securityUserPassword;

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    // Getters
    public String getApplicationName() {
        return applicationName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getSecurityUserName() {
        return securityUserName;
    }

    public String getSecurityUserPassword() {
        return securityUserPassword;
    }

    public String getKafkaBootstrapServers() {
        return kafkaBootstrapServers;
    }
}
