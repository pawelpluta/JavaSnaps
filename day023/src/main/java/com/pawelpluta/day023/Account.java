package com.pawelpluta.day023;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record Account(AccountId id, BigDecimal balance, List<String> consumedEventIds) {

    Account accept(RechargeAccountCommand command) {
        if (consumedEventIds.contains(command.commandId())) {
            return this;
        }
        List<String> updatedEventIds = new ArrayList<>(consumedEventIds);
        updatedEventIds.add(command.commandId());
        return new Account(id, balance.add(command.amount()), updatedEventIds);
    }

}
