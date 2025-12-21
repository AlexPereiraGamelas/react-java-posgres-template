CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    publisher TEXT NOT NULL
);

INSERT INTO books (title, author, publisher)
VALUES ('O Senhor dos Aneis', 'Tolkien', 'Beira Mar');
