package com.example.aop.controller;

import com.example.aop.aspect.LogArguments;
import com.example.aop.aspect.LogExecutionTime;
import com.example.aop.dto.ResponseDto;
import com.example.aop.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {
    @PostMapping("/users")
    @LogArguments
    @LogExecutionTime
    public ResponseDto addUser(@RequestBody UserDto userDto) {
        log.info("AopController.addUser call");

        ResponseDto response = new ResponseDto();
        response.setMessage("addUser");
        return response;
    }

    @GetMapping("/users")
    @LogExecutionTime
    public ResponseDto getUsers() {
        long start = System.currentTimeMillis();

        try {
            ResponseDto response = new ResponseDto();
            response.setMessage("addUser");
            return response;
        } finally {
            log.info("elapsed: {}", System.currentTimeMillis() - start);
        }
    }
}
