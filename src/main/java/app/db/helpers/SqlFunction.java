package app.db.helpers;

public interface SqlFunction<T, R> {
    R apply(T t) throws Exception;
}