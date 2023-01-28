package com.pawelpluta.day026;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
class CommandHandler {

    private final BankAccountRepository accountRepository;
    private final EventPublisher eventPublisher;

    CommandHandler(BankAccountRepository accountRepository, EventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    void handle(ChargeAccountCommand command) {
        accountRepository.findById(command.accountId()).ifPresent(bankAccount ->
                {
                    BankAccount updatedBankAccount = bankAccount.handle(command);
                    accountRepository.save(updatedBankAccount);
                    eventPublisher.publish(updatedBankAccount.events());
                }
        );
    }
}
