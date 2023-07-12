package com.example.auth.repository;


import com.example.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 사용자 계정이름으로 사용자 정보 찾기
    Optional<UserEntity> findByUsername(String username);
    // 사용자 계정이름으로 사용자 정보가 존재하는지 판단  
    Boolean existsByUsername(String username);
}
