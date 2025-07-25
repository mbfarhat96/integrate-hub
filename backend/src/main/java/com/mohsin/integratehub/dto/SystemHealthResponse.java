package com.mohsin.integratehub.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class SystemHealthResponse {

    private boolean applicationUp;
    private LocalDateTime timestamp;
    private Map<String, String> serviceStatuses;
    private String version;

    public SystemHealthResponse() {}

    public SystemHealthResponse(boolean applicationUp,
                                LocalDateTime timestamp,
                                Map<String, String> serviceStatuses,
                                String version) {
        this.applicationUp = applicationUp;
        this.timestamp = timestamp;
        this.serviceStatuses = serviceStatuses;
        this.version = version;
    }

    public boolean isApplicationUp() { return applicationUp; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Map<String, String> getServiceStatuses() { return serviceStatuses; }
    public String getVersion() { return version; }

    public void setApplicationUp(boolean applicationUp) { this.applicationUp = applicationUp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setServiceStatuses(Map<String, String> serviceStatuses) { this.serviceStatuses = serviceStatuses; }
    public void setVersion(String version) { this.version = version; }
}
