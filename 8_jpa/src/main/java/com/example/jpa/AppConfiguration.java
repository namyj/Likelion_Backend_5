package com.example.jpa;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean // 메소드의 결과값을 Bean 객체로 등록
    public AppConfigData connectionUrl() {
        if (true) return new AppConfigData("main-url");
        else return new AppConfigData("backup-url");
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
