package com.pawelpluta.day009;

import org.junit.jupiter.api.Test;

import java.nio.CharBuffer;

import static org.junit.jupiter.api.Assertions.*;

class SwitchTypeMatchingTest {

    SwitchTypeMatching typeMatching = new SwitchTypeMatching();

    @Test
    void shouldMatchIntegerValue() {
        // given
        Object value = 123;

        // when
        MatchResults result = typeMatching.match(value);

        // then
        assertTrue(result.matchedInteger());
    }

    @Test
    void shouldMatchPositiveLongValue() {
        // given
        Object value = 1L;

        // when
        MatchResults result = typeMatching.match(value);

        // then
        assertTrue(result.matchedLong());
    }

    @Test
    void shouldMatchCharValue() {
        // given
        Object value = Character.valueOf('a');

        // when
        MatchResults result = typeMatching.match(value);

        // then
        assertTrue(result.matchedChar());
    }

    @Test
    void shouldMatchCharSequenceValue() {
        // given
        Object value = CharBuffer.allocate(1);

        // when
        MatchResults result = typeMatching.match(value);

        // then
        assertTrue(result.matchedCharSequence());
    }

    @Test
    void shouldMatchStringValue() {
        // given
        Object value = "Text";

        // when
        MatchResults result = typeMatching.match(value);

        // then
        assertTrue(result.matchedString());
    }

    @Test
    void shouldMatchNullValue() {
        // given
        Object value = null;

        // when
        MatchResults result = typeMatching.match(value);

        // then
        assertTrue(result.matchedNull());
    }

}