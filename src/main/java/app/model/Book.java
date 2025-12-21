package app.model;

public class Book {

    private final long id;
    private final String title;
    private final String author;
    private final String publisher;

    public Book(long id, String title, String author, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {return publisher; }
}