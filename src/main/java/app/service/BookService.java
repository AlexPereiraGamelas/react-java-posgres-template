package app.service;

import app.model.Book;
import app.repository.BookRepository;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }
}
