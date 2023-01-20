package com.pawelpluta.day018;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Produces
class CoffeeExceptionHandler implements ExceptionHandler<CoffeeBookException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, CoffeeBookException exception) {
        return HttpResponse.status(HttpStatus.I_AM_A_TEAPOT);
    }
}
