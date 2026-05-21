package com.example.note.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.note.DTO.NoteDTO;
import com.example.note.Model.Note;
import com.example.note.Repository.NoteRepo;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

@Service
public class NoteService {
    private NoteRepo repo;

    public NoteService(NoteRepo repo){
        this.repo = repo;
    }

    public List<Note> getAllNotes(){
        return repo.findAll();
    }

    public Note getNote(int id){
        return repo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Note with ID " + id + " not found"));
    }

    public String createNote(NoteDTO dto){
        LocalDateTime now = LocalDateTime.now();
        Note note = new Note(dto.getTitle(), dto.getContent(), dto.getTag(), dto.getPin(), (String.valueOf(now)));
        repo.save(note);
        return "Saved Note with ID: " + note.getNoteId();
    }

    public String updateNote(NoteDTO dto, int id){
        Note note = repo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Note with ID " + id + " not found"));
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setTag(dto.getTag());
        note.setPin(dto.getPin());
        LocalDateTime now = LocalDateTime.now();
        note.setDate(String.valueOf(now));
        repo.save(note);
        return "Updated Note with ID: " + id;
    }

    public String deleteNote(int id){
        Note note = repo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Note with ID " + id + " not found"));
        repo.delete(note);
        return "Deleted Note with ID: " + id;
    }

    public String managePin(int id){
        Note note = repo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Note with ID " + id + " not found"));

        if(note.getPin() == false){
            note.setPin(true);
            repo.save(note);
            return "pinned note with ID: " + note.getNoteId();
        } else {
            note.setPin(false);
            repo.save(note);
            return "un-pinned note with ID: " + note.getNoteId();
        }
    }
    
}