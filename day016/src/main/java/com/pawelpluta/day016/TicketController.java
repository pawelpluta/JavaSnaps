package com.pawelpluta.day016;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class TicketController {

    private final TicketReservationService ticketReservation;

    TicketController(TicketReservationService ticketReservation) {
        this.ticketReservation = ticketReservation;
    }

    ResponseEntity buyTicket(BuyTicket command) {
        ticketReservation.handle(command);
        return ResponseEntity.accepted().build();
    }

}
