package com.pawelpluta.day028;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sampleClient", url = "${sampleClient.api.url}")
interface SampleRestClient {
    @GetMapping("/{id}")
    String getById(@PathVariable("id") String id);

}
