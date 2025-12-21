package app.controller;

import app.http.Request;
import app.http.Response;
import app.model.Book;
import app.service.BookService;

public class BookController {

    private final BookService bookService = new BookService();

    public Response find(Request req) {
        Book book = bookService.getBookById();
        return Response.json(200, book);
    }
}