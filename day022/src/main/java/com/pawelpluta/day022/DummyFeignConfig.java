package com.pawelpluta.day022;

import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyFeignConfig {

    @Bean
    Retryer retryer() {
        return new Retryer.Default(100, 500, 2);
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            Exception exception = new ErrorDecoder.Default().decode(methodKey, response);

            if (exception instanceof RetryableException) {
                return exception;
            }

            if (response.status() >= 500) {
                return new RetryableException(
                        response.status(),
                        response.reason(),
                        response.request().httpMethod(),
                        null,
                        response.request()
                );
            }

            return exception;
        };
    }

}
