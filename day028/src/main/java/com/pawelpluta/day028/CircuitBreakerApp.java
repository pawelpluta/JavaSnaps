package com.pawelpluta.day028;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
class CircuitBreakerApp {

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApp.class, args);
    }
}
