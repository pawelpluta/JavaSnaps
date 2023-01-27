package com.pawelpluta.day026.jpa;

import com.pawelpluta.day026.BankAccount;
import com.pawelpluta.day026.BankAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class JpaBankAccountRepository implements BankAccountRepository {

    private final JpaBankAccountEntityRepository repository;

    JpaBankAccountRepository(JpaBankAccountEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BankAccount> findById(String id) {
        return repository.findById(id).map(BankAccountEntity::toModel);
    }

    @Override
    public void save(BankAccount account) {
        repository.save(BankAccountEntity.from(account));
    }
}
