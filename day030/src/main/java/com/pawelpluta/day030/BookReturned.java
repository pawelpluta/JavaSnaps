package com.pawelpluta.day030;

import java.time.Instant;

record BookReturned(String memberId, Instant occurrenceTime, String bookId) implements Event {
    @Override
    public String aggregateId() {
        return memberId;
    }
}
