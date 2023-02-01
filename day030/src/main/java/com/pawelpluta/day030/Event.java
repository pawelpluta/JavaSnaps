package com.pawelpluta.day030;

import java.time.Instant;

interface Event {
    String aggregateId();
    Instant occurrenceTime();
}
