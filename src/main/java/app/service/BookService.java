package app.service;

import app.model.Book;
import app.repository.BookRepository;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public Book getExampleBook() {
        return new Book(1265, "The Silmarilion", "Tolkien", "Beira Mar");
    }

    public Book getBookById() {
        return bookRepository.findById(1);
    }

    public static String toJson(Book book) {
        return "{"
                + "\"id\":" + book.getId() + ","
                + "\"title\":" + book.getTitle() + ","
                + "\"author\":\"" + book.getAuthor() + "\","
                + "\"publisher\":\"" + book.getPublisher() + "\""
                + "}";
    }
}
