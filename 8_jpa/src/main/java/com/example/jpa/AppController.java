package com.example.jpa;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }


}
