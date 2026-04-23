package com.stevensgv.user_service.service;

import com.stevensgv.user_service.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserSec> findAll();

    Optional<UserSec> findById(long id);

    Optional<UserSec> findByEmail(String email);

    UserSec save(UserSec user);

    void deleteById(long id);

    UserSec update(UserSec userSec);

    String encryptPassword(String password);
}
