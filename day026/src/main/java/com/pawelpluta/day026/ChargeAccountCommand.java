package com.pawelpluta.day026;

import java.math.BigDecimal;

record ChargeAccountCommand(String accountId, BigDecimal amount) {
}
