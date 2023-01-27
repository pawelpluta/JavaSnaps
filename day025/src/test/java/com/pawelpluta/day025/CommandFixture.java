package com.pawelpluta.day025;

import org.apache.commons.lang3.RandomStringUtils;

class CommandFixture {
    static MailService.CreateUserCommand someCreateUserCommand() {
        return new MailService.CreateUserCommand("some_user_id" + RandomStringUtils.randomAlphabetic(10), "some_user_login");
    }

    static MailService.AssignEmailAddressCreateUserCommand someEmailAssignCommand(String userId) {
        return new MailService.AssignEmailAddressCreateUserCommand(userId, "someEmail@example.com");
    }

    static MailService.SendEmailToUserCommand someSendEmailCommand(String userId) {
        return new MailService.SendEmailToUserCommand(userId, "some message title", "some message content");
    }
}
