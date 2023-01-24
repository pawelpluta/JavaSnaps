package com.pawelpluta.day023.jpa;

import com.pawelpluta.day023.Account;
import com.pawelpluta.day023.AccountId;
import com.pawelpluta.day023.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class JpaAccountRepository implements AccountRepository {

    private final JpaAccountEntityRepository repository;

    JpaAccountRepository(JpaAccountEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> findById(AccountId id) {
        return repository.findById(id.value()).map(AccountEntity::toModel);
    }

    @Override
    public void save(Account account) {
        repository.save(AccountEntity.from(account));
    }
}
