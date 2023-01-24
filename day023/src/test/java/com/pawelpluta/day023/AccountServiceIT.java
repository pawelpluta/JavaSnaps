package com.pawelpluta.day023;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountServiceIT implements PostgresqlSupport {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void theSameCommandExecuteTwiceShouldModifyValueOnlyOnce() {
        // given
        AccountId accountId = someAccountId();
        thereIsAccountWithBalance(accountId, TEN);
        RechargeAccountCommand command = rechargeAccountBy(accountId, ONE);

        // when
        accountService.execute(command);
        accountService.execute(command);

        // when
        assertEquals(BigDecimal.valueOf(11D), accountBalanceFor(accountId));
    }

    private AccountId someAccountId() {
        return new AccountId(UUID.randomUUID().toString());
    }

    private void thereIsAccountWithBalance(AccountId accountId, BigDecimal balance) {
        accountRepository.save(new Account(accountId, balance));
    }

    private RechargeAccountCommand rechargeAccountBy(AccountId accountId, BigDecimal amount) {
        return new RechargeAccountCommand(
                UUID.randomUUID().toString(),
                accountId.value(),
                amount);
    }

    private BigDecimal accountBalanceFor(AccountId accountId) {
        return accountRepository.findById(accountId).get().balance();
    }
}