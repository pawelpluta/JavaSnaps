package com.pawelpluta.day002;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoinedTextTest {

     @Test
     void singleWordTextShouldNotBeModified() {
         // expect
         assertEquals("test", JoinedText.of("test").value());
     }

     @Test
     void twoWordsShouldBeJoinedWithSpace() {
          // given
          String text1 = "text1";
          String text2 = "text2";

          // expect
          assertEquals("text1 text2", JoinedText.of(text1, text2).value());
     }

     @Test
     void emptyWordsShouldBeOmitted() {
          // given
          String emptyText = "";
          String blankText = " ";

          // expect
          assertEquals("", JoinedText.of(emptyText, blankText).value());
     }

     @Test
     void nullWordsShouldBeOmitted() {
          // given
          String text1 = "test1";
          String nullText = null;
          String text2 = "test2";

          // expect
          assertEquals("", JoinedText.of(text1, nullText, text2).value());
     }

}