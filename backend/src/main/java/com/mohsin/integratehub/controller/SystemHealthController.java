package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.SystemHealthResponse;
import com.mohsin.integratehub.service.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemHealthController {

    private final HealthService healthService;

    public SystemHealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/api/system-health")
    public ResponseEntity<SystemHealthResponse> getHealth() {
        return ResponseEntity.ok(healthService.getSystemHealth());
    }
}
