package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdNoteConverter;
import com.dev.gdmainservice.models.dto.GdNoteDto;
import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.services.GdNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class GdNoteController {

    private final GdNoteService noteService;

    @Autowired
    public GdNoteController(GdNoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdNoteDto noteDto) {
        noteService.save(GdNoteConverter.convertToEntity(noteDto));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @GetMapping("/{id}")
    public GdNote getNote(@PathVariable("id") int id) {
        return noteService.findOne(id);
    }
}
