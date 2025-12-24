import {type FormEvent, useState, useEffect, useCallback} from "react"
import {useParams, useNavigate} from "react-router-dom";
import type {Book} from "src/types/models/Book"

const EMPTY_BOOK = {
    id: 0,
    title: "",
    author: "",
    publisher: "",
}

const BookDetails = () => {
    const {id: bookID} = useParams()
    const navigate = useNavigate()
    const [book, setBook] = useState<Book>(EMPTY_BOOK)
    const [formBook, setFormBook] = useState<Book>(EMPTY_BOOK)
    const {title: formTitle, author: formAuthor, publisher: formPublisher} = formBook

    useEffect(() => {
        fetch(`/api/book/${bookID}`)
            .then(response => response.json())
            .then((data: Book) => {
                setBook(data)
                setFormBook(data)
            })
    }, [bookID]);

    const handleSubmit = useCallback((form: FormEvent<HTMLFormElement>) => {
        form.preventDefault();
        const formData = new FormData(form.currentTarget);

        const xhr = new XMLHttpRequest();
        xhr.open("PUT", `/api/book/${bookID}`, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            title: formData.get("title"),
            author: formData.get("author"),
            publisher: formData.get("publisher")
        }));
    }, [bookID])

    const handleTextInputChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        const key = e.target.name;

        setFormBook((prevBook) => ({
            ...prevBook,
            [key]: value
        }))
    }, [setFormBook])

    const handleDelete = useCallback(() => {
        const xhr = new XMLHttpRequest();
        xhr.open("DELETE", `/api/book/${bookID}`, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send();

        navigate("/")
    }, [navigate])


    return (
        <div style={{display: "flex", alignItems: "center", flexDirection: "column", width: "100%", gap: "4rem"}}>
            <div style={{border: "1px solid black", width: "60%"}}>
                <h2>{book?.title}</h2>
                <hr/>
                <h3>{book?.author}</h3>
                <p>{book?.publisher}</p>
            </div>
            <div style={{width: "100%"}}>
                <h1>Edit Book</h1>
                <form
                    onSubmit={handleSubmit}
                    style={{
                        display: "flex",
                        flexDirection: "column",
                        padding: "0 30%",
                        gap: "1rem",
                    }
                    }>
                    <input
                        name={"title"}
                        type={"text"}
                        value={formTitle}
                        placeholder={"Book title"}
                        onChange={handleTextInputChange}
                    />
                    <input
                        name={"author"}
                        type={"text"}
                        value={formAuthor}
                        placeholder={"Book author"}
                        onChange={handleTextInputChange}
                    />
                    <input
                        name={"publisher"}
                        type={"text"}
                        value={formPublisher}
                        placeholder={"Book publisher"}
                        onChange={handleTextInputChange}
                    />
                    <button type={"submit"}>Update</button>
                </form>
            </div>

            <div style={{width: "100%"}}>
                <h1>Delete Book</h1>
                <button
                    style={{backgroundColor: "red", color: "white", border: "none", padding: "0.5rem 1rem"}}
                    onClick={handleDelete}
                >
                    Delete
                </button>
            </div>
        </div>
    )
}

export default BookDetails