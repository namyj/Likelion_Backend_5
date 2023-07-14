package com.example.auth;

import com.example.auth.jwt.JwtRequestDto;
import com.example.auth.jwt.JwtTokenDto;
import com.example.auth.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("token")  // http://localhost:8080/token/** 부터 시작하는 요청들
public class TokenController {
    // UserDetailsManager: 사용자 정보 회수
    // PasswordEncoder: 비밀번호 대조용 인코더

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    public TokenController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, JwtTokenUtils jwtTokenUtils) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
    }


    // /token/issue : JWT 발급용 엔드포인트
    @PostMapping("/issue")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {

        // 사용자 정보 회수
        UserDetails userDetails = userDetailsManager.loadUserByUsername(dto.getUsername());
        // 기록된 비밀번호와 실제 비밀번호가 일치하는지
        if (!passwordEncoder.encode(dto.getPassword()).equals(userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); // 인증 실패

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }
}
