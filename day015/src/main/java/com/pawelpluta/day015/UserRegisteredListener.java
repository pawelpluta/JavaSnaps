package com.pawelpluta.day015;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class UserRegisteredListener {

    private final UserLoginRepository userLoginRepository;

    UserRegisteredListener(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @KafkaListener(topics = "${kafka.userRegistered.topic}")
    void handle(UserRegisteredEvent userRegistered) {
        userLoginRepository.save(new UserLogin(userRegistered.userId, userRegistered.login));
    }

    record UserRegisteredEvent(
            String eventId,
            String userId,
            String firstName,
            String lastName,
            String login,
            String passwordHash){}
}
