package app.http;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Request {

    private static final Gson gson = new Gson();
    private final HttpExchange exchange;
    private final Map<String, String> pathParams;
    private final Map<String, String> queryParams;
    private String cachedBody;

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

    // Raw body
    public String body() throws IOException {
        if (cachedBody == null) {
            cachedBody = new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8
            );
        }
        return cachedBody;
    }

    // JSON → POJO
    public <T> T body(Class<T> clazz) throws IOException {
        return gson.fromJson(body(), clazz);
    }

    public String pathParam(String name) {
        return pathParams.get(name);
    }

    public Integer pathParamAsInt(String name) {
        String value = pathParams.get(name);
        if (value == null || value.isBlank()) return null;
        return Integer.parseInt(value);
    }

    public String queryParam(String key) {
        return queryParams.get(key);
    }

    public Integer queryParamAsInt(String key) {
        String value = queryParams.get(key);
        if (value == null || value.isBlank()) return null;
        return Integer.parseInt(value);
    }
}