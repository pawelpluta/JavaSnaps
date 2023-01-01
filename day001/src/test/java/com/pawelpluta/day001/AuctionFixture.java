package com.pawelpluta.day001;

import java.time.Instant;
import java.util.UUID;

import static com.pawelpluta.day001.TimeFixture.today;
import static java.math.BigDecimal.valueOf;

class AuctionFixture {
    static ItemId someItemId() {
        return new ItemId(UUID.randomUUID().toString());
    }
    static OffererId someOffererId() {
        return new OffererId(UUID.randomUUID().toString());
    }

    static Money aMoney(Long amount) {
        return new Money(valueOf(amount));
    }

    static Bid bidValued(Long amount) {
        return new Bid(someOffererId(), aMoney(amount), today());
    }

    static Bid bidValued(Long amount, Instant offeredAt) {
        return new Bid(someOffererId(), aMoney(amount), offeredAt);
    }
}
