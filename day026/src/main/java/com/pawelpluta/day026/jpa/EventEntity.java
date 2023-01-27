package com.pawelpluta.day026.jpa;

import com.pawelpluta.day026.Event;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events_outbox")
class EventEntity {
    @Id
    @Column(name = "event_id")
    private String id;
    @Column(name = "body")
    private String body;
    @Column(name = "sent")
    private Boolean sent;

    public EventEntity() {
    }

    public EventEntity(String id, String body) {
        this.id = id;
        this.body = body;
        sent = false;
    }

    static EventEntity from(Event event) {
        return new EventEntity(
                event.id(),
                event.jsonBody());
    }

    String getId() {
        return id;
    }

    String getBody() {
        return body;
    }
}
