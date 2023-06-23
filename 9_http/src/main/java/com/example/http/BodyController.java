package com.example.http;

import com.example.http.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Writer;

@Slf4j
@Controller
public class BodyController {
    
    // request 요청을 받아서 ArticleDto에 넣음
    // ResponseDto 자바 객체를 생성 > response body에 넣어줌
    @PostMapping("/body")
    public @ResponseBody ResponseDto body(@RequestBody ArticleDto requestDto) {

        log.info("POST /body " + requestDto.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        return response;
    }

    @PostMapping("/body-2")
    public @ResponseBody ResponseDto body2(@RequestBody WriterDto requestDto) {

        log.info("POST /body-2 " + requestDto.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        return response;
    }

    @PostMapping("/body-3")
    public @ResponseBody ResponseDto body3(@RequestBody ArticleWithCommentsDto requestDto) {

        log.info("POST /body-3 " + requestDto.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        return response;
    }

    @PostMapping("/body-4")
    public @ResponseBody ResponseDto body4(@RequestBody ArticleComplexDto requestDto) {

        log.info("POST /body-4 " + requestDto.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        return response;
    }

    @PostMapping("/body-entity")
    public ResponseEntity<ResponseDto> entity (@RequestBody ArticleDto requestDto) {
        log.info("POST /body-entity " + requestDto.toString());

        // 응답 바디 설정
        ResponseDto response = new ResponseDto();
        response.setStatus(200); // 직접 status 라는 필드를 만든 것으로 헤더의 status code와 다르다
        response.setMessage("success");

        // 응답 헤더 설정 가능
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-likelion-custom", "Hello World"); // ("header name", "value")

        // ResponseEntity 객체
        ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<>(response, headers, HttpStatus.ACCEPTED );

        return responseEntity;
    }
}
