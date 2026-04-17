package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<Role> findAll();

    Optional<Role> findById(long id);

    Role save(Role role);

    void deleteById(long id);

    Role update(Long id, Role role);
}
