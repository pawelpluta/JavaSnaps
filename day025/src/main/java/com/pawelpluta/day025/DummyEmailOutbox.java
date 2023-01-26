package com.pawelpluta.day025;

class DummyEmailOutbox implements EmailOutbox {
    @Override
    public void send(Email email) {
        // NOP
    }
}
