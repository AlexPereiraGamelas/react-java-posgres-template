package app.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class Database {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env("DB_URL"));
        config.setUsername(env("DB_USER"));
        config.setPassword(env("DB_PASSWORD"));

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setAutoCommit(false);

        dataSource = new HikariDataSource(config);
    }

    private Database() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static String env(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new RuntimeException("Missing env var: " + key);
        }
        return value;
    }
}