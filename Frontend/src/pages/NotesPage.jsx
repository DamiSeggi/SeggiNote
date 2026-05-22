import { useEffect, useState } from 'react'
import NoteCard from '../components/NoteCard'
import { fetchAllNotes } from '../api/noteApi'
import { createNote } from '../api/noteApi'

function NotesPage() {
  const [notes, setNotes] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [createMode, setCreateMode] = useState(false)
  const [title, setTitle] = useState("")
  const [content, setContent] = useState("")
  const [tag, setTag] = useState("")
  const [search, setSearch] = useState("")

  useEffect(() => {
  loadNotes()
}, [search])

  const loadNotes = () => {
  fetchAllNotes()
    .then(data => {
      if (search === "") {
        setNotes(data)
      } else {
        const filtered = data.filter(note =>
          note.title.toLowerCase().includes(search.toLowerCase())
        )
        setNotes(filtered)
      }
    })
    .catch(err => setError(err.message))
    .finally(() => setLoading(false))
}

  const handleSave = async () => {
    if (title.trim() && content.trim()) {
      await createNote({ title, content, tag: tag || null})
      await loadNotes()
      setCreateMode(false)
      setTitle("")
      setContent("")
    }
  }

  if (loading) return <p className="loading">Loading...</p>
  if (error) return <p className="error">Error: {error}</p>

  return (
    <>
      {createMode ? (
        <div className="create-modal">
          <div className="create-modal-content">
            <div className="create-modal-header">
              <h1>Create note</h1>
              <button onClick={() => setCreateMode(false)}>✕</button>
            </div>

            <input
              type="text"
              placeholder="Title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />

            <textarea
              placeholder="Your note..."
              value={content}
              onChange={(e) => setContent(e.target.value)}
            />

            <select
              value={tag}
              onChange={(e) => setTag(e.target.value)}
            >
              <option value="">Click to select a tag...</option>
              <option value="WORK">Work</option>
              <option value="HOBBY">Hobby</option>
              <option value="HOME">Home</option>
              <option value="PERSONAL">Personal</option>
              <option value="OTHER">Other</option>
            </select>

            <div className="modal-buttons">
              <button onClick={() => setCreateMode(false)}>cancel</button>
              <button onClick={handleSave}>save</button>
            </div>
          </div>
        </div>
      ) : null}

      <div className="notes-page">
        <button onClick={() => setCreateMode(true)}>+</button>
        <h1>All notes</h1>

        <input 
            type="text"
            placeholder='search...'
            value={search}
            onChange={(e) => setSearch(e.target.value)}>
        </input>

        <div className="notes-grid">
          {[...notes]
          .sort((a, b) => b.pin - a.pin)
          .map(note => (
            <NoteCard key={note.noteId} note={note} />
        ))}
        </div>
      </div>
    </>
  )
}

export default NotesPage