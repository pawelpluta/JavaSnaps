package com.pawelpluta.day023;

import org.springframework.stereotype.Service;

@Service
class AccountService {

    private final AccountRepository accountRepository;

    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    void execute(RechargeAccountCommand command) {
        accountRepository.findById(new AccountId(command.accountId()))
                .map(account -> account.accept(command))
                .ifPresent(accountRepository::save);
    }

}
