import { useParams, useNavigate } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { fetchNoteById, deleteNote, updateNote } from '../api/noteApi'

function NoteDetailPage() {
  const { id } = useParams()
  const navigate = useNavigate()

  const [note, setNote] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const [editMode, setEditMode] = useState(false)
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')

  const handleDelete = async () => {
    try {
      await deleteNote(id)
      navigate('/')
    } catch (err) {
      setError(err.message)
    }
  }

const handleUpdate = async () => {
  try {
    await updateNote(id, { title, content })

    const fresh = await fetchNoteById(id)

    setNote(fresh)
    setTitle(fresh.title)
    setContent(fresh.content)

    setEditMode(false)
  } catch (err) {
    setError(err.message)
  }
}

  useEffect(() => {
    fetchNoteById(id)
      .then(data => {
        setNote(data)
        setTitle(data.title)
        setContent(data.content)
      })
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [id])

  if (loading) return <p>Lädt...</p>
  if (error) return <p>Fehler: {error}</p>

  return (
    <div className="note-detail">
      <button onClick={() => navigate(-1)}>Zurück</button>

      {editMode ? (
        <>
          <input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />

          <button onClick={handleUpdate}>Speichern</button>
          <button onClick={() => setEditMode(false)}>Abbrechen</button>
        </>
      ) : (
        <>
          <h1>{note.title}</h1>
          <p>{note.content}</p>

          <button onClick={() => setEditMode(true)}>
            Bearbeiten
          </button>

          <button onClick={handleDelete}>
            Löschen
          </button>
        </>
      )}
    </div>
  )
}

export default NoteDetailPage