package app.routing;

import app.http.Request;
import app.http.Response;
import com.sun.net.httpserver.HttpExchange;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {

    private final List<Route> routes = new ArrayList<>();

    public void register(String method, String pathRegex, RouteHandler handler) {
        routes.add(new Route(method, Pattern.compile(pathRegex), handler));
    }

    public Response dispatch(HttpExchange exchange) throws Exception {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        for (Route route : routes) {
            if (!route.method.equalsIgnoreCase(method)) {
                continue;
            }

            Matcher matcher = route.pathPattern.matcher(path);
            if (matcher.matches()) {
                Map<String, String> pathParams = new HashMap<>();

                //regex route parameters matching
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    pathParams.put("param" + i, matcher.group(i));
                }

                Request request = new Request(exchange, pathParams);
                return route.handler.handle(request);
            }
        }

        // request has no match in registered routes
        return Response.json(404, "{ \"error\": \"Not Found\" }");
    }
}