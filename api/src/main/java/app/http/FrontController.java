package app.http;

import app.routing.Router;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Handles incoming requests and calls the router to dispatch them
 * Writes response to exchange stream
 */
public class FrontController implements HttpHandler {

    private final Router router;

    public FrontController(Router router) {
        this.router = router;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            Response response = router.dispatch(exchange);

            response.headers.forEach(
                    (k, v) -> exchange.getResponseHeaders().add(k, v)
            );

            byte[] bytes = response.body.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(response.status, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                exchange.sendResponseHeaders(500, -1);
            } catch (Exception ignored) {
            }
        }
    }
}