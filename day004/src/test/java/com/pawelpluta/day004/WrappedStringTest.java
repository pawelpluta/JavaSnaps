package com.pawelpluta.day004;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WrappedStringTest {

    @Test
    void shouldRecognizePalindromeWithEvenCountOfCharacters() {
        // given
        WrappedString text = new WrappedString("abba");

        // expect
        assertTrue(text.isPalindrome());
    }

    @Test
    void shouldRecognizePalindromeWithOddCountOfCharacters() {
        // given
        WrappedString text = new WrappedString("bcb");

        // expect
        assertTrue(text.isPalindrome());
    }

    @Test
    void shouldRecognizeMultiWord() {
        // given
        WrappedString text = new WrappedString("race fast safe car");

        // expect
        assertTrue(text.isPalindrome());
    }

    @Test
    void shouldIgnoreLetterCase() {
        // given
        WrappedString text = new WrappedString("Race fast safe car");

        // expect
        assertTrue(text.isPalindrome());
    }

    @Test
    void shouldRecognizeIfTextIsNotAPalindrome() {
        // given
        WrappedString text = new WrappedString("abc");

        // expect
        assertFalse(text.isPalindrome());
    }

}