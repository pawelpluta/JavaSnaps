package com.pawelpluta.day001;

import java.math.BigDecimal;

record Money(BigDecimal value) {
    boolean isHigherThan(Money anotherMoney) {
        return this.value.compareTo(anotherMoney.value) > 0;
    }
}
