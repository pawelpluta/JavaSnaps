package com.pawelpluta.day016;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = KafkaListenerConfig.class)
@Profile("it")
class TicketControllerIT implements KafkaSupport {

    @Autowired
    ConsumerFactory<String, String> ticketsConsumerFactory;
    @Autowired
    ConsumerFactory<String, String> seatsConsumerFactory;
    @Autowired
    TicketController controller;
    @Value("${kafka.tickets.topic}")
    String ticketsTopic;
    @Value("${kafka.seats.topic}")
    String seatsTopic;

    @Test
    void reservingTicketsShouldCreateEvents() {
        // given
        BuyTicket buyTicket = someBuyTicketCommand();
        Consumer<String, String> ticketsConsumer = getTicketsConsumer();
        Consumer<String, String> seatsConsumer = getSeatsConsumer();

        // when
        controller.buyTicket(buyTicket);

        // then
        await().atMost(3, SECONDS).untilAsserted(() -> {
            assertNotNull(KafkaTestUtils.getSingleRecord(ticketsConsumer, ticketsTopic));
            assertNotNull(KafkaTestUtils.getSingleRecord(seatsConsumer, seatsTopic));
        });
    }

    private BuyTicket someBuyTicketCommand() {
        return new BuyTicket(
                UUID.randomUUID().toString(),
                "personName_" + RandomStringUtils.randomAlphabetic(10),
                5,
                10);
    }

    @NotNull
    private Consumer<String, String> getTicketsConsumer() {
        Consumer<String, String> consumer = ticketsConsumerFactory.createConsumer();
        consumer.subscribe(List.of(ticketsTopic));
        return consumer;
    }

    @NotNull
    private Consumer<String, String> getSeatsConsumer() {
        Consumer<String, String> consumer = seatsConsumerFactory.createConsumer();
        consumer.subscribe(List.of(seatsTopic));
        return consumer;
    }

}