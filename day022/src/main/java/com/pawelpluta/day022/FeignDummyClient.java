package com.pawelpluta.day022;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dummyClient", url = "${dummyClient.api.url}", configuration = DummyFeignConfig.class)
interface FeignDummyClient {
    @GetMapping("${dummyClient.api.path}")
    SampleClient.ResourceResponse getById(@PathVariable("resourceId") Integer resourceId);
}
