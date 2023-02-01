package com.pawelpluta.day030;

import java.time.Instant;

record LibraryMemberRegistered(
        String memberId,
        Instant occurrenceTime,
        String firstName,
        String lastName,
        Integer yearOfBirth) implements Event {
    @Override
    public String aggregateId() {
        return memberId;
    }

}
