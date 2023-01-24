package com.pawelpluta.day023;

import java.math.BigDecimal;
import java.util.List;

public record Account(AccountId id, BigDecimal balance) {

    Account accept(RechargeAccountCommand command) {
        return null;
    }

}
