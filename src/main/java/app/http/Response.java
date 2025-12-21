package app.http;

import app.util.Json;

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

    public static Response json(int status, Object obj) {
        Response r = new Response();
        r.status = status;
        r.body = Json.toJson(obj); // serialize using Gson
        r.headers.put("Content-Type", "application/json");
        return r;
    }
}