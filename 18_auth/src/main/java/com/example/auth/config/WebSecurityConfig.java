package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1. requsetMatchers를 통해 설정할 URL 지정
        // 2. permitAll(), authenticated() 등을 통해 어떤 사용자가접근 가능한지 설정
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                authHttp -> authHttp
                                .requestMatchers("/no-auth").permitAll() // 모든 사용자 허가
                                .requestMatchers("/re-auth", "/users/my-profile").authenticated() // 인증이 된 사용자만 허가
                                .requestMatchers("/", "/users/register").anonymous()
        );

        http.formLogin(
                formLogin -> formLogin
                        .loginPage("/users/login") // 로그인 경로
                        .defaultSuccessUrl("/users/my-profile")
                        .failureUrl("/users/login?fail")
                        .permitAll()
        );

        http.logout(
                logout -> logout
                        .logoutUrl("/users/logout") // 로그아웃 경로
                        .logoutSuccessUrl("/users/login")
        );

        return http.build();
    }

    // @Bean
    // // 사용자 관리 인터페이스 구현체
    // public UserDetailsManager getUserDetailsManager(PasswordEncoder passwordEncoder) {
    //     // 임시 유저 생성
    //     UserDetails user1 = User.withUsername("user1")
    //             .password(passwordEncoder.encode("password"))
    //             .build();
    //
    //     // 메모리 상에 유저 정보 저장
    //     return new InMemoryUserDetailsManager(user1);
    // }

    @Bean
    // 비밀번호 암호화 인코더
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
