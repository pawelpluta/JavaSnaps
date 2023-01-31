package com.pawelpluta.day030;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LibraryMemberCommandHandlerIT {

    @Autowired
    LibraryMemberCommandHandler libraryMemberCommandHandler;

    @Autowired
    InMemoryEventStore eventStore;

    @Test
    void memberCanBeRegistered() {
        // given
        String memberId = someMemberId();
        RegisterLibraryMemberCommand command = someRegisterLibraryMemberCommand(memberId);

        // when
        libraryMemberCommandHandler.handle(command);

        // then
        assertTrue(lastEventOf(memberId) instanceof LibraryMemberRegistered);
    }

    @Test
    void shouldEmitEventAboutBorrowedBook() {
        // given
        String memberId = someMemberId();
        RegisterLibraryMemberCommand registerCommand = someRegisterLibraryMemberCommand(memberId);
        BorrowABookCommand borrowABookCommand = someBorrowABookCommand(memberId);

        // when
        libraryMemberCommandHandler.handle(registerCommand);
        libraryMemberCommandHandler.handle(borrowABookCommand);

        // then
        assertTrue(lastEventOf(memberId) instanceof BookBorrowed);
    }

    @Test
    void memberCanBorrowAtMostThreeBooks() {
        // given
        String memberId = someMemberId();
        libraryMemberCommandHandler.handle(someRegisterLibraryMemberCommand(memberId));
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));

        // when
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));

        // then
        assertTrue(lastEventOf(memberId) instanceof BookNotBorrowed);
    }

    @Test
    void shouldEmitEventAboutReturnedBook() {
        // given
        String memberId = someMemberId();
        RegisterLibraryMemberCommand registerMemberCommand = someRegisterLibraryMemberCommand(memberId);
        BorrowABookCommand borrowABookCommand = someBorrowABookCommand(memberId);
        libraryMemberCommandHandler.handle(registerMemberCommand);
        libraryMemberCommandHandler.handle(borrowABookCommand);

        // when
        libraryMemberCommandHandler.handle(someReturnABookCommand(memberId, borrowABookCommand.bookId()));

        // then
        assertTrue(lastEventOf(memberId) instanceof BookReturned);
    }

    @Test
    void returnedBookDoesNotCountInMaxBorrowedBooksLimit() {
        // given
        String memberId = someMemberId();
        RegisterLibraryMemberCommand registerMemberCommand = someRegisterLibraryMemberCommand(memberId);
        BorrowABookCommand borrowABookCommand = someBorrowABookCommand(memberId);
        libraryMemberCommandHandler.handle(registerMemberCommand);
        libraryMemberCommandHandler.handle(borrowABookCommand);
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));
        libraryMemberCommandHandler.handle(someReturnABookCommand(memberId, borrowABookCommand.bookId()));

        // when
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));

        // then
        assertTrue(lastEventOf(memberId) instanceof BookBorrowed);
    }

    @Test
    void memberWhoDidNotReturnBookWithinSigMonthsCannotBorrowAnotherBook() {
        // given
        String memberId = someMemberId();
        List<Event> pastEvents = List.of(
                new LibraryMemberRegistered(memberId, tenYearsAgo(), "Pawel", "Pluta", 1988),
                new BookBorrowed(memberId, Instant.now(), UUID.randomUUID().toString()),
                new BookBorrowed(memberId, oneYearAgo(), UUID.randomUUID().toString()));
        eventStore.emit(pastEvents);

        // when
        libraryMemberCommandHandler.handle(someBorrowABookCommand(memberId));

        // then
        assertTrue(lastEventOf(memberId) instanceof BookNotBorrowed);
    }

    private String someMemberId() {
        return UUID.randomUUID().toString();
    }

    private RegisterLibraryMemberCommand someRegisterLibraryMemberCommand(String memberId) {
        return new RegisterLibraryMemberCommand(
                memberId,
                "firstName_" + randomAlphabetic(8),
                "lastName_" + randomAlphabetic(8),
                1988
        );
    }

    private Event lastEventOf(String memberId) {
        List<Event> events = eventStore.getFor(memberId);
        return events.get(events.size() - 1);
    }

    private BorrowABookCommand someBorrowABookCommand(String memberId) {
        return new BorrowABookCommand(memberId, UUID.randomUUID().toString(), 12);
    }

    private ReturnABookCommand someReturnABookCommand(String memberId, String bookId) {
        return new ReturnABookCommand(memberId, bookId);
    }

    private Instant tenYearsAgo() {
        return LocalDateTime.now().minusYears(10).toInstant(ZoneOffset.UTC);
    }

    private Instant oneYearAgo() {
        return LocalDateTime.now().minusYears(1).toInstant(ZoneOffset.UTC);
    }

}