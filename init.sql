CREATE TABLE IF NOT EXISTS books (
     id SERIAL PRIMARY KEY,
     title TEXT NOT NULL,
     author TEXT NOT NULL,
     publisher TEXT NOT NULL
);

INSERT INTO books (title, author, publisher)
SELECT
    'Book ' || gs,
    'Author ' || gs,
    'Publisher ' || ((gs % 10) + 1)
FROM generate_series(1, 100) AS gs;
