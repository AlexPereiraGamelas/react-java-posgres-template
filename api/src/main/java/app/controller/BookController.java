package app.controller;

import app.http.PaginatedResponse;
import app.http.PaginationPolicy;
import app.http.Request;
import app.http.Response;
import app.model.Book;
import app.routing.Router;
import app.service.BookService;

import java.io.IOException;
import java.util.Map;

public class BookController implements Controller {
    private final PaginationPolicy paginationPolicy = new PaginationPolicy(20, 100, 0);
    private final BookService bookService = new BookService();

    @Override
    public void registerRoutes(Router router) {
        router.register("GET", "/book/{id}", this::read);
        router.register("GET", "/books", this::list);
        router.register("POST", "/books/new", this::create);
        router.register("PUT", "/book/{id}", this::update);
        router.register("DELETE", "/book/{id}", this::delete);
    }

    public Response read(Request req) {
        /*
         * Handle path parameter with typecasting
         */
        String idParameter = req.pathParam("id");
        int id;
        try {
            id = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            return Response.json(400, Map.of("error", "Invalid book id"));
        }

        Book book = bookService.getBookById(id);
        return Response.json(200, book);
    }

    // List all with pagination
    public Response list(Request req) {
        Integer offset = req.queryParamAsInt("offset");
        Integer limit = req.queryParamAsInt("limit");

        PaginatedResponse<Book> result = bookService.getBooks(offset, limit);
        return Response.json(200, result);
    }

    // Create book
    public Response create(Request req) throws IOException {
        Book book = req.body(Book.class);
        Book created = bookService.register(book);
        return Response.json(201, created);
    }

    public Response update(Request req) throws IOException {
        Integer id = req.pathParamAsInt("id");
        Book book = req.body(Book.class);
        return bookService.updateBook(id, book);
    }

    public Response delete(Request req) {
        Integer id = req.pathParamAsInt("id");
        return bookService.deleteBook(id);
    }
}