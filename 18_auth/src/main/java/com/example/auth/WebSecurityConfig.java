package com.example.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1. requsetMatchers를 통해 설정할 URL 지정
        // 2. permitAll(), authenticated() 등을 통해 어떤 사용자가접근 가능한지 설정
        http.authorizeHttpRequests(
                authHttp -> authHttp
                        .requestMatchers("/no-auth").permitAll()
                        .requestMatchers("/re-auth").authenticated() // 인증이 된 사용자만 허가
        );

        return http.build();
    }
}
