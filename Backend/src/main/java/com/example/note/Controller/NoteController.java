package com.example.note.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.note.Service.NoteService;
import com.example.note.DTO.NoteDTO;
import com.example.note.Model.Note;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Notes", description = "CRUD operations for notes")
@RestController
@RequestMapping("/api/v1")
public class NoteController {
    private NoteService service;

    public NoteController(NoteService service){
        this.service = service;
    }

    @Operation(summary = "Get all notes")
    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return service.getAllNotes();
    }

    @Operation(summary = "Get a single note")
    @GetMapping("/note/{id}")
    public Note getNote(@Parameter(description = "Note ID") @PathVariable int id){
        return service.getNote(id);
    }

    @Operation(summary = "Create a new note")
    @PostMapping("/note")
    public String createNote(@Valid @RequestBody NoteDTO dto) {
        return service.createNote(dto);
    }

    @Operation(summary = "Update an existing note")
    @PutMapping("/note/{id}")
    public String updateNote(@Parameter(description = "Note ID") @PathVariable int id, @Valid @RequestBody NoteDTO dto) {
    return service.updateNote(dto, id);
    }

    @Operation(summary = "Delete a note")
    @DeleteMapping("/note/{id}")
    public String deleteNote(@Parameter(description = "Note ID") @PathVariable int id){
        return service.deleteNote(id);
    }

    @Operation(summary = "Toggle pin status of a note")
    @PatchMapping("/note/pin/{id}")
    public String managePin(@Parameter(description = "Note ID") @PathVariable int id){
        return service.managePin(id);
    }
}