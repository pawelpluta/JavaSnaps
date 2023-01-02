package com.pawelpluta.day001;

import java.time.Instant;

record Auction(ItemId item, Instant dueDate, Bid highestBid, boolean isOpen) {

    public Auction(ItemId item, Instant dueDate, Bid highestBid) {
        this(item, dueDate, highestBid, true);
    }
    Auction place(Bid bid) {
        if (!isOpen) {
            return this;
        }
        if (highestBid.isHigherThan(bid.value())) {
            return this;
        } else if (highestBid.isAfter(bid.offeredAt())) {
            return this;
        }
        return new Auction(item, dueDate, bid, true);
    }

    Auction close() {
        return new Auction(item, dueDate, highestBid, false);
    }

    Money currentBid() {
        return highestBid.value();
    }
}
