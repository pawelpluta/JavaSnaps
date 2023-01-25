package com.pawelpluta.day024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
class RestTemplateApp {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateApp.class);
    }
}
