package com.pawelpluta.day026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class TransactionalOutboxApp {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalOutboxApp.class, args);
    }
}
