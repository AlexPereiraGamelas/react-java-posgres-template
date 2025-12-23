package app.routing;

import app.http.ErrorResponse;
import app.http.Request;
import app.http.Response;
import app.http.exception.ApiException;
import com.sun.net.httpserver.HttpExchange;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {

    private final List<Route> routes = new ArrayList<>();

    public void register(String method, String pathTemplate, RouteHandler handler) {
        List<String> paramNames = new ArrayList<>();

        Pattern p = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_]*)\\}");
        Matcher m = p.matcher(pathTemplate);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            paramNames.add(m.group(1));      // group(1) = param name
            m.appendReplacement(sb, "([^/]+)");
        }
        m.appendTail(sb);

        String regex = "^" + sb.toString() + "$";
        Pattern compiled = Pattern.compile(regex);

        routes.add(new Route(method, compiled, handler, paramNames));
    }

    public Response dispatch(HttpExchange exchange) throws Exception {
        try {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String rawQuery = exchange.getRequestURI().getRawQuery();

            Map<String, String> queryParams = parseQuery(rawQuery);

            for (Route route : routes) {
                if (!route.method.equalsIgnoreCase(method)) continue;

                Matcher matcher = route.pathPattern.matcher(path);
                if (matcher.matches()) {
                    Map<String, String> pathParams = new HashMap<>();
                    for (int i = 0; i < route.paramNames.size(); i++) {
                        pathParams.put(route.paramNames.get(i), matcher.group(i + 1));
                    }
                    Request request = new Request(exchange, pathParams, queryParams);
                    return route.handler.handle(request);
                }
            }

            return Response.json(404, Map.of("error", "Not Found"));
        } catch (ApiException ex) {
            // Known API exceptions (400, 404, 401, etc)
            return Response.json(ex.getStatus(), new ErrorResponse(ex.getStatus(), ex.getMessage()));
        } catch (Exception ex) {
            return Response.json(500, new ErrorResponse(500, "Internal Server Error"));
        }
    }

    private Map<String, String> parseQuery(String rawQuery) {
        Map<String, String> queryParams = new HashMap<>();
        if (rawQuery == null || rawQuery.isEmpty()) return queryParams;

        String[] pairs = rawQuery.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            queryParams.put(kv[0], kv.length > 1 ? kv[1] : "");
        }
        return queryParams;
    }
}