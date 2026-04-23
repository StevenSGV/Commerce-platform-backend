package com.stevensgv.user_service.mapper;

import com.stevensgv.user_service.dto.UserAuthDTO;
import com.stevensgv.user_service.model.Permission;
import com.stevensgv.user_service.model.Role;
import com.stevensgv.user_service.model.UserSec;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserAuthDTO toAuthDTO(UserSec user) {
        return new UserAuthDTO(
                user.getEmail(),
                user.getPassword(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()),
                user.getRoles().stream()
                        .flatMap(role -> role.getPermissions().stream())
                        .map(Permission::getName)
                        .collect(Collectors.toSet())
        );
    }
}
