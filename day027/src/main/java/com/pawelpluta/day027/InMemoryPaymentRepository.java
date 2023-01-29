package com.pawelpluta.day027;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
class InMemoryPaymentRepository {

    private final Map<String, String> paymentStatus = new HashMap<>();
    public void save(Payment payment) {
        paymentStatus.put(payment.orderId(), payment.status());
    }

    public Optional<Payment> findByOrderId(String orderId) {
        return Optional.ofNullable(paymentStatus.get(orderId))
                .map(status -> new Payment(orderId, status));
    }
}
