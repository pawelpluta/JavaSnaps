package com.pawelpluta.day026;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommandHandlerIT implements PostgresqlSupport {

    @Autowired
    CommandHandler handler;

    @Autowired
    BankAccountRepository accountRepository;

    @Autowired
    EventSupplier eventSupplier;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldSaveEventAfterAggregateStateChange() {
        // given
        BankAccount account = thereIsAnAccountWithBalance(TEN);
        ChargeAccountCommand chargeAccountCommand = new ChargeAccountCommand(account.accountNumber(), ONE);

        // when
        handler.handle(chargeAccountCommand);

        // then
        assertEquals(BigDecimal.valueOf(9), balanceOfAccount(account.accountNumber()));
        thereIsBalanceUpdateEventFor(account.accountNumber());
    }

    private BankAccount thereIsAnAccountWithBalance(BigDecimal initialBalance) {
        BankAccount account = BankAccount.of(UUID.randomUUID().toString(), initialBalance);
        accountRepository.save(account);
        return account;
    }

    private BigDecimal balanceOfAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).map(BankAccount::balance).get();
    }

    private void thereIsBalanceUpdateEventFor(String accountNumber) {
        Map lastEvent = eventSupplier.fetchEventsToSend().stream()
                .map(Event::jsonBody)
                .map(this::toMap)
                .filter(fields -> fields.get("accountNumber").equals(accountNumber))
                .findFirst().get();
        assertEquals("BankAccountBalanceDecreased", lastEvent.get("type"));
    }

    private Map toMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}