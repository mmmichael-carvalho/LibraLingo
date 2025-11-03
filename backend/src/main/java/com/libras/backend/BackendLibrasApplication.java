package com.libras.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.libras.backend.repository")

@SpringBootApplication
public class BackendLibrasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLibrasApplication.class, args);
    }
}