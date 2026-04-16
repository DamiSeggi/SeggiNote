import { Routes, Route } from 'react-router-dom'
import NotesPage from './pages/NotesPage'
import NoteDetailPage from './pages/NoteDetailPage'
import './App.css'

function App() {
  return (
    <Routes>
      <Route path="/"        element={<NotesPage />} />
      <Route path="/notes/:id" element={<NoteDetailPage />} />
    </Routes>
  )
}

export default App
    