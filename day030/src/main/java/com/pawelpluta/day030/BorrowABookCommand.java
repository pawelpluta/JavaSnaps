package com.pawelpluta.day030;

record BorrowABookCommand(String libraryMemberId, String bookId, Integer minimalRecommendedReaderAge) {
}
