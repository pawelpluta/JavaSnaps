package com.pawelpluta.day003;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DummyCalculatorTest {

    private DummyCalculator calculator = new DummyCalculator();

    @ParameterizedTest(name = "Case {index}: {0} + {1} = {2}")
    @MethodSource
    void sumIsProperlyCalculated(BigDecimal addend1, BigDecimal addend2, BigDecimal expectedSum) {
        assertEquals(expectedSum, calculator.sum(addend1, addend2));
    }

    static Stream<Arguments> sumIsProperlyCalculated() {
        return Stream.of(
                Arguments.of(valueOf(3), valueOf(2), valueOf(5)),
                Arguments.of(valueOf(-3), valueOf(2), valueOf(-1)),
                Arguments.of(valueOf(-3), valueOf(-2), valueOf(-5)));
    }

    @ParameterizedTest(name = "Case {index}: {0} + {1}}")
    @MethodSource
    void invalidInputCannotBeAdded(BigDecimal addend1, BigDecimal addend2) {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sum(addend1, addend2));
    }

    static Stream<Arguments> invalidInputCannotBeAdded() {
        return Stream.of(
                Arguments.of(null, ONE),
                Arguments.of(ONE, null),
                Arguments.of(null, null));
    }

}
