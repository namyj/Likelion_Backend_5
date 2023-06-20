package com.example.jpa;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class AppConponent {
    private final AppConfigData configData;
    private final Gson gson;

    public AppConponent(AppConfigData configData, Gson gson) {
        this.configData = configData;
        this.gson = gson;
    }
    
    // 외부 API를 사용하는 메소드
    public void useApi() {
        // send request
    }
}
