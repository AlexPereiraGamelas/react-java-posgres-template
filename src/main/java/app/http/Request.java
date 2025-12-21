package app.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Request {

    private final HttpExchange exchange;
    private final Map<String, String> pathParams;
    private final Map<String, String> queryParams;

    public Request(HttpExchange exchange, Map<String, String> pathParams, Map<String, String> queryParams) {
        this.exchange = exchange;
        this.pathParams = pathParams;
        this.queryParams = queryParams;
    }

    public String method() {
        return exchange.getRequestMethod();
    }

    public String path() {
        return exchange.getRequestURI().getPath();
    }

    public String body() throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    public String pathParam(String name) {
        return pathParams.get(name);
    }
    public String queryParam(String key) {
        return queryParams.get(key);
    }
}