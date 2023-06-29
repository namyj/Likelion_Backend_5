package com.example.contents;

import com.example.contents.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseDto> handleIllegalState(IllegalStateException exception) {
        ResponseDto response = new ResponseDto();
        response.setMessage("UserControllerAdvice에서 처리한 예외입니다.");

        return  ResponseEntity.badRequest().body(response);
    }

}
