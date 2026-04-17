import { useParams, useNavigate } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { fetchNoteById, deleteNote } from '../api/noteApi'

function NoteDetailPage() {
  const { id } = useParams()
  const navigate = useNavigate()
  const [note, setNote]     = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError]   = useState(null)

  const handleDelete = async () => {
  try {
    await deleteNote(id)
    navigate('/') 
  } catch (err) {
    setError(err.message)
  }
}

  useEffect(() => {
    fetchNoteById(id)
      .then(data => setNote(data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [id])

  if (loading) return <p>Lädt...</p>
  if (error)   return <p>Fehler: {error}</p>

  return (
    <div className="note-detail">
      <button onClick={() => navigate(-1)}>Zurück</button>
      <h1>{note.title}</h1>
      <p>{note.content}</p>
      <button onClick={handleDelete}>Löschen</button>    
    </div>
  )
}

export default NoteDetailPage
