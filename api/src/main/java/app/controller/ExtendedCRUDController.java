package app.controller;

import app.routing.Router;

public abstract class ExtendedCRUDController extends CRUDController {

    protected ExtendedCRUDController(String basePath) {
        super(basePath);
    }

    @Override
    public void registerRoutes(Router router) {
        super.registerRoutes(router);  // register standard CRUD
        registerCustomRoutes(router);  // register additional custom routes
    }

    /**
     * Example Implementation
     * BookExtendedCRUDController
     * <p>
     * #Override protected void registerCustomRoutes(Router router) {
     * router.register("GET", "/books/random", this::randomBook);
     * }
     * <p>
     * private Response randomBook(Request req) {
     * return Response.json(200, bookService.getRandomBook());
     * }
     */
    protected abstract void registerCustomRoutes(Router router);
}
