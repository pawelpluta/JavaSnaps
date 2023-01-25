package com.pawelpluta.day024;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
class TestControllerIT {

    @Autowired
    private TestController controller;

    @Test
    @Timeout(value = 500, unit = MILLISECONDS)
    void shouldReturnResponseWithin500ms() {
        // when
        TestController.TestResponse response = controller.execute();

        // then
        assertEquals("that took 250 ms", response.longResponseField());
        assertEquals("that took 350 ms", response.evenLongerResponseField());
    }
}