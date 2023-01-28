package com.pawelpluta.day027;

import java.util.List;

record Product(String productId, Integer quantity, List<WarehouseProductReservedEvent> reservations) {
    public Product reserve(OrderPlacedEvent event) {
        return new Product(productId, quantity - event.quantity(), List.of(reservationEventFor(event)));
    }

    private WarehouseProductReservedEvent reservationEventFor(OrderPlacedEvent event) {
        return new WarehouseProductReservedEvent(event.orderId(), productId, event.quantity());
    }
}
