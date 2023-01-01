package com.pawelpluta.day001;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

class TimeFixture {
    static Instant today() {
        return Instant.now().truncatedTo(ChronoUnit.DAYS);
    }
    static Instant tomorrow() {
        return today().plus(Duration.ofDays(1));
    }
    static Instant yesterday() {
        return today().minus(Duration.ofDays(1));
    }

    static Instant twoDaysFromNow() {
        return today().plus(Duration.ofDays(2));
    }
}
