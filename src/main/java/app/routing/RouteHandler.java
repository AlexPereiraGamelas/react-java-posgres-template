package app.routing;

import app.http.Request;
import app.http.Response;

@FunctionalInterface
public interface RouteHandler {
    Response handle(Request request) throws Exception;
}