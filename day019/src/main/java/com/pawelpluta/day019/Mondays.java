package com.pawelpluta.day019;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

class Mondays {

    private final Long amount;

    private Mondays(Long amount) {
        this.amount = amount;
    }

    static Mondays between(LocalDate one, LocalDate two) {
        if (one.isAfter(two)) {
            return new Mondays(count(two, one));
        }
        return new Mondays(count(one, two));
    }

    private static Long count(LocalDate earlier, LocalDate later) {
        LocalDate firstMonday = earlier.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        LocalDate lastMonday = later.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return ChronoUnit.WEEKS.between(firstMonday, lastMonday);
    }

    Long amount() {
        return amount;
    }
}
