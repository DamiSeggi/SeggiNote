import { useEffect, useState } from 'react'
import NoteCard from '../components/NoteCard'
import { fetchAllNotes } from '../api/noteApi'
import { createNote } from '../api/noteApi'

function NotesPage() {
  const [notes, setNotes]   = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError]   = useState(null)
    const [createMode, setCreateMode] = useState(false);
    const [title, setTitle] = useState("")
const [content, setContent] = useState("")

useEffect(() => {
  loadNotes()
}, [])

  const loadNotes = () => {
  setLoading(true)
  fetchAllNotes()
    .then(data => setNotes(data))
    .catch(err => setError(err.message))
    .finally(() => setLoading(false))
}

  const handleSave = async () => {
    await createNote({ title, content })
    await loadNotes()
    setCreateMode(false)
  }

  if (loading) return <p>Lädt...</p>
  if (error)   return <p>Fehler: {error}</p>

    return (
    createMode ? (
        <div>
        <button onClick={() => setCreateMode(false)}>X</button>
        <h1>Note erstellen</h1>

        <input placeholder='titel' value={title}
        onChange={(e) => setTitle(e.target.value)}
        ></input>
        <input placeholder='content' value={content}
        onChange={(e) => setContent(e.target.value)}
        ></input>

        <button onClick={handleSave}>save</button>
        </div>
    ) : (
        <div className="notes-page">
        <button onClick={() => setCreateMode(true)}> + </button>
        <h1>Alle Notes</h1>
        <div className="notes-grid">
            {notes.map(note => (
            <NoteCard key={note.noteId} note={note} />
            ))}
        </div>
        </div>
    )
    );
}

export default NotesPage
