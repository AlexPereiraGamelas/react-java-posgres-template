package app;

import app.controller.HealthcheckController;
import app.controller.UserController;
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
        UserController userController = new UserController();
        HealthcheckController healthcheckController = new HealthcheckController();

        //Healthcheck Controller Registry
        router.register("GET", "/healthcheck", healthcheckController::check);

        //Users Controller Registry
        router.register("GET", "/users/random", userController::sample);
        router.register("GET", "/users/default", userController::defaultUser);

        server.createContext("/", new FrontController(router));
        server.start();

        System.out.println("Server started at http://localhost:" + port);
    }
}