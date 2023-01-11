package com.pawelpluta.day009;

class SwitchTypeMatching {
    MatchResults match(Object value) {
        return switch (value) {
            case null -> MatchResults.forNull();
            case Integer casted -> MatchResults.forInteger();
            case Long casted -> MatchResults.forLong();
            case Character casted -> MatchResults.forChar();
            case String casted -> MatchResults.forString();
            case CharSequence casted -> MatchResults.forCharSequence();
            default -> throw new IllegalArgumentException("Unsupported value: " + value);
        };
    }
}
