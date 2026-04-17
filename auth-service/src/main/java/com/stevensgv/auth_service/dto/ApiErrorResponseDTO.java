package com.stevensgv.auth_service.dto;

import java.time.Instant;

public record ApiErrorResponseDTO(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path) {
}
