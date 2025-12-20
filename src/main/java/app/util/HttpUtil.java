package app.util;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

    public static void sendJsonResponse(HttpExchange exchange, int statusCode, String body)
            throws IOException {

        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    public static void sendMethodNotAllowed(HttpExchange exchange) throws IOException {
        System.out.println("Handling not allowed send method for " + exchange.getRequestURI());
        exchange.sendResponseHeaders(405, -1);
        exchange.close();
    }
}