package com.pawelpluta.day024;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        CompletableFuture<FeignLongResponseClient.LongResponse> longResponse = getLongResponse();
        CompletableFuture<FeignEvenLongerResponseClient.EventLongerResponse> evenLongerResponse = getEvenLongerResponse();
        try {
            return new TestResponse(
                    longResponse.get().longResponseField(),
                    evenLongerResponse.get().evenLongerResponseField());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private CompletableFuture<FeignLongResponseClient.LongResponse> getLongResponse() {
        return CompletableFuture.supplyAsync(longResponseClient::get);
    }

    private CompletableFuture<FeignEvenLongerResponseClient.EventLongerResponse> getEvenLongerResponse() {
        return CompletableFuture.supplyAsync(evenLongerResponseClient::get);
    }

    record TestResponse(String longResponseField, String evenLongerResponseField){}

}
