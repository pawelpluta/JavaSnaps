package com.pawelpluta.day001;

import java.time.Instant;

record Bid(OffererId offerer, Money value, Instant offeredAt) {

    boolean isHigherThan(Money anotherValue) {
        return this.value.isHigherThan(anotherValue);
    }

    boolean isAfter(Instant anotherOfferedAt) {
        return this.offeredAt.isAfter(anotherOfferedAt);
    }
}
