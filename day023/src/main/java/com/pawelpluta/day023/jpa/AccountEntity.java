package com.pawelpluta.day023.jpa;

import com.pawelpluta.day023.Account;
import com.pawelpluta.day023.AccountId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
class AccountEntity {
    @Id
    @Column(name = "account_id")
    private String id;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "event_ids")
    private String[] eventIds;

    public AccountEntity() {
    }

    public AccountEntity(String id, Double balance, String[] eventIds) {
        this.id = id;
        this.balance = balance;
        this.eventIds = eventIds;
    }

    static AccountEntity from(Account account) {
        return new AccountEntity(
                account.id().value(),
                account.balance().doubleValue(),
                account.consumedEventIds().toArray(new String[0]));
    }

    Account toModel() {
        return new Account(new AccountId(id), BigDecimal.valueOf(balance), List.of(eventIds));
    }
}
