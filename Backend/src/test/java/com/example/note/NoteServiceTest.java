package com.example.note;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.note.DTO.NoteDTO;
import com.example.note.Model.Note;
import com.example.note.Model.Tags;
import com.example.note.Repository.NoteRepo;
import com.example.note.Service.NoteService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    NoteRepo repo;

    @InjectMocks
    NoteService service;

    @Test
    void getAllNotesTest() {
        List<Note> notes = List.of(new Note("1", "Title", Tags.WORK, false, "1.1.2000"));

        when(repo.findAll()).thenReturn(notes);

        List<Note> result = service.getAllNotes();

        assertEquals(notes, result);
    }

    @Test
    void getNoteTest(){
        Note note = new Note("1", "1", Tags.WORK, false, "1.1.2000");

        when(repo.findById(0)).thenReturn(Optional.of(note));

        Note result = service.getNote(0);

        assertEquals(note, result);
    }

    @Test 
    void getNoteNotFoundTest(){
        when(repo.findById(0)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getNote(0));
    }

    @Test 
    void createNoteTest(){
        NoteDTO dto = new NoteDTO("1", "1", Tags.WORK);

        Note note = new Note("1", "1", Tags.WORK, false, "1.1.200");

        when(repo.save(any(Note.class))).thenReturn(note);

        String result = service.createNote(dto);

        assertEquals("Saved Note with ID: " + note.getNoteId(), result);
    }

    @Test 
    void deleteNoteTest(){
        Note note = new Note("1", "1", Tags.WORK, false, "1.1.2000");
        
        when(repo.findById(0)).thenReturn(Optional.of(note));

        String result = service.deleteNote(0);

        assertEquals("Deleted Note with ID: 0", result);
    }

    @Test 
    void deleteNoteNotFoundTest(){
        when(repo.findById(0)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.deleteNote(0));
    }

    @Test
    void updateNoteTest(){
        NoteDTO dto = new NoteDTO("Updated Title", "Updated Content", Tags.PERSONAL);
        Note note = new Note("1", "1", Tags.WORK, false, "1.1.2000");

        when(repo.findById(0)).thenReturn(Optional.of(note));
        when(repo.save(any(Note.class))).thenReturn(note);

        String result = service.updateNote(dto, 0);

        assertEquals("Updated Note with ID: 0", result);
    }

    @Test 
    void updateNoteNotFoundTest(){
        NoteDTO dto = new NoteDTO("Title", "Content", Tags.WORK);
        
        when(repo.findById(0)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateNote(dto, 0));
    }
}

