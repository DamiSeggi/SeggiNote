import { useEffect, useState } from 'react'
import NoteCard from '../components/NoteCard'
import { fetchAllNotes } from '../api/noteApi'

function NotesPage() {
  const [notes, setNotes]   = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError]   = useState(null)

  useEffect(() => {
    fetchAllNotes()
      .then(data => setNotes(data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <p>Lädt...</p>
  if (error)   return <p>Fehler: {error}</p>

  return (
    <div className="notes-page">
      <h1>Alle Notes</h1>
      <div className="notes-grid">
        {notes.map(note => (
          <NoteCard key={note.noteId} note={note} />
        ))}
      </div>
    </div>
  )
}

export default NotesPage
