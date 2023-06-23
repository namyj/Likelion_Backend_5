package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.util.Map;

@Slf4j // 자동으로 logger 생성
@Controller
public class MappingController {

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public String getPath() {
        log.info("GET /path");
        return "index";
    }

    @RequestMapping(value = "/path", method = RequestMethod.POST)
    public String postPath() {
        log.info("POST /path");
        return "index";
    }

    @RequestMapping(value = "/path", method = {RequestMethod.PUT, RequestMethod.DELETE})
    public String putOrDeletePath() {
        log.info("PUT or DELETE /path");
        return "index";
    }

    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE // 수신하는 데이터 포맷 지정
    )
    public String comsumes() {
        log.info("POST /path Content-Type: application.json");
        return "index";
    }

    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            headers = "x-likelion=hello"
    )
    public String headerWith() {
        log.info("POST /path with x-likelion=hello");
        return "index";
    }

    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            params = "likelion=hello"
    )
    public String params() {
        log.info("POST /path with parameter likelion");
        return "index";
    }

    @PostMapping("/header-one")
    public String getHeader(@RequestHeader("x-likelion") String header) {
        log.info("POST /header-one header: " + header);
        return "index";
    }

    @PostMapping("/header-all")
    public String getHeaderAll(
            @RequestHeader
            Map<String, String> headerMap
    ) {
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            log.info(String.format("%s: %s", entry.getKey(), entry.getValue()));
        }

        return "index";
    }
}
