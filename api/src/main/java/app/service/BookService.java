package app.service;

import app.http.PaginatedResponse;
import app.http.Pagination;
import app.http.PaginationPolicy;
import app.http.Response;
import app.http.exception.ApiException;
import app.http.exception.NotFoundException;
import app.model.Book;
import app.repository.BookRepository;

import java.util.List;
import java.util.Map;

public class BookService {
    private static final PaginationPolicy PAGINATION_POLICY = new PaginationPolicy(20, 100, 0);

    private final BookRepository bookRepository = new BookRepository();

    public Book getBookById(Integer id) {
        Book foundBook = bookRepository.findById(id);
        if (foundBook == null) {
            throw new NotFoundException("No book found");
        }
        return bookRepository.findById(id);
    }

    public PaginatedResponse<Book> getBooks(Integer offset, Integer limit) {
        Pagination pagination = PAGINATION_POLICY.apply(limit, offset);
        List<Book> books = bookRepository.findAll(pagination);
        int total = books.size();
        if (total == 0) {
            throw new NotFoundException("No books found");
        }
        return new PaginatedResponse<>(books, total, pagination.offset());
    }

    public Book register(Book book) {
        Book newBook = bookRepository.register(book);
        if (newBook == null) {
            throw new ApiException(500, "Error creating book");
        }
        return newBook;
    }

    public Response updateBook(Integer id, Book book) {
        Book updatedBook = bookRepository.update(id, book);
        if (updatedBook == null) {
            throw new NotFoundException("Could not update book");
        }
        ;
        return Response.json(200, updatedBook);
    }

    public Response deleteBook(Integer id) {
        boolean deletedBook = bookRepository.delete(id);
        if (!deletedBook) {
            throw new ApiException(500, "Error deleting book");
        }
        return Response.json(200, Map.of("message", "Book deleted successfully"));
    }
}
