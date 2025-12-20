package app.controller;

import app.http.Request;
import app.http.Response;
import app.model.User;
import app.service.UserService;

public class UserController {

    private final UserService userService = new UserService();

    public Response sample(Request req) {
        User user = userService.getExampleUser();
        return Response.json(200, UserService.toJson(user));
    }

    public Response defaultUser(Request req) {
        User user = userService.getDefaultUser();
        return Response.json(200, UserService.toJson(user));
    }
}