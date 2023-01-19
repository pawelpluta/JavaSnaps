package com.pawelpluta.day018;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/books")
class BookController {

    private final FakeBookService bookService;

    BookController(FakeBookService bookService) {
        this.bookService = bookService;
    }

    @Get("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    HttpResponse<Book> getBook(String bookId) {
        bookService.findBook(new BookId(bookId));
        return null;
    }
}
