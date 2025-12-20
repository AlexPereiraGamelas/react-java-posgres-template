package app.repository;

import app.db.AbstractRepository;
import app.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository extends AbstractRepository {

    public User findById(long id) {
        return withConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, name, email FROM users WHERE id = ?"
            );
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            return map(rs);
        });
    }

    public User findRandom() {
        return withConnection(conn -> {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT id, name, email FROM users ORDER BY random() LIMIT 1"
            );
            if (!rs.next()) return null;
            return map(rs);
        });
    }

    private User map(ResultSet rs) throws Exception {
        return new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );
    }
}