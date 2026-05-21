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


@RestController
@RequestMapping("/api/v1")
public class NoteController {
    private NoteService service;

    public NoteController(NoteService service){
        this.service = service;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return service.getAllNotes();
    }

    @GetMapping("/note/{id}")
    public Note getNote(@PathVariable int id){
        return service.getNote(id);
    }

    @PostMapping("/note")
    public String createNote(@RequestBody NoteDTO dto) {
        return service.createNote(dto);
    }

    @PutMapping("/note/{id}")
    public String updateNote(@PathVariable int id, @RequestBody NoteDTO dto) {
       return service.updateNote(dto, id);
    }

    @DeleteMapping("/note/{id}")
    public String deleteNote(@PathVariable int id){
        return service.deleteNote(id);
    }

    @PatchMapping("/note/pin/{id}")
    public String managePin(@PathVariable int id){
        return service.managePin(id);
    }
}
