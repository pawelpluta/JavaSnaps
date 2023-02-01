package com.pawelpluta.day030;

import java.time.Clock;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

class LibraryMember {

    private final List<Event> unemittedEvents;

    private LibraryMember() {
        this(emptyList());
    }

    private LibraryMember(List<Event> unemittedEvents) {
        this.unemittedEvents = unmodifiableList(unemittedEvents);
    }

    static LibraryMember register(String memberId, String firstName, String lastName, Integer birthYear) {
        return new LibraryMember(List.of(new LibraryMemberRegistered(
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
        return this;
    }

    LibraryMember returnBook(String bookId) {
        return this;
    }

    List<Event> events() {
        return unemittedEvents;
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
        return this;
    }

    private LibraryMember apply(BookBorrowed event) {
        return this;
    }

    private LibraryMember apply(BookReturned event) {
        return this;
    }

}
