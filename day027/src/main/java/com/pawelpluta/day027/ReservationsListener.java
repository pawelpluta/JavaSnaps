package com.pawelpluta.day027;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class ReservationsListener {

    private final InMemoryPaymentRepository paymentRepository;
    private final List<String> failingOrders;

    ReservationsListener(InMemoryPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        failingOrders = new ArrayList<>();
    }

    @KafkaListener(topics = "${kafka.reservations.topic}", containerFactory = "reservationsKafkaListenerContainerFactory")
    void reserveGoods(WarehouseProductReservedEvent event) {
        if (failingOrders.contains(event.orderId())) {
            // TODO invoke compensating transaction
        } else {
            paymentRepository.save(new Payment(event.orderId(), "PAID"));
        }
    }

    /**
     * method for test puproses - simulates some business checks so we can show negative flow
     * @param orderId
     */
    void failPaymentForOrder(String orderId) {
        failingOrders.add(orderId);
    }
}
