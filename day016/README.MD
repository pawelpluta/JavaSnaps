# Day 16 - Kafka Producer

This simplified ticket reservation system is supposed to emit events when ticket is bought.
Single operation creates two events - `TicketSold` and `SeatReserved`.

Your task is to implement `EventSender` class and emit events on kafka topics checked in test.
