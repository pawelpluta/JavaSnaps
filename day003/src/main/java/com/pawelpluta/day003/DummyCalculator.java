package com.pawelpluta.day003;

import java.math.BigDecimal;

class DummyCalculator {

    BigDecimal sum(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        return first.add(second);
    }
}
