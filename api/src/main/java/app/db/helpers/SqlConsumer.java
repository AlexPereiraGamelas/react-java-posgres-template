package app.db.helpers;

public interface SqlConsumer<T> {
    void accept(T t) throws Exception;
}