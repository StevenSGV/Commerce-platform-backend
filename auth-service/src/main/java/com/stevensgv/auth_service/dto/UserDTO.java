package com.stevensgv.auth_service.dto;

import java.util.Set;

public record UserDTO(
        String email,
        String password,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Set<String> roles,
        Set<String> permissions
) {
}
