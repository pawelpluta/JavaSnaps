package com.pawelpluta.day009;

record MatchResults(
    boolean matchedInteger,
    boolean matchedLong,
    boolean matchedChar,
    boolean matchedCharSequence,
    boolean matchedString,
    boolean matchedNull) {
    static MatchResults forInteger() {
        return new MatchResults(true,false,false,false,false,false);
    }
    static MatchResults forLong() {
        return new MatchResults(false,true,false,false,false,false);
    }
    static MatchResults forChar() {
        return new MatchResults(false,false,true,false,false,false);
    }
    static MatchResults forCharSequence() {
        return new MatchResults(false,false,false,true,false,false);
    }
    static MatchResults forString() {
        return new MatchResults(false,false,false,false,true,false);
    }
    static MatchResults forNull() {
        return new MatchResults(false,false,false,false,false,true);
    }

}
