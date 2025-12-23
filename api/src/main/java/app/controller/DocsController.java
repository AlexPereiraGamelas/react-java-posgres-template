package app.controller;

import app.http.Request;
import app.http.Response;
import app.routing.Router;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DocsController implements Controller {

    @Override
    public void registerRoutes(Router router) {
        router.register("GET", "/openapi.json", this::openApi);
    }

    public Response openApi(Request req) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("openapi.json")) {
            if (is == null) {
                return Response.json(404, Map.of("error", "openapi.json not found"));
            }

            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            Response res = new Response();
            res.status = 200;
            res.body = json;
            // CORS headers only for this route
            res.headers.put("Content-Type", "application/json");
            res.headers.put("Access-Control-Allow-Origin", "*");
            res.headers.put("Access-Control-Allow-Methods", "GET, OPTIONS");
            res.headers.put("Access-Control-Allow-Headers", "Content-Type, Authorization");
            return res;

        } catch (Exception e) {
            return Response.json(500, Map.of("error", "Failed to load OpenAPI spec"));
        }
    }
}