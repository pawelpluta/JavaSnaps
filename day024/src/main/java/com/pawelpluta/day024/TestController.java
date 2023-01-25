package com.pawelpluta.day024;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependentEndpoint")
class TestController {

    private final FeignLongResponseClient longResponseClient;
    private final FeignEvenLongerResponseClient evenLongerResponseClient;

    TestController(FeignLongResponseClient longResponseClient, FeignEvenLongerResponseClient evenLongerResponseClient) {
        this.longResponseClient = longResponseClient;
        this.evenLongerResponseClient = evenLongerResponseClient;
    }

    @GetMapping
    TestResponse execute() {
        return new TestResponse(
                longResponseClient.get().longResponseField(),
                evenLongerResponseClient.get().evenLongerResponseField());
    }

    record TestResponse(String longResponseField, String evenLongerResponseField) {
    }

}
