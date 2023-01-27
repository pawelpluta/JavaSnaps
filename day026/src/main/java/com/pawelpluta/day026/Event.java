package com.pawelpluta.day026;

public interface Event {
    String id();
    String jsonBody();

    record EventVO(String id, String jsonBody) implements Event {}
}
