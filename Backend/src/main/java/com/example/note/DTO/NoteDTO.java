package com.example.note.DTO;

import com.example.note.Model.Tags;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class NoteDTO {
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Tags tag;
    private boolean pin;
    public String date;

    public NoteDTO() {}

    public NoteDTO(String title, String content, Tags tag){
        this.title = title;
        this.content = content;
        this.tag = tag;
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
