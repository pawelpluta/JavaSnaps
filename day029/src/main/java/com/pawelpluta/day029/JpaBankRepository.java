package com.pawelpluta.day029;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class JpaBankRepository implements BankRepository {

    private final JpaBankEntityRepository repository;

    JpaBankRepository(JpaBankEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Bank> findById(String bankId) {
        return repository.findById(bankId).map(JpaBankEntityRepository.BankEntity::toModel);
    }

    @Override
    public void save(Bank bank) {
        repository.save(JpaBankEntityRepository.BankEntity.from(bank));
    }
}
