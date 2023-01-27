package com.pawelpluta.day026;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private final String accountNumber;
    private final BigDecimal balance;
    private final List<Event> events;

    private BankAccount(String accountNumber, BigDecimal balance, List<Event> events) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.events = events;
    }

    public String accountNumber() {
        return accountNumber;
    }

    public BigDecimal balance() {
        return balance;
    }

    public List<Event> events() {
        return events;
    }

    public static BankAccount of(String accountNumber, BigDecimal balance) {
        return new BankAccount(accountNumber, balance, Collections.emptyList());
    }
    BankAccount handle(ChargeAccountCommand command) {
        if (balance.compareTo(command.amount()) >= 0) {
            BigDecimal updatedBalance = balance.subtract(command.amount());
            return new BankAccount(
                    accountNumber,
                    updatedBalance,
                    List.of(new BankAccountBalanceDecreased(accountNumber, balance, updatedBalance)));
        } else {
            return new BankAccount(
                    accountNumber,
                    balance,
                    List.of(new AccountChargeRejectedDueToInsufficientFounds(accountNumber, balance, command.amount())));
        }
    }

    static class BankAccountBalanceDecreased implements Event {

        private final String id;
        private final String accountNumber;
        private final BigDecimal oldBalance;
        private final BigDecimal newBalance;

        BankAccountBalanceDecreased(String accountNumber, BigDecimal oldBalance, BigDecimal newBalance) {
            id = UUID.randomUUID().toString();
            this.accountNumber = accountNumber;
            this.oldBalance = oldBalance;
            this.newBalance = newBalance;
        }

        @Override
        public String id() {
            return id;
        }

        @Override
        public String jsonBody() {
            return "{}";
        }
    }

    static class AccountChargeRejectedDueToInsufficientFounds implements Event {

        private final String id;
        private final String accountNumber;
        private final BigDecimal balance;
        private final BigDecimal charge;

        AccountChargeRejectedDueToInsufficientFounds(String accountNumber, BigDecimal balance, BigDecimal charge) {
            id = UUID.randomUUID().toString();
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.charge = charge;
        }

        @Override
        public String id() {
            return id;
        }

        @Override
        public String jsonBody() {
            return "{}";
        }
    }
}
