package com.pawelpluta.day030;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LibraryMemberCommandHandler {

    private final LibraryMemberEventStore libraryMemberEventStore;

    LibraryMemberCommandHandler(LibraryMemberEventStore libraryMemberEventStore) {
        this.libraryMemberEventStore = libraryMemberEventStore;
    }

    void handle(RegisterLibraryMemberCommand command) {
        LibraryMember freshMember = LibraryMember.register(command.memberId(), command.firstName(), command.lastName(), command.birthYear());
        libraryMemberEventStore.emit(freshMember.events());
    }

    void handle(BorrowABookCommand command) {
        List<Event> libraryMemberPastEvents = libraryMemberEventStore.getFor(command.libraryMemberId());
        LibraryMember libraryMember = LibraryMember.of(libraryMemberPastEvents).borrowBook(command.bookId(), command.minimalRecommendedReaderAge());
        libraryMemberEventStore.emit(libraryMember.events());
    }

    void handle(ReturnABookCommand command) {
        List<Event> libraryMemberPastEvents = libraryMemberEventStore.getFor(command.libraryMemberId());
        LibraryMember libraryMember = LibraryMember.of(libraryMemberPastEvents).returnBook(command.bookId());
        libraryMemberEventStore.emit(libraryMember.events());
    }

}
