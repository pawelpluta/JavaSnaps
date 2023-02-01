package com.pawelpluta.day030;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

@Repository
class InMemoryEventStore implements LibraryMemberEventStore {

    private final Map<String, List<Event>> eventsForAggregate;

    InMemoryEventStore() {
        this.eventsForAggregate = new HashMap<>();
    }

    @Override
    public void emit(Event event) {
        eventsForAggregate.merge(
                event.aggregateId(),
                List.of(event),
                (prev, curr) -> {
                    List<Event> updatedEvents = new ArrayList<>(prev);
                    updatedEvents.addAll(curr);
                    return updatedEvents;
                });
    }

    @Override
    public List<Event> getFor(String libraryMemberId) {
        return eventsForAggregate.getOrDefault(libraryMemberId, emptyList());
    }
}
