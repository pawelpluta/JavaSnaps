package com.pawelpluta.day014;

import java.math.BigDecimal;
import java.util.Currency;

record Money(BigDecimal amount, Currency currency) {
}
