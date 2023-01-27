package com.pawelpluta.day026;

import java.util.Optional;

public interface BankAccountRepository {
    Optional<BankAccount> findById(String id);
    void save(BankAccount account);
}
