package com.pawelpluta.day016;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class EventSender {

    private final KafkaTemplate<String, SeatReserved> seatReservedKafkaTemplate;
    private final KafkaTemplate<String, TicketSold> ticketSoldKafkaTemplate;
    private final String seatReservedTopic;
    private final String ticketSoldTopic;

    EventSender(
            KafkaTemplate<String, SeatReserved> seatReservedKafkaTemplate,
            KafkaTemplate<String, TicketSold> ticketSoldKafkaTemplate,
            @Value("${kafka.seats.topic}") String seatReservedTopic,
            @Value("${kafka.tickets.topic}") String ticketSoldTopic) {
        this.seatReservedKafkaTemplate = seatReservedKafkaTemplate;
        this.ticketSoldKafkaTemplate = ticketSoldKafkaTemplate;
        this.seatReservedTopic = seatReservedTopic;
        this.ticketSoldTopic = ticketSoldTopic;
    }

    void send(SeatReserved event) {
        seatReservedKafkaTemplate.send(seatReservedTopic, event.eventId(), event);
    }

    void send(TicketSold event) {
        ticketSoldKafkaTemplate.send(ticketSoldTopic, event.eventId(), event);
    }
}
