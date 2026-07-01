package com.example.note.DTO;

import com.example.note.Model.Tags;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data used to create or update a note")
public class NoteDTO {

    @NotBlank(message = "Title must not be empty")
    @Schema(description = "Title of the note", example = "Grocery list", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "Content must not be empty")
    @Schema(description = "Main content of the note", example = "Milk, eggs, bread", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Category tag of the note")
    private Tags tag;

    @Schema(description = "Whether the note is pinned", example = "false")
    private boolean pin;

    @Schema(description = "Date the note was created or last updated", example = "09:16, 1.7.2026")
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