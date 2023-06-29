package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    // createUser
    public UserDto createUser(UserDto dto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // readUserByUsername
    public UserDto readUserByUsername(String username) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // updateUser
    public UserDto updateUser(Long id, UserDto dto) {

        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // updateUserAvatar
    public UserDto updateUserAvatar(Long id, MultipartFile avatarImage) {

        // 1. 사용자 확인
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 2-1. 저장 폴더 생성
        String profileDir = String.format("media/%d/", id);
        log.info("profileDir = " + profileDir);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 2-2. 확장자를 포함한 저장 파일명 생성
        String originalFilename = avatarImage.getOriginalFilename();
        String[] filenameSplit = originalFilename.split("\\."); // . 은 regrex에서 한 문자를 의미하기 때문에 \\ 필요
        String extension = filenameSplit[filenameSplit.length -1];
        String profileFilename = "profile." + extension;

        // 2-3. 저장 폴더와 경로를 포함한 전체 파일명 생성
        String profilePath = profileDir + profileFilename;

        // 3. 파일 저장
        try {
            avatarImage.transferTo(Path.of(profilePath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
