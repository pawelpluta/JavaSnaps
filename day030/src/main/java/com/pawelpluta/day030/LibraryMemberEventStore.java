package com.pawelpluta.day030;

import java.util.List;

interface LibraryMemberEventStore {

    void emit(Event event);
    default void emit(List<Event> event) {
        event.forEach(this::emit);
    }
    List<Event> getFor(String libraryMemberId);
}
