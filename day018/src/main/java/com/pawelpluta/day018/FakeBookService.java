package com.pawelpluta.day018;

import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
class FakeBookService {

    private static final BookId EXISTING_BOOK_ID = new BookId("existingBook");
    private static final BookId BOOK_ID_CAUSING_PROBLEMS = new BookId("brewCoffee");

    Optional<Book> findBook(BookId bookId) {
        if (bookId.equals(EXISTING_BOOK_ID)) {
            return Optional.of(someBook(bookId));
        } else if (bookId.equals(BOOK_ID_CAUSING_PROBLEMS)) {
            throw new CoffeeBookException();
        }
        return Optional.empty();
    }

    private Book someBook(BookId bookId) {
        return new Book(bookId, "John Doe", "Redactor will change it", "123-1234567890");
    }

}
