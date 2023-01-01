package com.pawelpluta.day001;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.pawelpluta.day001.TimeFixture.today;
import static com.pawelpluta.day001.TimeFixture.tomorrow;
import static com.pawelpluta.day001.TimeFixture.twoDaysFromNow;
import static com.pawelpluta.day001.TimeFixture.yesterday;

class ActionTest {

    @Test
    void bidCanBePlacedWhenItHasHigherValue() {
        // given
        Auction auction = new Auction(AuctionFixture.someItemId(), tomorrow(), AuctionFixture.bidValued(5000L));

        // when
        Auction updatedAuction = auction.place(AuctionFixture.bidValued(6000L));

        // then
        Assertions.assertEquals(AuctionFixture.aMoney(6000L), updatedAuction.currentBid());
    }

    @Test
    void smalledBidCannotWinAnAuction() {
        // given
        Auction auction = new Auction(AuctionFixture.someItemId(), tomorrow(), AuctionFixture.bidValued(10000L));

        // when
        Auction updatedAuction = auction.place(AuctionFixture.bidValued(6000L));

        // then
        Assertions.assertEquals(AuctionFixture.aMoney(10000L), updatedAuction.currentBid());
    }

    @Test
    void bidThatOccurredEarlierCannotWinAnAuction() {
        // given
        Auction auction = new Auction(AuctionFixture.someItemId(), tomorrow(), AuctionFixture.bidValued(10000L, today()));
        Bid bidFromThePast = AuctionFixture.bidValued(999999L, yesterday());

        // when
        Auction updatedAuction = auction.place(bidFromThePast);

        // then
        Assertions.assertEquals(AuctionFixture.aMoney(10000L), updatedAuction.currentBid());
    }

}