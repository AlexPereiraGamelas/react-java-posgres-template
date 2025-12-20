package app.routing;

import java.util.regex.Pattern;

public class Route {
    public final String method;
    public final Pattern pathPattern;
    public final RouteHandler handler;

    public Route(String method, Pattern pathPattern, RouteHandler handler) {
        this.method = method;
        this.pathPattern = pathPattern;
        this.handler = handler;
    }
}