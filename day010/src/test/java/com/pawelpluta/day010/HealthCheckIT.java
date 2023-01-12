package com.pawelpluta.day010;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckIT extends RestAssuredSupport {

    @Test
    void healthCheckIsExposed() {
            RestAssured.given().auth().none()
                    .when().get("/actuator/health")
                    .then().statusCode(200);
    }

    @Test
    void applicationReadinessCheckIsExposed() {
            RestAssured.given().auth().none()
                    .when().get("/actuator/health/readiness")
                    .then().statusCode(200);
    }
    @Test
    void applicationLivenessCheckIsExposed() {
            RestAssured.given().auth().none()
                    .when().get("/actuator/health/liveness")
                    .then().statusCode(200);
    }
}
