// 404 Not Found
package app.http.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(404, message);
    }
}