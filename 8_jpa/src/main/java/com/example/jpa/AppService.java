package com.example.jpa;

import org.springframework.stereotype.Service;

@Service
public class AppService {
    private final AppRepository repository;

    public AppService(AppRepository repository) {
        this.repository = repository;
    }

}
