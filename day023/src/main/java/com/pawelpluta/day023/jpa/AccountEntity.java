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

    public AccountEntity() {
    }

    public AccountEntity(String id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    static AccountEntity from(Account account) {
        return new AccountEntity(
                account.id().value(),
                account.balance().doubleValue());
    }

    Account toModel() {
        return new Account(new AccountId(id), BigDecimal.valueOf(balance));
    }
}
