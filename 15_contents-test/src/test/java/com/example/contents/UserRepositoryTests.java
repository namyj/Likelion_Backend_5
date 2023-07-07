package com.example.contents;

import com.example.contents.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired UserRepository userRepository;

    @Test
    public void testSaveNew() {
        // given
        UserEntity newUser = new UserEntity();
        newUser.setUsername("kim");

        // when
        UserEntity saveUser = userRepository.save(newUser);

        // then
        assertNotNull(saveUser.getId());
        assertEquals(newUser.getUsername(), saveUser.getUsername());
    }
    
    @Test
    @DisplayName("새 UserEntity를 데이터베이스에 추가 실패")
    public void testSaveNewFail() {
        // given
        UserEntity user1 = new UserEntity();
        user1.setUsername("kim");
        userRepository.save(user1);

        // when
        UserEntity user2 = new UserEntity();
        user2.setUsername("kim");

        // then
        assertThrows(Exception.class, () -> userRepository.save(user2));

    }

    @Test
    @DisplayName("username으로 UserEntity 찾기")
    public void testFindByUsername() {
        // gien
        UserEntity user = new UserEntity();
        user.setUsername("kim");
        userRepository.save(user);

        // when
        Optional<UserEntity> optionalUser = userRepository.findByUsername(user.getUsername());

        // then
        assertTrue(optionalUser.isPresent());
        assertEquals(user.getUsername(), optionalUser.get().getUsername());
    }
    

}
