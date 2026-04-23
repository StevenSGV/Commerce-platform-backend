package com.stevensgv.user_service.service;

import com.stevensgv.user_service.model.Permission;
import com.stevensgv.user_service.repository.IPermissionRepository;
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
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission update(Permission permission) {
        if (permission.getId() == null || !permissionRepository.existsById(permission.getId())) {
            throw new ResourceNotFoundException("Permission with id " + permission.getId() + " does not found");
        }

        return permissionRepository.save(permission);
    }
}
