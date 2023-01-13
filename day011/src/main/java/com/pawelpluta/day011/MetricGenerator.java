package com.pawelpluta.day011;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
class MetricGenerator {

    private final MeterRegistry meterRegistry;

    MetricGenerator(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    void publicMetric() {
        meterRegistry.gauge("testCustomMetric", 12);
    }

}
