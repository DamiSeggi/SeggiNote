import { useState } from 'react';
import { Link } from 'react-router-dom'
import { managePin } from '../api/noteApi';

function NoteCard({ note }) {

  const[pin, setPin] = useState(false);

    const handlePin = async () => {
      try {
        await managePin(note.noteId)
        setPin(prev => !prev);
      } catch (err) {
        setError(err.message)
      }
    }

  return (
  <div className="note-card">
    <button className="pin-button" onClick={handlePin}>
      {pin 
        ? <span style={{ color: "green" }}>pinned</span> 
        : <span style={{ color: "grey" }}>pin</span>}
    </button>
    
    <Link to={`/notes/${note.noteId}`}>
      <h2>{note.title}</h2>
      <p>{note.content?.substring(0, 100)}...</p>
      <div>{note.tag}</div>
    </Link>
  </div>
  )
}

export default NoteCard