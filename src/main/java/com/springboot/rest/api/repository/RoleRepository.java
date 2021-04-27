package com.springboot.rest.api.repository;

import com.springboot.rest.api.entity.RoleEntity;
import com.springboot.rest.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findByRoleName(String roleName);

    List<RoleEntity> findByUsers(UserEntity userEntity);
}


