package com.pawelpluta.day004;

record WrappedString(String value) {
    boolean isPalindrome() {
        String textWithoutBlanks = value.replaceAll("\\s", "");
        String reversedText = new StringBuilder(textWithoutBlanks).reverse().toString();
        return textWithoutBlanks.equalsIgnoreCase(reversedText);
    }
}
