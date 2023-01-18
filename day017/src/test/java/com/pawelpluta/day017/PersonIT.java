package com.pawelpluta.day017;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonIT {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    InMemoryPersonRepository personRepository;

    @Test
    public void shouldProvidePersonDataThroughRestApi() {
        // given
        String pesel = RandomStringUtils.randomNumeric(11);
        String firstName = "firstName_" + RandomStringUtils.randomAlphabetic(10);
        String lastName = "lastName_" + RandomStringUtils.randomAlphabetic(10);
        thereIsPersonInMongo(pesel, firstName, lastName);

        // when:
        String body = client.toBlocking().retrieve(HttpRequest.GET("/persons/" + pesel));

        // then
        assertNotNull(body);
        assertTrue(body.contains("\"pesel\":\"" + pesel + "\""));
        assertTrue(body.contains("\"firstName\":\"" + firstName + "\""));
        assertTrue(body.contains("\"lastName\":\"" + lastName + "\""));
    }

    void thereIsPersonInMongo(String pesel, String firstName, String lastName) {
        personRepository.save(new Person(pesel, firstName, lastName));
    }

}
