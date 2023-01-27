package com.pawelpluta.day026.jpa;

import com.pawelpluta.day026.BankAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
class BankAccountEntity {
    @Id
    @Column(name = "account_id")
    private String id;
    @Column(name = "balance")
    private BigDecimal balance;

    public BankAccountEntity() {
    }

    public BankAccountEntity(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    static BankAccountEntity from(BankAccount account) {
        return new BankAccountEntity(
                account.accountNumber(),
                account.balance());
    }

    BankAccount toModel() {
        return BankAccount.of(id, balance);
    }
}
