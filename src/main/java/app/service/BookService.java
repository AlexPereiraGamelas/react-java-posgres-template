package app.service;

import app.http.PaginatedResponse;
import app.model.Book;
import app.repository.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }

    public PaginatedResponse<Book> getBooks(int offset, int limit) {
        List<Book> books = bookRepository.findAll();
        int total = books.size();
        return new PaginatedResponse<>(books, total, offset);
    }
}
