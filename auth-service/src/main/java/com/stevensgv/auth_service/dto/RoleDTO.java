package com.stevensgv.auth_service.dto;

import java.util.Set;

public record RoleDTO(
        String name,
        Set<String> permissions
) {
}
