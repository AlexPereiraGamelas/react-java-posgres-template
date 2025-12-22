package app.http;

import java.util.List;

public class PaginatedResponse<T> {
    public List<T> items;
    public int total;
    public int offset;

    public PaginatedResponse(List<T> items, int total, int offset) {
        this.items = items;
        this.total = total;
        this.offset = offset;
    }
}