// 400 Bad Request
package app.http.exception;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(400, message);
    }
}