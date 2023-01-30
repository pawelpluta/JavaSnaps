package com.pawelpluta.day029;

import java.util.Optional;

interface BankRepository {

    Optional<Bank> findById(String bankId);
    void save(Bank bank);
}
