package com.pawelpluta.day025;

import java.util.Optional;

class MailService {
    private final UserRepository userRepository;
    private final EmailOutbox emailOutbox;

    MailService(UserRepository userRepository, EmailOutbox emailOutbox) {
        this.userRepository = userRepository;
        this.emailOutbox = emailOutbox;
    }

    void execute(CreateUserCommand command) {
        userRepository.register(new User(new UserId(command.userId()), command.login(), null));
    }

    void execute(AssignEmailAddressCreateUserCommand command) {
        userRepository.findById(new UserId(command.userId()))
                .map(user -> new User(user.id(), user.login(), new EmailAddress(command.email())))
                .ifPresent(userRepository::save);
    }

    boolean execute(SendEmailToUserCommand command) {
        Optional<EmailAddress> emailAddress = userRepository.findById(new UserId(command.userId())).map(User::emailAddress);
        if (emailAddress.isEmpty()) {
            return false;
        }
        emailAddress.ifPresent(address -> emailOutbox.send(
                new Email(address, command.title(), command.content())));
        return true;
    }

    record CreateUserCommand(String userId, String login) {}
    record AssignEmailAddressCreateUserCommand(String userId, String email) {}
    record SendEmailToUserCommand(String userId, String title, String content) {}
}
