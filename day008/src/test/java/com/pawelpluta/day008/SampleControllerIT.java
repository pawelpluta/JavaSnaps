package com.pawelpluta.day008;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleControllerIT extends RestAssuredSupport {


    @Test
    void validRequestShouldBeAccepted() {
        // given
        RequestData validRequestBody = someValidRequestData();

        // when
        ValidatableResponse response = requestPerformedWith(validRequestBody);

        // then
        response.statusCode(HttpStatus.OK.value());
    }

    @Test
    void requestWithoutIntegerValueShouldBeRejected() {
        // given
        RequestData requestWithMissingData = someValidRequestData().withoutInteger();

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void requestWithoutDecimalValueShouldBeRejected() {
        // given
        RequestData requestWithMissingData = someValidRequestData().withoutDecimal();

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void requestWithoutTextValueShouldBeRejected() {
        // given
        RequestData requestWithMissingData = someValidRequestData().withoutText();

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    void requestWithNegativeIntegerValuesShouldBeRejected(Integer value) {
        // given
        RequestData requestWithMissingData = someValidRequestData().withInteger(value);

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.000001, Double.MAX_VALUE})
    void requestWithNonNegativeDecimalValuesShouldBeRejected(Double value) {
        // given
        RequestData requestWithMissingData = someValidRequestData().withDecimal(BigDecimal.valueOf(value));

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1","7AAAAAA","17AAAAAAAAAAAAAAA"})
    void requestWithTextShorterThan8OrLongerThan16ShouldBeRejected(String value) {
        // given
        RequestData requestWithMissingData = someValidRequestData().withText(value);

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void nestedStringIsRequiredWhenNestedFlagIsTrue() {
        // given
        RequestData requestWithMissingData = someValidRequestData().withMagicLogicFlag(true).withoutMagicLogicField();

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void nestedStringIsNotRequiredWhenNestedFlagIsFalse() {
        // given
        RequestData requestWithMissingData = someValidRequestData().withMagicLogicFlag(false).withoutMagicLogicField();

        // when
        ValidatableResponse response = requestPerformedWith(requestWithMissingData);

        // then
        response.statusCode(HttpStatus.OK.value());
    }

    private ValidatableResponse requestPerformedWith(RequestData validRequestBody) {
        return RestAssured
                .given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(validRequestBody.asJson())
                .when().post("/sample").then();
    }

    private RequestData someValidRequestData() {
        return new RequestData(10, BigDecimal.valueOf(-1), randomAlphabetic(12), true, randomAlphabetic(12));
    }


}