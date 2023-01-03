package com.pawelpluta.day001;

import java.time.Instant;

record Auction(ItemId item, Instant dueDate, Bid highestBid) {

    Auction place(Bid bid) {
        if (bid.isAfter(dueDate)) {
            return this;
        } else if (highestBid.isHigherThan(bid.value())) {
            return this;
        } else if (highestBid.isAfter(bid.offeredAt())) {
            return this;
        }
        return new Auction(item, dueDate, bid);
    }


    Money currentBid() {
        return highestBid.value();
    }
}
