package com.stevensgv.auth_service.dto;

import java.util.Set;

public record UserDTO(
        String email,
        String password,
        boolean enabled,
        Set<String> roles,
        Set<RoleDTO> permissions
) {
}
