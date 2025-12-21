package app.routing;

import java.util.List;
import java.util.regex.Pattern;

public class Route {
    public final String method;
    public final Pattern pathPattern;
    public final RouteHandler handler;
    public List<String> paramNames;

    public Route(String method, Pattern pathPattern, RouteHandler handler, List<String> paramNames) {
        this.method = method;
        this.pathPattern = pathPattern;
        this.handler = handler;
        this.paramNames = paramNames;
    }
}