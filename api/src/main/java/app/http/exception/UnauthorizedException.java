// 401 Unauthorized (if you add auth later)
package app.http.exception;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(401, message);
    }
}