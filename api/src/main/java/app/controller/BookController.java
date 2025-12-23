package app.controller;

import app.http.PaginatedResponse;
import app.http.PaginationPolicy;
import app.http.Request;
import app.http.Response;
import app.http.exception.BadRequestException;
import app.model.Book;
import app.routing.Router;
import app.service.BookService;

import java.io.IOException;
import java.util.Map;

public class BookController extends CRUDController {
    private final PaginationPolicy paginationPolicy = new PaginationPolicy(20, 100, 0);
    private final BookService bookService = new BookService();

    public BookController() {
        super("/book");
    }

    @Override
    public Response list(Request req) {
        Integer offset = req.queryParamAsInt("offset");
        Integer limit = req.queryParamAsInt("limit");

        PaginatedResponse<Book> result = bookService.getBooks(offset, limit);
        return Response.json(200, result);
    }

    @Override
    public Response read(Request req) {
        Integer id = req.pathParamAsInt("id");
        if (id == null) {
            throw new BadRequestException("id is required");
        }
        Book book = bookService.getBookById(id);
        return Response.json(200, book);
    }

    @Override
    public Response create(Request req) throws IOException {
        Book book = req.body(Book.class);
        Book created = bookService.register(book);
        return Response.json(201, created);
    }

    @Override
    public Response update(Request req) throws IOException {
        Integer id = req.pathParamAsInt("id");
        if (id == null) {
            throw new BadRequestException("id is required");
        }
        Book book = req.body(Book.class);
        return bookService.updateBook(id, book);
    }

    @Override
    public Response delete(Request req) {
        Integer id = req.pathParamAsInt("id");
        if (id == null) {
            throw new BadRequestException("id is required");
        }
        return bookService.deleteBook(id);
    }
}