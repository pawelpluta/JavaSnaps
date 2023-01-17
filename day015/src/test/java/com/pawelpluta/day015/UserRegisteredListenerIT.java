package com.pawelpluta.day015;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = UserRegisteredTestProducerConfig.class)
@Profile("it")
class UserRegisteredListenerIT implements KafkaSupport, MongoDbSupport {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Value("${kafka.userRegistered.topic}")
    String userRegisteredTopic;

    @Test
    void shouldSaveRegisteredUserLogin() {
        // given
        String userId = randomAlphabetic(16);
        String userLogin = randomAlphabetic(16);
        String userRegisteredEvent = someUserRegisteredEvent(userId, userLogin);

        // when
        send(userRegisteredEvent);

        // then
        await().atMost(3, SECONDS).untilAsserted(() -> {
            UserLogin user = userFromDatabaseWith(userId);
            assertNotNull(user);
            assertEquals(userId, user.userId());
            assertEquals(userLogin, user.login());
        });
    }

    private String someUserRegisteredEvent(String userId, String userLogin) {
        return """
                {
                "eventId": "%s",
                "userId": "%s",
                "firstName": "%s",
                "lastName": "%s",
                "login": "%s",
                "passwordHash": "%s"
                }
                """.formatted(UUID.randomUUID().toString(), userId, randomAlphabetic(10), randomAlphabetic(10), userLogin, randomAlphabetic(36));
    }

    private void send(String userRegisteredEvent) {
        kafkaTemplate.send(userRegisteredTopic, UUID.randomUUID().toString(), userRegisteredEvent);
    }

    private UserLogin userFromDatabaseWith(String userId) {
        return userLoginRepository.findById(userId).orElse(null);
    }
}