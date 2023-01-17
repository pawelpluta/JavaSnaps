package com.pawelpluta.day016;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
class TicketReservationService {

    private final EventSender eventSender;

    TicketReservationService(EventSender eventSender) {
        this.eventSender = eventSender;
    }

    void handle(BuyTicket command) {
        // logic removed for simplicity reasons

        eventSender.send(reserveSeat(command));
        eventSender.send(sell(command));
    }

    private SeatReserved reserveSeat(BuyTicket command) {
        return new SeatReserved(
                UUID.randomUUID().toString(),
                command.performanceId(),
                "A25",
                command.rowNumber(),
                command.seatNumber());
    }

    private TicketSold sell(BuyTicket command) {
        return new TicketSold(
                UUID.randomUUID().toString(),
                command.performanceId(),
                command.personName(),
                BigDecimal.TEN);
    }
}
