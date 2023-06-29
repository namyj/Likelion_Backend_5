package com.example.contents;

import com.example.contents.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    
    // 사용자 이름이 이미 존재하는지 여부
    Boolean existsByUsername(String username);
}
