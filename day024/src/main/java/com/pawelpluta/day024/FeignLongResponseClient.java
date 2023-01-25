package com.pawelpluta.day024;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "longResponseClient", url = "${longResponseClient.api.url}")
interface FeignLongResponseClient {
    @GetMapping("${longResponseClient.api.path}")
    LongResponse get();

    record LongResponse(String longResponseField) {}
}
