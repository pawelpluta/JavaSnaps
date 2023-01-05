package com.pawelpluta.day003;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParameterizedTest {

    private DummyCalculator calculator = new DummyCalculator();

    @Test
    void twoPositiveNumbersCanBeAdded() {
        // expect
        assertEquals(valueOf(5), calculator.sum(valueOf(3), valueOf(2)));
    }

    @Test
    void negativeAndPositiveNumbersCanBeAdded() {
        // expect
        assertEquals(valueOf(-1), calculator.sum(valueOf(-3), valueOf(2)));
    }

    @Test
    void twoNegativeNumbersCanBeAdded() {
        // expect
        assertEquals(valueOf(-5), calculator.sum(valueOf(-3), valueOf(-2)));
    }

    @Test
    void nullAsFirstNumberIsUnsupported() {
        // expect
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sum(null, ONE));
    }

    @Test
    void nullAsSecondNumberIsUnsupported() {
        // expect
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sum(ONE, null));
    }

    @Test
    void nullAsBothNumbersIsUnsupported() {
        // expect
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sum(null, null));
    }
}
