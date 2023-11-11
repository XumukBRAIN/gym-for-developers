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

    private final GdNoteService gdNoteService;
    private final GdNoteConverter gdConverterNote;

    @Autowired
    public GdNoteController(GdNoteService gdNoteService, GdNoteConverter gdConverterNote) {
        this.gdNoteService = gdNoteService;
        this.gdConverterNote = gdConverterNote;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdNoteDto gdNoteDTO) {
        gdNoteService.save(gdConverterNote.convertToEntity(gdNoteDTO));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @GetMapping("/{id}")
    public GdNote getNote(@PathVariable("id") int id) {
        return gdNoteService.findOne(id);
    }

}
