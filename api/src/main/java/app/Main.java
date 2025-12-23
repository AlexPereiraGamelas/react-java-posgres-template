package app;

import app.controller.BookController;
import app.controller.Controller;
import app.controller.DocsController;
import app.http.FrontController;
import app.routing.Router;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 9090;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Router router = new Router();

        //Controllers
        List<Controller> controllers = List.of(
                new DocsController(),
                new BookController()
        );

        //Route Registry
        for (Controller c : controllers) {
            c.registerRoutes(router);
        }

        server.createContext("/", new FrontController(router));
        server.start();

        System.out.println("Server started at http://localhost:" + port);
    }
}