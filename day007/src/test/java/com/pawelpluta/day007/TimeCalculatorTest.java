package com.pawelpluta.day007;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeCalculatorTest {

    private TimeCalculator timeCalculator = new TimeCalculator();

    @Test
    void minutesCanBeConvertedToSeconds() {
        // expect
        assertEquals(120L, timeCalculator.minutesToSeconds(2L));
    }

    @Test
    void secondsCanBeConvertedToHours() {
        // expect
        assertEquals(1L, timeCalculator.secondsToHours(3601L));
    }

    @Test
    void weeksCanBeConvertedToDays() {
        // expect
        assertEquals(14L, timeCalculator.weeksToDays(2L));
    }

    @Test
    void daysCanBeConvertedToWeeks() {
        // expect
        assertEquals(1L, timeCalculator.daysToWeeks(12L));
    }

    @Test
    void weeksCanBeConvertedToNanoseconds() {
        // expect
        assertEquals(604800L, timeCalculator.weeksToSeconds(1L));
    }
}
