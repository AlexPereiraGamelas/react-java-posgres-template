CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    publisher TEXT NOT NULL
);

INSERT INTO books (title, author, publisher)
VALUES ('O Senhor dos Aneis: Irmandade do Anel', 'Tolkien', 'Beira Mar');
INSERT INTO books (title, author, publisher)
VALUES ('O Senhor dos Aneis: As Duas Torres', 'Tolkien', 'Beira Mar');
INSERT INTO books (title, author, publisher)
VALUES ('O Senhor dos Aneis: O Regresso do Rei', 'Tolkien', 'Beira Mar');
