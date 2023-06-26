package com.example.validation;

import com.example.validation.dto.UserDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @PostMapping("/users")
    public ResponseEntity<Map<String, String>> adduser(
        @Valid @RequestBody UserDto userDto
    ) {
        log.info(userDto.toString());
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "success");

        return ResponseEntity.ok(responseBody);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> hadnleValidationException (
        MethodArgumentNotValidException exception
    ) {
        // 유효성 검사 실패에 대한 정보를 담을 errors 해시 맵 생성
        Map<String, String> errors = new HashMap<>();
        
        // 유효성 검사를 실패한 필드 이름, 오류 메시지를 errors에 저장
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }
}
