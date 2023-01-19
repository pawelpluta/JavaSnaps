package com.pawelpluta.day018;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class BookControllerIT {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void existingBookShouldBeReturnedWithOK() {
        // given
        String bookId = "existingBook";

        // when
        HttpResponse response = client.toBlocking().exchange(HttpRequest.GET("/books/" + bookId));

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void notExistingBookShouldBeReturnedWithNotFound() {
        // given
        String bookId = "notExistingBookId";

        // when
        HttpClientResponseException responseError = assertThrows(
                HttpClientResponseException.class,
                () -> client.toBlocking().exchange(HttpRequest.GET("/books/" + bookId)));

        // then
        assertEquals(HttpStatus.NOT_FOUND, responseError.getStatus());
    }

    @Test
    public void brewingCoffeeWithBookShouldReturnTeaPot() {
        // given
        String bookId = "brewCoffee";

        // when:
        HttpClientResponseException responseError = assertThrows(
                HttpClientResponseException.class,
                () -> client.toBlocking().exchange(HttpRequest.GET("/books/" + bookId)));

        // then
        assertEquals(HttpStatus.I_AM_A_TEAPOT, responseError.getStatus());
    }

}