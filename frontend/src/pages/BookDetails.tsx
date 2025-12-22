import {useState, useEffect, useCallback} from "react"
import {useParams} from "react-router-dom";
import type {Book} from "src/types/models/Book"

const BookDetails = () => {
    const {id: bookID} = useParams()
    const [book, setBook] = useState<Book>()

    useEffect(() => {
        fetch(`/api/book/${bookID}`)
            .then(response => response.json())
            .then((data: Book) => {
                setBook(data)
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
                        marginBottom: "8rem"
                    }
                    }>
                    <input name={"title"} type={"text"} value={book?.title} placeholder={"Book title"}
                           required={true}></input>
                    <input name={"author"} type={"text"} value={book?.author} placeholder={"Book author"}
                           required={true}></input>
                    <input name={"publisher"} type={"text"} value={book?.publisher} placeholder={"Book publisher"}
                           required={true}></input>
                    <button type={"submit"}>Create</button>
                </form>
            </div>
        </div>
    )
}

export default BookDetails