package com.pawelpluta.day019;

import java.time.LocalDate;

class Mondays {

    private final Long amount;

    private Mondays(Long amount) {
        this.amount = amount;
    }

    static Mondays between(LocalDate one, LocalDate two) {
        return null;
    }

    Long amount() {
        return amount;
    }
}
