import { Link } from 'react-router-dom'

function NoteCard({ note }) {
  return (
    <Link to={`/notes/${note.noteId}`} className="note-card">
      <h2>{note.title}</h2>
      <p>{note.content?.substring(0, 100)}...</p>
    </Link>
  )
}

export default NoteCard
