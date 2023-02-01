package com.pawelpluta.day030;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

class LibraryMember {

    private static final Integer MAXIMUM_BORROWED_BOOKS_COUNT = 3;
    private static final Duration MAXIMUM_BORROW_TIME = Duration.ofDays(183);
    private final String memberId;
    private final Map<String, Duration> borrowedBooks;
    private final List<Event> unemittedEvents;

    private LibraryMember() {
        this(null, emptyMap(), emptyList());
    }

    private LibraryMember(String memberId, Map<String, Duration> borrowedBooks, List<Event> unemittedEvents) {
        this.memberId = memberId;
        this.borrowedBooks = unmodifiableMap(borrowedBooks);
        this.unemittedEvents = unmodifiableList(unemittedEvents);
    }

    static LibraryMember register(String memberId, String firstName, String lastName, Integer birthYear) {
        return new LibraryMember(memberId, emptyMap(), List.of(new LibraryMemberRegistered(
                memberId,
                Clock.systemUTC().instant(),
                firstName,
                lastName,
                birthYear
        )));
    }

    static LibraryMember of(List<Event> pastEvents) {
        LibraryMember member = new LibraryMember();
        for (Event pastEvent : pastEvents) {
            member = member.apply(pastEvent);
        }
        return member;
    }

    LibraryMember borrowBook(String bookId, Integer minimalRecommendedAge) {
        if (maxBorrowedBooksLimitReached() || anyBookNotReturnedOnTime()) {
            BookNotBorrowed bookNotBorrowedEvent = new BookNotBorrowed(memberId, Instant.now(), bookId);
            return apply(bookNotBorrowedEvent).emit(bookNotBorrowedEvent);
        }
        BookBorrowed bookBorrowedEvent = new BookBorrowed(memberId, Instant.now(), bookId);
        return apply(bookBorrowedEvent).emit(bookBorrowedEvent);
    }

    private boolean maxBorrowedBooksLimitReached() {
        return borrowedBooks.size() >= MAXIMUM_BORROWED_BOOKS_COUNT;
    }

    private boolean anyBookNotReturnedOnTime() {
        return borrowedBooks.values().stream().max(Comparator.naturalOrder())
                .filter(longestBorrowTime -> longestBorrowTime.compareTo(MAXIMUM_BORROW_TIME) >= 0)
                .isPresent();
    }

    LibraryMember returnBook(String bookId) {
        if (borrowedBooks.containsKey(bookId)) {
            BookReturned bookReturnedEvent = new BookReturned(memberId, Instant.now(), bookId);
            return apply(bookReturnedEvent).emit(bookReturnedEvent);
        }
        return this;
    }

    List<Event> events() {
        return unemittedEvents;
    }

    private LibraryMember emit(Event event) {
        List<Event> updatedEvents = new ArrayList<>(unemittedEvents);
        updatedEvents.add(event);
        return new LibraryMember(memberId, borrowedBooks, updatedEvents);
    }

    private LibraryMember apply(Event event) {
        if (event instanceof LibraryMemberRegistered) {
            return apply((LibraryMemberRegistered) event);
        } else if (event instanceof BookBorrowed) {
            return apply((BookBorrowed) event);
        } else if (event instanceof BookReturned) {
            return apply((BookReturned) event);
        }
        return this;
    }

    private LibraryMember apply(LibraryMemberRegistered event) {
        return new LibraryMember(event.aggregateId(), borrowedBooks, unemittedEvents);
    }

    private LibraryMember apply(BookBorrowed event) {
        Map<String, Duration> updatedBorrowedBooks = new HashMap<>(borrowedBooks);
        updatedBorrowedBooks.put(event.bookId(), durationBetweenNowAndOccurenceOf(event));
        return new LibraryMember(event.aggregateId(), updatedBorrowedBooks, unemittedEvents);
    }

    private LibraryMember apply(BookReturned event) {
        Map<String, Duration> updatedBorrowedBooks = new HashMap<>(borrowedBooks);
        updatedBorrowedBooks.remove(event.bookId());
        return new LibraryMember(event.aggregateId(), updatedBorrowedBooks, unemittedEvents);
    }

    private Duration durationBetweenNowAndOccurenceOf(Event event) {
        return Duration.between(event.occurrenceTime(), Instant.now());
    }
}
