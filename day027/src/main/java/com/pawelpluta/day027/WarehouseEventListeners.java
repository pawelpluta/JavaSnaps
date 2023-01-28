package com.pawelpluta.day027;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class WarehouseEventListeners {

    private final InMemoryProductRepository productRepository;
    private final KafkaTemplate<String, WarehouseProductReservedEvent> reservationsKafkaTemplate;

    WarehouseEventListeners(
            InMemoryProductRepository productRepository,
            KafkaTemplate<String, WarehouseProductReservedEvent> reservationsKafkaTemplate) {
        this.productRepository = productRepository;
        this.reservationsKafkaTemplate = reservationsKafkaTemplate;
    }

    @KafkaListener(topics = "${kafka.orders.topic}", containerFactory = "orderKafkaListenerContainerFactory")
    void reserveGoods(OrderPlacedEvent event) {
        productRepository.findById(event.productId()).ifPresent(product -> {
            Product updatedProduct = product.reserve(event);
            productRepository.save(updatedProduct);
            sendEvents(updatedProduct);
        });
    }

    private void sendEvents(Product updatedProduct) {
        updatedProduct.reservations().stream().findFirst()
                .ifPresent(reservationEvent -> reservationsKafkaTemplate.send("reservations-topic", reservationEvent));
    }
}
