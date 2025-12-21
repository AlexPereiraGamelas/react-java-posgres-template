package app.controller;

import app.http.PaginatedResponse;
import app.http.Request;
import app.http.Response;
import app.model.Book;
import app.routing.Router;
import app.service.BookService;

import java.util.Map;

public class BookController implements Controller{

    private final BookService bookService = new BookService();

    @Override
    public void registerRoutes(Router router) {
        router.register("GET", "/book/{id}", this::find);
        router.register("GET", "/books", this::listAll);
    }

    public Response find(Request req) {
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
    public Response listAll(Request req) {
        int offset = 0, limit = 10;
        if (req.queryParam("offset") != null) offset = Integer.parseInt(req.queryParam("offset"));
        if (req.queryParam("limit") != null) limit = Integer.parseInt(req.queryParam("limit"));

        PaginatedResponse<Book> result = bookService.getBooks(offset, limit);
        return Response.json(200, result);
    }
}