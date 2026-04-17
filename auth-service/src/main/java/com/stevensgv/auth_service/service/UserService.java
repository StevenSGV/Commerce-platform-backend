package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.Role;
import com.stevensgv.auth_service.model.UserSec;
import com.stevensgv.auth_service.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleService roleService;

    @Override
    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserSec> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserSec> findByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public UserSec save(UserSec user) {
        user.setRoles(resolveRoles(user.getRoles()));

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserSec update(UserSec userSec) {
        UserSec userFound = this
                .findById(userSec.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("user with id " + userSec.getId() + " nof found"));

        userFound.setEmail(userSec.getEmail());
        userFound.setPassword(userSec.getPassword());
        userFound.setAccountNonExpired(userSec.isAccountNonExpired());
        userFound.setAccountNonLocked(userSec.isAccountNonLocked());
        userFound.setCredentialsNonExpired(userSec.isCredentialsNonExpired());
        userFound.setEnabled(userSec.isEnabled());
        userFound.setRoles(resolveRoles(userSec.getRoles()));

        return userRepository.save(userFound);
    }

    @Override
    public String encryptPassword(String password) {
        return "";
    }

    private Set<Role> resolveRoles(Set<Role> roles) {
        Set<Role> resolvedRoles = roles.stream()
                .map(role ->
                        roleService.findById(role.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (resolvedRoles.isEmpty()) {
            throw new ResourceNotFoundException("At least one valid role is required");
        }

        return resolvedRoles;
    }
}
