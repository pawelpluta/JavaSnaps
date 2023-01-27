package com.pawelpluta.day025;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.pawelpluta.day025.CommandFixture.someCreateUserCommand;
import static com.pawelpluta.day025.CommandFixture.someEmailAssignCommand;
import static com.pawelpluta.day025.CommandFixture.someSendEmailCommand;
import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {

    private static MailService mailService;

    @BeforeAll
    static void setup() {
        mailService = new MailService(new InMemoryUserRepository(), new DummyEmailOutbox());
    }

    @Test
    void shouldReturnConfirmationAboutSentEmailMessage() {
        // given
        var registerCommand = someCreateUserCommand();
        var assignEmailCommand = someEmailAssignCommand(registerCommand.userId());
        mailService.execute(registerCommand);
        mailService.execute(assignEmailCommand);

        // expect
        assertTrue(mailService.execute(someSendEmailCommand(registerCommand.userId())));
    }

    @Test
    void shouldReturnFalseOnEmailSendoutForUserWithoutEmailAddress() {
        // given
        var registerCommand = someCreateUserCommand();
        mailService.execute(registerCommand);

        // expect
        assertFalse(mailService.execute(someSendEmailCommand(registerCommand.userId())));
    }

}