package app.http;

public class PaginationPolicy {

    private final int defaultLimit;
    private final int maxLimit;
    private final int minOffset;

    public PaginationPolicy(int defaultLimit, int maxLimit, int minOffset) {
        this.defaultLimit = defaultLimit;
        this.maxLimit = maxLimit;
        this.minOffset = minOffset;
    }

    public Pagination apply(Integer limit, Integer offset) {
        int finalLimit;
        int finalOffset;

        if (limit == null && offset == null) {
            // No pagination provided
            finalLimit = defaultLimit;
            finalOffset = 0;
        } else {
            // Partial or full pagination
            finalLimit = (limit == null)
                    ? defaultLimit
                    : Math.min(limit, maxLimit);

            finalOffset = (offset == null || offset < minOffset)
                    ? minOffset
                    : offset;
        }

        return new Pagination(finalLimit, finalOffset);
    }
}