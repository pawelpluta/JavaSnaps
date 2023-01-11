package com.pawelpluta.day010;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KubernetesHealthcheckApp {

    public static void main(String[] args) {
        SpringApplication.run(KubernetesHealthcheckApp.class, args);
    }

}
