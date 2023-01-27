package com.pawelpluta.day026;

import java.util.List;

public interface EventSupplier {
    List<Event> fetchEventsToSend();
}
