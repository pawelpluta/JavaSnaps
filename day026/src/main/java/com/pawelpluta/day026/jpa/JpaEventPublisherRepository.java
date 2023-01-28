package com.pawelpluta.day026.jpa;

import com.pawelpluta.day026.Event;
import com.pawelpluta.day026.EventPublisher;
import com.pawelpluta.day026.EventSupplier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
class JpaEventPublisherRepository implements EventPublisher, EventSupplier {

    private final JpaEventEntityRepository repository;

    JpaEventPublisherRepository(JpaEventEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void publish(List<Event> events) {
        List<EventEntity> mappedEvents = events.stream().map(EventEntity::from).collect(Collectors.toList());
        repository.saveAll(mappedEvents);
    }

    @Override
    public List<Event> fetchEventsToSend() {
        return repository.findAllBySentIsFalse().stream().map(entity -> (Event) new Event.EventVO(entity.getId(), entity.getBody())).toList();
    }
}
