package com.pawelpluta.day024;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "evenLongerResponseClient", url = "${evenLongerResponseClient.api.url}")
interface FeignEvenLongerResponseClient {
    @GetMapping("${evenLongerResponseClient.api.path}")
    EventLongerResponse get();

    record EventLongerResponse(String evenLongerResponseField) {}
}
