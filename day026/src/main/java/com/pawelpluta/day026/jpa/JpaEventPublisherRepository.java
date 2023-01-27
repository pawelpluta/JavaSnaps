package com.pawelpluta.day026.jpa;

import com.pawelpluta.day026.Event;
import com.pawelpluta.day026.EventSupplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class JpaEventPublisherRepository implements EventSupplier {

    private final JpaEventEntityRepository repository;

    JpaEventPublisherRepository(JpaEventEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> fetchEventsToSend() {
        return repository.findAllBySentIsFalse().stream().map(entity -> (Event) new Event.EventVO(entity.getId(), entity.getBody())).toList();
    }
}
