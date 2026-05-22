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
  const [pin, setPin] = useState('')

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
      await updateNote(id, { title, content, pin })
      const fresh = await fetchNoteById(id)
      setNote(fresh)
      setTitle(fresh.title)
      setContent(fresh.content)
      setEditMode(false)
    } catch (err) {
      setError(err.message)
    }
  }

  const formatDate = (dateString) => {
    const date = new Date(dateString)
    const time = date.toLocaleTimeString('de-CH', { 
      hour: '2-digit', 
      minute: '2-digit' 
    })
    const day = date.toLocaleDateString('de-CH')
    return `${time}, ${day}`
  }

  useEffect(() => {
    fetchNoteById(id)
      .then(data => {
        setNote(data)
        setTitle(data.title)
        setContent(data.content)
        setPin(data.pin)
      })
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [id])

  if (loading) return <p className="loading">Loading...</p>
  if (error) return <p className="error">Error: {error}</p>
  if (!note) return null

  return (
    <div className="note-detail">
      <button onClick={() => navigate(-1)}>←</button>

      {editMode ? (
        <>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="title"
          />

          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="content"
          />

          <button onClick={handleUpdate}>Speichern</button>
          <button onClick={() => setEditMode(false)}>Abbrechen</button>
        </>
      ) : (
        <>
          <div className="note-header">
            <h1>{note.title}</h1>
            <div>{note.tag}</div>
          </div>
          <p>{note.content}</p>

          <button onClick={() => setEditMode(true)}>
            edit
          </button>

          <button onClick={handleDelete}>
            delete
          </button>
     
          <div className="note-footer">
            <p>{formatDate(note.date)}</p>
            <span className="pin-status">
            {note.pin && <span style={{ color: "green" }}>pinned</span>}            
            </span>
          </div>
        </>
      )}
    </div>
  )
}

export default NoteDetailPage