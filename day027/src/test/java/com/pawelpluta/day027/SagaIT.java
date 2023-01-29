package com.pawelpluta.day027;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SagaIT implements KafkaSupport {
    @Autowired
    ReservationsListener reservationsListener;
    @Autowired
    InMemoryProductRepository productRepository;
    @Autowired
    InMemoryPaymentRepository paymentRepository;
    @Autowired
    KafkaTemplate<String, OrderPlacedEvent> ordersKafkaTemplate;
    @Autowired
    KafkaTemplate<String, WarehouseProductReservedEvent> reservationsKafkaTemplate;

    @Test
    void orderingAProductShouldCausePayment() {
        // given
        String productId = UUID.randomUUID().toString();
        Integer initialQuantity = 10;
        Integer orderQuantity = 2;
        thereIsAProduct(productId, initialQuantity);
        OrderPlacedEvent orderPlacedEvent = orderFor(productId, orderQuantity);

        // when
        sent(orderPlacedEvent);

        // then
        await().atMost(5, SECONDS).untilAsserted(() -> {
            assertEquals(8, quantityForProduct(productId));
            assertEquals("PAID", paymentStatusFor(orderPlacedEvent.orderId()));
        });
    }

    @Test
    void failedPaymentShouldCauseReservationLift() {
        // given
        String productId = UUID.randomUUID().toString();
        Integer initialQuantity = 8;
        Integer orderQuantity = 2;
        thereIsAProduct(productId, initialQuantity);
        WarehouseProductReservedEvent reservationEvent = reservationFor(productId, orderQuantity);
        paymentWillFailForOrder(reservationEvent.orderId());

        // when
        sent(reservationEvent);

        // then
        await().atMost(5, SECONDS).untilAsserted(() -> {
            assertEquals("REJECTED", paymentStatusFor(reservationEvent.orderId()));
            assertEquals(10, quantityForProduct(productId));
        });
    }

    private void thereIsAProduct(String productId, Integer initialQuantity) {
        productRepository.save(new Product(productId, initialQuantity, List.of()));
    }

    private int quantityForProduct(String productId) {
        return productRepository.findById(productId).get().quantity();
    }

    private void sent(OrderPlacedEvent orderPlacedEvent) {
        ordersKafkaTemplate.send("orders-topic", orderPlacedEvent);
    }

    private void sent(WarehouseProductReservedEvent reservationEvent) {
        reservationsKafkaTemplate.send("reservations-topic", reservationEvent);
    }

    private OrderPlacedEvent orderFor(String productId, Integer orderQuantity) {
        return new OrderPlacedEvent(UUID.randomUUID().toString(), productId, orderQuantity);
    }

    private WarehouseProductReservedEvent reservationFor(String productId, Integer reservationQuantity) {
        return new WarehouseProductReservedEvent(UUID.randomUUID().toString(), productId, reservationQuantity);
    }

    private String paymentStatusFor(String order) {
        return paymentRepository.findByOrderId(order).map(Payment::status).orElse("UNKNOWN");
    }

    private void paymentWillFailForOrder(String orderId) {
        reservationsListener.failPaymentForOrder(orderId);
    }

}