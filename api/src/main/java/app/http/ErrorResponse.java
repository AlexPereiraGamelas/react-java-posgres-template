package app.http;

public class ErrorResponse {
    public final int status;
    public final String error;

    public ErrorResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }
}