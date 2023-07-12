package com.example.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public String root() {
        return "hello";
    }

    // no-auth : 누구나 접근 가능하도록
    @GetMapping("/no-auth")
    public String noAuth() {
        return "no auth success!";
    }

    // re-auth : 인증된 사용자만 접근 가능하도록
    @GetMapping("/re-auth")
    public String reAuth() {
        return "re auth success!";
    }
}
