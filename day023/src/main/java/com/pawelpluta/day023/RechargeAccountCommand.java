package com.pawelpluta.day023;

import java.math.BigDecimal;

record RechargeAccountCommand(String commandId, String accountId, BigDecimal amount) {
}
