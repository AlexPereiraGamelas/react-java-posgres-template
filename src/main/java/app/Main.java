package app;

import app.controller.BookController;
import app.http.FrontController;
import app.routing.Router;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 9090;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Router router = new Router();

        //Controllers
        BookController bookController = new BookController();

        //Book Controller Registry
        router.register("GET", "/book", bookController::find);

        server.createContext("/", new FrontController(router));
        server.start();

        System.out.println("Server started at http://localhost:" + port);
    }
}