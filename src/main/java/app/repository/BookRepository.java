package app.repository;

import app.db.AbstractRepository;
import app.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookRepository extends AbstractRepository {

    public Book findById(long id) {
        return withConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, title, author, publisher FROM books WHERE id = ?"
            );
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            return map(rs);
        });
    }

    private Book map(ResultSet rs) throws Exception {
        return new Book(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("publisher")
        );
    }
}