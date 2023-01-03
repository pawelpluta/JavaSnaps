package com.pawelpluta.day002;

class JoinedText {

    private final String value;

    private JoinedText(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    static JoinedText of(String... texts) {
        return null;
    }
}
