package app.controller;

import app.http.Request;
import app.http.Response;
import app.routing.Router;

public abstract class CRUDController<T, ID> implements Controller {

    protected final String basePath;

    protected CRUDController(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public void registerRoutes(Router router) {
        router.register("GET", basePath, this::list);
        router.register("GET", basePath + "/{id}", this::read);
        router.register("POST", basePath, this::create);
        router.register("PUT", basePath + "/{id}", this::update);
        router.register("DELETE", basePath + "/{id}", this::delete);
    }

    // Abstract CRUD handlers
    public abstract Response list(Request req);

    public abstract Response read(Request req);

    public abstract Response create(Request req) throws Exception;

    public abstract Response update(Request req) throws Exception;

    public abstract Response delete(Request req);
}