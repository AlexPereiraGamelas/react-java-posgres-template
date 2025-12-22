package app.db;

import app.db.helpers.SqlFunction;
import app.db.helpers.SqlConsumer;
import java.sql.Connection;

public abstract class AbstractRepository {

    protected <T> T withConnection(SqlFunction<Connection, T> fn) {
        try (Connection conn = Database.getConnection()) {
            T result = fn.apply(conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Database error", e);
        }
    }

    protected void withTransaction(SqlConsumer<Connection> fn) {
        try (Connection conn = Database.getConnection()) {
            fn.accept(conn);
            conn.commit();
        } catch (Exception e) {
            throw new RuntimeException("Transaction failed", e);
        }
    }
}



