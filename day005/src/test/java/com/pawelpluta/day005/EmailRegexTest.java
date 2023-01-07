package com.pawelpluta.day005;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailRegexTest {

    private static final String TESTED_REGEX_VALUE = "^[\\w.+_-]+@([\\w\\-]+\\.){1,3}?\\w+$";
    private static final Pattern TESTED_REGEX = Pattern.compile(TESTED_REGEX_VALUE);

    @Test
    void secondLevelDomainAreAllowed() {
        assertTrue(TESTED_REGEX.matcher("someMail@example.com").matches());
    }

    @Test
    void thirdLevelDomainAreAllowed() {
        assertTrue(TESTED_REGEX.matcher("someMail@en.example.com").matches());
    }

    @Test
    void fourthLevelDomainAreAllowed() {
        assertTrue(TESTED_REGEX.matcher("someMail@001.en.example.com").matches());
    }

    @Test
    void domainNamesCanContainHyphenAndNumbers() {
        assertTrue(TESTED_REGEX.matcher("someMail@sample-example01.com").matches());
    }

    @Test
    void topLevelDomainCannotContainHyphen() {
        assertFalse(TESTED_REGEX.matcher("someMail@example.c-o-m").matches());
    }

    @Test
    void domainCannotBeEmpty() {
        assertFalse(TESTED_REGEX.matcher("someMail@").matches());
    }

    @Test
    void topLevelDomainIsNotSupported() {
        assertFalse(TESTED_REGEX.matcher("someMail@com").matches());
    }

    @Test
    void mailNameCanContainLettersAndWords() {
        assertTrue(TESTED_REGEX.matcher("some0123456789Mail@example.com").matches());
    }

    @Test
    void mailNameCanContainDots() {
        assertTrue(TESTED_REGEX.matcher("some.Mail@example.com").matches());
    }

    @Test
    void mailNameCanContainHyphenUnderscoreAndPlus() {
        assertTrue(TESTED_REGEX.matcher("some-_+Mail@example.com").matches());
    }

    @Test
    void mailNameCannotContainAtSign() {
        assertFalse(TESTED_REGEX.matcher("some@Mail@example.com").matches());
    }

    @Test
    void mailNameCannotContainBeEmpty() {
        assertFalse(TESTED_REGEX.matcher("@example.com").matches());
    }
}
