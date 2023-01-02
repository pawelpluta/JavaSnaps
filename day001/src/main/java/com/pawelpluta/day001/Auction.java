package com.pawelpluta.day001;

import java.time.Instant;

import static com.pawelpluta.day001.AuctionClosedException.BIG_AFTER_AUCTION_CLOSED_MESSAGE;
import static com.pawelpluta.day001.AuctionStatus.CLOSED;
import static com.pawelpluta.day001.AuctionStatus.OPENED;

record Auction(ItemId item, Instant dueDate, Bid highestBid, AuctionStatus status) {

    public Auction(ItemId item, Instant dueDate, Bid highestBid) {
        this(item, dueDate, highestBid, OPENED);
    }

    Auction place(Bid bid) {
        if (OPENED != status) {
            throw new AuctionClosedException(BIG_AFTER_AUCTION_CLOSED_MESSAGE);
        }
        if (highestBid.isHigherThan(bid.value())) {
            return this;
        } else if (highestBid.isAfter(bid.offeredAt())) {
            return this;
        }
        return new Auction(item, dueDate, bid, OPENED);
    }

    Auction close() {
        return new Auction(item, dueDate, highestBid, CLOSED);
    }

    Money currentBid() {
        return highestBid.value();
    }
}
