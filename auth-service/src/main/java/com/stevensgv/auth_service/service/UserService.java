package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.UserSec;
import com.stevensgv.auth_service.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public List<UserSec> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserSec> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserSec> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public UserSec save(UserSec user) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public UserSec update(UserSec userSec) {
        return null;
    }

    @Override
    public String encryptPassword(String password) {
        return "";
    }
}
