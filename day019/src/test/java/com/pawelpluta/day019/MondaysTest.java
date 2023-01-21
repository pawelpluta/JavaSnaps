package com.pawelpluta.day019;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MondaysTest {

    @Test
    void regularYearHas52Mondays() {
        // given
        LocalDate firstDate = LocalDate.of(2023, 1, 1);
        LocalDate secondDate = LocalDate.of(2023, 12, 31);

        // expect
        assertEquals(52L, Mondays.between(firstDate, secondDate).amount());
    }

    @Test
    void yearThatStartsOnMondayHas53Mondays() {
        // given
        LocalDate firstDate = LocalDate.of(2007, 1, 1);
        LocalDate secondDate = LocalDate.of(2007, 12, 31);

        // expect
        assertEquals(53L, Mondays.between(firstDate, secondDate).amount());
    }

    @Test
    void leapYearThatStartsOnSundayHas53Mondays() {
        // given
        LocalDate firstDate = LocalDate.of(2040, 1, 1);
        LocalDate secondDate = LocalDate.of(2040, 12, 31);

        // expect
        assertEquals(53L, Mondays.between(firstDate, secondDate).amount());
    }

    @Test
    void dateRangeWithoutMondaysHasZeroMondays() {
        // given
        LocalDate firstDate = LocalDate.of(2023, 1, 18);
        LocalDate secondDate = LocalDate.of(2023, 1, 20);

        // expect
        assertEquals(0L, Mondays.between(firstDate, secondDate).amount());
    }

    @Test
    void dateRangeWithOnlyMondayHasOneMonday() {
        // given
        LocalDate firstDate = LocalDate.of(2023, 1, 23);
        LocalDate secondDate = LocalDate.of(2023, 1, 23);

        // expect
        assertEquals(1L, Mondays.between(firstDate, secondDate).amount());
    }

    @Test
    void datesAreReversible() {
        // given
        LocalDate firstDate = LocalDate.of(2023, 2, 1);
        LocalDate secondDate = LocalDate.of(2023, 1, 1);

        // expect
        assertEquals(5L, Mondays.between(firstDate, secondDate).amount());
    }
}