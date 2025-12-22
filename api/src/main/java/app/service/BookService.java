package app.service;

import app.http.PaginatedResponse;
import app.http.Pagination;
import app.http.PaginationPolicy;
import app.model.Book;
import app.repository.BookRepository;

import java.util.List;

public class BookService {
    private static final PaginationPolicy PAGINATION_POLICY = new PaginationPolicy(20, 100, 0);

    private final BookRepository bookRepository = new BookRepository();

    public Book getBookById(long id) {
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
}
