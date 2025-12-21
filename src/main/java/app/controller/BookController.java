package app.controller;

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
}