import {type FormEvent, useCallback, useEffect, useState} from 'react'
import './App.css'

interface Book {
    id: number,
    title: string,
    author: string,
    publisher: string,
}

function App() {
    const [books, setBooks] = useState<Book[]>()

    useEffect(() => {
        fetch("/api/books")
            .then(response => response.json())
            .then(data => {
                setBooks(data.items)
            })
    }, [])

    const handleSubmit = useCallback((form: FormEvent<HTMLFormElement>) => {
        form.preventDefault();
        const formData = new FormData(form.currentTarget);

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/books/new", true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            title: formData.get("title"),
            author: formData.get("author"),
            publisher: formData.get("publisher")
        }));
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

            <div>
                <h1>All Books</h1>
                <hr/>
                {books?.map(book => (
                    <div key={book?.id}>
                        <h2>{book?.title}</h2>
                        <h3>{book?.author}</h3>
                        <p>Published by {book?.publisher}</p>
                        <hr/>
                    </div>
                ))}
            </div>
        </main>
    )
}

export default App
