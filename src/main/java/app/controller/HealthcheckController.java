package app.controller;

import app.http.Request;
import app.http.Response;

public class HealthcheckController {
    public Response check(Request req) {
        return Response.json(200, "Alive and Well\n");
    }
}
