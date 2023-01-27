package com.pawelpluta.day026;

import org.springframework.stereotype.Service;

@Service
class CommandHandler {

    private final BankAccountRepository accountRepository;

    CommandHandler(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    void handle(ChargeAccountCommand command) {
        accountRepository.findById(command.accountId())
                .ifPresent(bankAccount -> accountRepository.save(bankAccount.handle(command)));
    }
}
