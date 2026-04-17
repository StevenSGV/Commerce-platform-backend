package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    List<Permission> findAll();

    Optional<Permission> findById(long id);

    Permission save(Permission permission);

    void deleteById(long id);

    Permission update(Permission permission);
}
