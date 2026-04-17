package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserSec> findAll();

    Optional<UserSec> findById(long id);

    Optional<UserSec> findByUsername(String username);

    UserSec save(UserSec user);

    void deleteById(long id);

    UserSec update(UserSec userSec);

    String encryptPassword(String password);
}
