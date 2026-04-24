package com.stevensgv.user_service.mapper;

import com.stevensgv.user_service.dto.UserAuthDTO;
import com.stevensgv.user_service.model.Permission;
import com.stevensgv.user_service.model.Role;
import com.stevensgv.user_service.model.UserSec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    @Mapping(target = "permissions", source = "roles", qualifiedByName = "mapPermissions")
    UserAuthDTO toUserAuthDTO (UserSec userSec);

    @Named("mapRoles")
    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Named("mapPermissions")
    default Set<String> mapPermissions(Set<Role> roles) {
        return roles.stream()
                .flatMap(role ->
                        role.getPermissions().stream())
                .map(Permission::getName)
                .collect(Collectors.toSet());
    }
}
