import {type FormEvent, useCallback, useEffect, useState} from 'react'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

import {Landing, BookDetails} from "src/pages"

import './App.css'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Landing/>}/>
                <Route path="/book/:id" element={<BookDetails/>}/>
            </Routes>
        </Router>
    );
}

export default App
