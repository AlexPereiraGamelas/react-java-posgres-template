package app.http;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public int status;
    public String body;
    public Map<String, String> headers = new HashMap<>();

    public static Response json(int status, String body) {
        Response r = new Response();
        r.status = status;
        r.body = body;
        r.headers.put("Content-Type", "application/json");
        return r;
    }
}