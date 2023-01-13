package com.pawelpluta.day011;

import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckIT extends RestAssuredSupport {

    @Test
    void prometheusMetricsAreExposed() {
            RestAssured.given().auth().none()
                    .when().get("/actuator/prometheus")
                    .then().statusCode(200);
    }

    @Test
    void customGaugeMetricIsExposed() {
            RestAssured.given().auth().none()
                    .when().get("/actuator/prometheus")
                    .then().body(CoreMatchers.containsString("TYPE testCustomMetric gauge"))
                    .and().body(CoreMatchers.containsString("testCustomMetric 12.0"));
    }

}
