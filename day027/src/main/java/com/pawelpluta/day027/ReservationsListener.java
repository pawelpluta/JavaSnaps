package com.pawelpluta.day027;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class ReservationsListener {

    private final InMemoryPaymentRepository paymentRepository;
    private final KafkaTemplate<String, OrderPaymentRejectedEvent> kafkaTemplate;
    private final List<String> failingOrders;

    ReservationsListener(
            InMemoryPaymentRepository paymentRepository,
            KafkaTemplate<String, OrderPaymentRejectedEvent> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
        failingOrders = new ArrayList<>();
    }

    @KafkaListener(topics = "${kafka.reservations.topic}", containerFactory = "reservationsKafkaListenerContainerFactory")
    void reserveGoods(WarehouseProductReservedEvent event) {
        if (failingOrders.contains(event.orderId())) {
            paymentRepository.save(new Payment(event.orderId(), "REJECTED"));
            kafkaTemplate.send("payments-topic", new OrderPaymentRejectedEvent(event.orderId(), event.productId(), event.quantity()));
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
