package com.pawelpluta.day027;

record OrderPaymentRejectedEvent(String orderId, String productId, Integer quantity) {
}
