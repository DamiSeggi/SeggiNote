import api from './axiosInstance'

export async function fetchAllNotes() {
  const res = await api.get('/notes')
  return res.data
}

export async function fetchNoteById(id) {
  const res = await api.get(`/note/${id}`)
  return res.data
}

export async function createNote(dto) {
  const res = await api.post('/note', dto)
  return res.data
}

export async function updateNote(id, dto) {
  const res = await api.put(`/note/${id}`, dto)
  return res.data
}

export async function deleteNote(id) {
    const res = await api.delete(`/note/${id}`)
}

export async function managePin(id) {
    const res = await api.patch(`/note/pin/${id}`)
}