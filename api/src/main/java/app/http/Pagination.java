package app.http;

public class Pagination {

    private final int limit;
    private final int offset;

    public Pagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int limit() {
        return limit;
    }

    public int offset() {
        return offset;
    }

    public static Pagination of(Integer limit, Integer offset) {
        int safeLimit = (limit == null || limit <= 0) ? 20 : Math.min(limit, 100);
        int safeOffset = (offset == null || offset < 0) ? 0 : offset;
        return new Pagination(safeLimit, safeOffset);
    }
}