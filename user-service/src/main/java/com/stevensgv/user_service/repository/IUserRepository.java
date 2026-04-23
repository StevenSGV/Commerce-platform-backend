package com.stevensgv.user_service.repository;

import com.stevensgv.user_service.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {

    Optional<UserSec> findUserEntityByEmail(String email);
}
