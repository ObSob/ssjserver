package com.shixun.ssjserver.dao;

import com.shixun.ssjserver.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
}
