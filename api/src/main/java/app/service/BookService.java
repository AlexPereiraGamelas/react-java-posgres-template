package app.service;

import app.http.PaginatedResponse;
import app.http.Pagination;
import app.http.PaginationPolicy;
import app.http.Response;
import app.model.Book;
import app.repository.BookRepository;

import java.util.List;
import java.util.Map;

public class BookService {
    private static final PaginationPolicy PAGINATION_POLICY = new PaginationPolicy(20, 100, 0);

    private final BookRepository bookRepository = new BookRepository();

    public Book getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public PaginatedResponse<Book> getBooks(Integer offset, Integer limit) {
        Pagination pagination = PAGINATION_POLICY.apply(limit, offset);
        List<Book> books = bookRepository.findAll(pagination);
        int total = books.size();
        return new PaginatedResponse<>(books, total, pagination.offset());
    }

    public Book register(Book book) {
        return bookRepository.register(book);
    }

    public Response updateBook(Integer id, Book book) {
        Book updated = bookRepository.update(id, book);
        if (updated == null) return Response.json(404, Map.of("error", "Book not found"));
        return Response.json(200, updated);
    }

    public Response deleteBook(Integer id) {
        boolean deleted = bookRepository.delete(id);
        if (!deleted) return Response.json(404, Map.of("error", "Book not found"));
        return Response.json(200, Map.of("message", "Book deleted successfully"));
    }
}
