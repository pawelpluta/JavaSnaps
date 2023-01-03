package com.pawelpluta.day002;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

class JoinedText {

    private static final String SPACE_DELIMITER = " ";
    private final String value;

    private JoinedText(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    static JoinedText of(String... texts) {
        return new JoinedText(Arrays.stream(texts)
                .filter(Objects::nonNull)
                .filter(t -> !t.isBlank())
                .collect(Collectors.joining(SPACE_DELIMITER)));
    }
}
