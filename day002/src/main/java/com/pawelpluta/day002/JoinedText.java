package com.pawelpluta.day002;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

class JoinedText {

    private final String value;

    private JoinedText(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    static JoinedText of(String... texts) {
        return new JoinedText(Stream.of(texts)
                .filter(Objects::nonNull)
                .filter(not((String::isBlank)))
                .collect(Collectors.joining(" ")));
    }
}
