package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.Role;
import com.stevensgv.auth_service.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public Optional<Role> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Role update(Long id, Role role) {
        return null;
    }
}
