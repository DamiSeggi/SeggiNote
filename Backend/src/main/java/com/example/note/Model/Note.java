package com.example.note.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    private Tags tag;
    private boolean pin = false;
    public String date;

    public Note() {}

    public Note(String title, String content, Tags tag, boolean pin, String date){
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.pin = pin;
        this.date = date;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    public boolean getPin (){
        return pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
