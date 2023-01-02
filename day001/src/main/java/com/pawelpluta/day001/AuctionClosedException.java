package com.pawelpluta.day001;

public class AuctionClosedException extends RuntimeException {

    public static final String BIG_AFTER_AUCTION_CLOSED_MESSAGE = "Bid placed after auction was closed";
    public AuctionClosedException(String message) {
        super(message);
    }
}
