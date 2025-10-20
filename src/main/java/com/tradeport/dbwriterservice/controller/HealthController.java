package com.tradeport.dbwriterservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/ready")
    public String readinessCheck() { 
        // You can add logic here to check DB connections, caches, etc.  
        return "READY";
    }
}
