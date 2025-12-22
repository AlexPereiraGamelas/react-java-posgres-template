package app.repository;

import app.db.AbstractRepository;
import app.http.Pagination;
import app.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Book> findAll(Pagination pagination) {
        return withConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement(
                    """
                            SELECT id, title, author, publisher
                            FROM books
                            ORDER BY id
                            LIMIT ?
                            OFFSET ?
                            """
            );
            ps.setInt(1, pagination.limit());
            ps.setInt(2, pagination.offset());

            ResultSet rs = ps.executeQuery();

            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(map(rs));
            }

            return books;
        });
    }

    public Book register(Book book) {
        return withConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement(
                    """
                            INSERT INTO books (title, author, publisher)
                            VALUES (?, ?, ?)
                            RETURNING id, title, author, publisher
                            """
            );

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("Failed to insert book");
            }

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