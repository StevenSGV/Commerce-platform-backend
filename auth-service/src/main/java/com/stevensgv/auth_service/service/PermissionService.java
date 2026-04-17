package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.Permission;
import com.stevensgv.auth_service.repository.IPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {

    private final IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return List.of();
    }

    @Override
    public Optional<Permission> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Permission save(Permission permission) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Permission update(Permission permission) {
        return null;
    }
}
