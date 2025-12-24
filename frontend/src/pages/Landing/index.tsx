import {type FormEvent, useEffect, useState} from "react";
import {Link} from "react-router-dom"

import type {PaginatedResponse} from "src/types/api/PaginatedResponse"
import type {Book} from "src/types/models/Book"

import "./index.css"

const handleSubmit = (form: FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    const formData = new FormData(form.currentTarget);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/book/new", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        title: formData.get("title"),
        author: formData.get("author"),
        publisher: formData.get("publisher")
    }));
}

const BookPreview = ({book}: { book: Book }) => {
    return (
        <div key={book?.id} className={"book-preview"}>
            <h2>{book?.title}</h2>
            <Link to={`/book/${book?.id}`}>See More</Link>
            <hr/>
        </div>
    )
}

const Landing = () => {
    const [books, setBooks] = useState<PaginatedResponse<Book>>()

    useEffect(() => {
        fetch("/api/book")
            .then(response => response.json())
            .then((data: PaginatedResponse<Book>) => {
                setBooks(data)
            })
    }, [])

    return (
        <main>
            <form
                onSubmit={handleSubmit}
                style={{display: "flex", flexDirection: "column", padding: "0 30%", gap: "1rem", marginBottom: "8rem"}
                }>
                <h3>Register New Book</h3>
                <input name={"title"} type={"text"} placeholder={"Book title"} required={true}></input>
                <input name={"author"} type={"text"} placeholder={"Book author"} required={true}></input>
                <input name={"publisher"} type={"text"} placeholder={"Book publisher"} required={true}></input>
                <button type={"submit"}>Create</button>
            </form>

            <div className={"book-list"}>
                <h1>All Books</h1>
                <hr/>
                <div className={"book-list-container"}>
                    {books?.items.map(book => (
                        <BookPreview book={book}/>
                    ))}
                </div>
            </div>
        </main>
    )
}

export default Landing