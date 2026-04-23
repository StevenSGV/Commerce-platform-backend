package com.stevensgv.auth_service.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
