package com.tradeport.dbwriterservice.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tradeport.dbwriterservice")
public class DatabaseWriterService {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseWriterService.class, args);
        System.out.println("Database Service is running on port 9099...");
    }
}
